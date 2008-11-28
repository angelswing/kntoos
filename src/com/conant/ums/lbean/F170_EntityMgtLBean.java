package com.conant.ums.lbean;

import java.sql.*;
import java.util.*;

import com.conant.ums.data.F170_EntityMgt;
import com.conant.ums.db.DbAccess;
import com.conant.ums.interfaces.*;
import com.conant.ums.util.*;
import com.conant.ums.util.tree.*;

public class F170_EntityMgtLBean
    extends BaseLBean {
    private static List allEntiList = null;

    public F170_EntityMgtLBean() {
    }

    //查询业务实体树
    public List SelectData() throws Exception {
        List rootList = new ArrayList();
        TreeNode trRoot = null;
        List EntiList = new ArrayList();
        Map DeptMap = new HashMap();

        ExternResource externResource = (ExternResource)Class.forName(ComGlobal.OUTRESOURCE).newInstance();
        List entiList = externResource.getAllEntity();

        try {
            for (int i = 0; i < entiList.size(); i++) {
                EntityInfo enti = (EntityInfo) entiList.get(i);
                TreeNode treenode = new TreeNode(String.valueOf(enti.getEntityId()),
                                                 String.valueOf(enti.getParentId()),
                                                 enti.getEntityName());
                EntiList.add(treenode);
                DeptMap.put(String.valueOf(enti.getEntityId()), treenode);
            }
            trRoot = TreeHelper.makeTree(EntiList, DeptMap, "-1");
            rootList.add(trRoot);
        }
        catch (Exception e) {
            log.debug("error: " + e.toString());
            throw e;
        }
        return rootList;
    }

    //取得所有的业务实体列表
    public List getAllEnti(Connection oConn) throws Exception {

        allEntiList = new ArrayList();
        DbAccess oDba = new DbAccess();
        String sql =
            "select * from user_enti where del_flag='0' order by layer_rank,parent_enti_id,layer_seq";
        ResultSet rs = oDba.query(oConn, sql);
        int rowscount = 0;
        try {
            while (rs.next()) {
                rowscount++;
                F170_EntityMgt EntityData = new F170_EntityMgt();
                EntityData.setEnti_id(rs.getString("enti_id"));
                EntityData.setEnti_name(rs.getString("enti_name"));
                EntityData.setParent_enti_id(rs.getString("parent_enti_id").
                                             trim());
                EntityData.setEnti_type(rs.getString("enti_type"));
                EntityData.setLayer_rank(rs.getInt("layer_rank"));
                EntityData.setLayer_seq(rs.getInt("layer_seq"));
                allEntiList.add(EntityData);
            }
            rs.close();
            log.debug("select record:" + rowscount);
        }
        catch (Exception e) {
            allEntiList = null;
            log.error("get_set:" + e.getMessage());
            throw e;
        }
        return allEntiList;

    }

    //按业务实体流水号在业务实体表中查询业务实体name
    public String getEntiName(Connection oConn, String enti_id) throws
        Exception {

        int iCount = 0;
        String sEntiName = "";
        String sql = "select enti_name from user_enti where enti_id = '" +
            enti_id + "'";

        DbAccess oDba = new DbAccess();
        ResultSet rs = oDba.query(oConn, sql);

        try {
            while (rs.next()) {
                sEntiName = ComString.nvl(rs.getString(1)).trim();
                iCount++;
            }
        }
        catch (Exception e) {
            log.debug("error:" + e.toString());
            throw e;
        }
        return sEntiName;
    }

}
