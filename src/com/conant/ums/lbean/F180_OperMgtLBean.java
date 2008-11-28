package com.conant.ums.lbean;

import java.sql.*;
import java.util.*;

import com.conant.ums.data.F180_OperMgt;
import com.conant.ums.db.DbAccess;
import com.conant.ums.interfaces.*;
import com.conant.ums.util.*;
import com.conant.ums.util.tree.*;

public class F180_OperMgtLBean
    extends BaseLBean {
    private static List allOperList = null;

    public F180_OperMgtLBean() {
    }

    //查询系统操作树
    public List SelectData() throws Exception {
        List rootList = new ArrayList();
        TreeNode trRoot = null;
        List OperList = new ArrayList();
        Map DeptMap = new HashMap();

        ExternResource externResource = (ExternResource)Class.forName(ComGlobal.OUTRESOURCE).newInstance();
        List operList = externResource.getAllOperation();

        try {
            for (int i = 0; i < operList.size(); i++) {
                OperationInfo oper = (OperationInfo) operList.get(i);
//                System.out.println(oper.getOperId());
//                System.out.println(oper.getOperName());
//                System.out.println(oper.getParentId());


                TreeNode treenode = new TreeNode(String.valueOf(oper.getOperId()),
                                                 String.valueOf(oper.getParentId()),
                                                 oper.getOperName());
                OperList.add(treenode);
                DeptMap.put(String.valueOf(oper.getOperId()), treenode);
            }
            trRoot = TreeHelper.makeTree(OperList, DeptMap, "-1");
            rootList.add(trRoot);
        }
        catch (Exception e) {
            log.debug("error: " + e.toString());
            throw e;
        }
        return rootList;
    }

    //取得所有的系统操作列表
    public List getAllOper(Connection oConn) throws Exception {

        allOperList = new ArrayList();
        DbAccess oDba = new DbAccess();
        String sql =
            "select * from user_oper where del_flag='0' order by layer_rank,parent_oper_id,layer_seq";
        ResultSet rs = oDba.query(oConn, sql);
        int rowscount = 0;
        try {
            while (rs.next()) {
                rowscount++;
                F180_OperMgt EntityData = new F180_OperMgt();
                EntityData.setOper_id(rs.getString("oper_id"));
                EntityData.setOper_name(rs.getString("oper_name"));
                EntityData.setParent_oper_id(rs.getString("parent_oper_id").
                                             trim());
                EntityData.setOper_type(rs.getString("oper_type"));
                EntityData.setLayer_rank(rs.getInt("layer_rank"));
                EntityData.setLayer_seq(rs.getInt("layer_seq"));
                allOperList.add(EntityData);
            }
            rs.close();
            log.debug("select record:" + rowscount);
        }
        catch (Exception e) {
            allOperList = null;
            log.error("get_set:" + e.getMessage());
            throw e;
        }
        return allOperList;

    }

    //按系统操作流水号在系统操作表中查询系统操作name
    public String getOperName(Connection oConn, String oper_id) throws
        Exception {

        int iCount = 0;
        String sOperName = "";
        String sql = "select oper_name from user_oper where oper_id = '" +
            oper_id + "'";

        DbAccess oDba = new DbAccess();
        ResultSet rs = oDba.query(oConn, sql);

        try {
            while (rs.next()) {
                sOperName = ComString.nvl(rs.getString(1)).trim();
                iCount++;
            }
        }
        catch (Exception e) {
            log.debug("error:" + e.toString());
            throw e;
        }

        return sOperName;
    }

}
