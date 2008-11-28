package com.conant.ums.lbean;

import java.sql.*;
import java.util.*;

import com.conant.ums.data.*;
import com.conant.ums.db.DbAccess;
import com.conant.ums.interfaces.*;
import com.conant.ums.util.*;
import com.conant.ums.util.tree.*;

public class F120_PurvMgtLBean
    extends BaseLBean {

    public F120_PurvMgtLBean() {
    }

    //��ѯϵͳ������
    public List SelectOper() throws Exception {
        List rootList = new ArrayList();
        TreeNode trRoot = null;
        List DataList = new ArrayList();
        Map DeptMap = new HashMap();

        ExternResource externResource = (ExternResource)Class.forName(ComGlobal.OUTRESOURCE).newInstance();
        List operList = externResource.getAllOperation();

        try {
            for (int i = 0; i < operList.size(); i++) {
                OperationInfo data = (OperationInfo) operList.get(i);
                TreeNode treenode = new TreeNode(String.valueOf(data.getOperId()),
                                                 String.valueOf(data.getParentId()),
                                                 data.getOperName());
                DataList.add(treenode);
                DeptMap.put(String.valueOf(data.getOperId()), treenode);
            }
            trRoot = TreeHelper.makeTree(DataList, DeptMap, "-1");
            rootList.add(trRoot);
        }
        catch (Exception e) {
            log.debug("error: " + e.toString());
            throw e;
        }

        return rootList;
    }

    //��ѯҵ��ʵ����
    public List SelectEnti() throws Exception {
        List rootList = new ArrayList();
        TreeNode trRoot = null;
        List DataList = new ArrayList();
        Map DeptMap = new HashMap();

        ExternResource externResource = (ExternResource)Class.forName(ComGlobal.OUTRESOURCE).newInstance();
        List entiList = externResource.getAllEntity();

        for (int i = 0; i < entiList.size(); i++) {
            EntityInfo data = (EntityInfo) entiList.get(i);
            TreeNode treenode = new TreeNode(String.valueOf(data.getEntityId()),
                                             String.valueOf(data.getParentId()),
                                             data.getEntityName());
            DataList.add(treenode);
            DeptMap.put(String.valueOf(data.getEntityId()), treenode);
        }
        trRoot = TreeHelper.makeTree(DataList, DeptMap, "-1");
        rootList.add(trRoot);


        return rootList;
    }

    //����ɫϵͳ�������еļ�¼��ʾ������ѡ���ϵͳ������
    public List SelectOperS(Connection oConn, String role_id) throws Exception {
        List rootList = new ArrayList();
        TreeNode trRoot = null;
        List OperList = new ArrayList();
        Map DeptMap = new HashMap();
        boolean selected = false;

        ExternResource externResource = (ExternResource)Class.forName(ComGlobal.OUTRESOURCE).newInstance();
        List operList = externResource.getAllOperation();

        try {
            for (int i = 0; i < operList.size(); i++) {
                OperationInfo oper = (OperationInfo) operList.get(i);
                selected = isOperSelected(oConn, role_id, String.valueOf(oper.getOperId()));
                TreeNode treenode = new TreeNode(String.valueOf(oper.getOperId()),
                                                 String.valueOf(oper.getParentId()),
                                                 oper.getOperName(), selected);
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

    //����ɫϵͳ�������еļ�¼��ʾ������ѡ���ϵͳ������
    public List SelectEntiS(Connection oConn, String role_id) throws Exception {
        List rootList = new ArrayList();
        TreeNode trRoot = null;
        List EntiList = new ArrayList();
        Map DeptMap = new HashMap();
        boolean selected = false;

        ExternResource externResource = (ExternResource)Class.forName(ComGlobal.OUTRESOURCE).newInstance();
        List entiList = externResource.getAllEntity();

        try {
            for (int i = 0; i < entiList.size(); i++) {
                EntityInfo enti = (EntityInfo) entiList.get(i);
                selected = isEntiSelected(oConn, role_id, String.valueOf(enti.getEntityId()));
                TreeNode treenode = new TreeNode(String.valueOf(enti.getEntityId()),
                                                 String.valueOf(enti.getParentId()),
                                                 enti.getEntityName(), selected);
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

    //���ϵͳ��������Ҷ�ĸ�ѡ���Ƿ��
    public boolean isOperSelected(Connection oConn, String role_id,
                                  String oper_id) throws
        Exception {
        boolean selected = false;
        DbAccess oDba = new DbAccess();
        String sql = "select oper_id from role_oper_pk where role_id = '" +
            role_id + "'";
        ResultSet rs = oDba.query(oConn, sql);
        int rowscount = 0;
        try {
            while (rs.next()) {
                if (rs.getString("oper_id").trim().equals(oper_id.trim())) {
                    return true;
                }
                rowscount++;
            }
            rs.close();
            log.debug("select record:" + rowscount);
        }
        catch (Exception e) {
            log.error("get_set:" + e.getMessage());
            throw e;
        }
        return selected;
    }

    //����ɫ��ˮ���ڽ�ɫϵͳ�������в�ѯϵͳ������id��name
    public List getOperListOptions(Connection oConn, String role_id) throws
        Exception {

        int iCount = 0;
        ArrayList operList = new ArrayList();
        DbAccess oDba = new DbAccess();
        String sql = "select oper_id from role_oper_pk where role_id = '" +
            role_id + "' order by oper_id";

        ResultSet rs = oDba.query(oConn, sql);

        try {
            while (rs.next()) {
                F180_OperMgt oper_t = new F180_OperMgt();
                String oper_id = ComString.nvl(rs.getString("oper_id")).trim();
                oper_t.setOper_id(oper_id);

                F180_OperMgtLBean operLBean = new F180_OperMgtLBean();
                oper_t.setOper_name(operLBean.getOperName(oConn, oper_id));

                operList.add(oper_t);
                iCount++;
            }
        }
        catch (Exception e) {
            log.error("error:" + e.toString());
        }

        return operList;
    }

    //���ҵ��ʵ������Ҷ�ĸ�ѡ���Ƿ��
    public boolean isEntiSelected(Connection oConn, String role_id,
                                  String enti_id) throws
        Exception {
        boolean selected = false;
        DbAccess oDba = new DbAccess();
        String sql = "select enti_id from role_enti_pk where role_id = '" +
            role_id + "'";
        ResultSet rs = oDba.query(oConn, sql);
        int rowscount = 0;
        try {
            while (rs.next()) {
                if (rs.getString("enti_id").trim().equals(enti_id.trim())) {
                    return true;
                }
                rowscount++;
            }
            rs.close();
            log.debug("select record:" + rowscount);
        }
        catch (Exception e) {
            log.error("get_set:" + e.getMessage());
            throw e;
        }
        return selected;
    }

    //����ɫ��ˮ���ڽ�ɫҵ��ʵ����в�ѯҵ��ʵ���id��name
    public List getEntiListOptions(Connection oConn, String role_id) throws
        Exception {

        int iCount = 0;
        ArrayList entiList = new ArrayList();
        DbAccess oDba = new DbAccess();
        String sql = "select enti_id from role_enti_pk where role_id = '" +
            role_id + "' order by enti_id";

        ResultSet rs = oDba.query(oConn, sql);

        try {
            while (rs.next()) {
                F170_EntityMgt enti_t = new F170_EntityMgt();
                String enti_id = ComString.nvl(rs.getString("enti_id")).trim();
                enti_t.setEnti_id(enti_id);

                F170_EntityMgtLBean operLBean = new F170_EntityMgtLBean();
                enti_t.setEnti_name(operLBean.getEntiName(oConn, enti_id));

                entiList.add(enti_t);
                iCount++;
            }
        }
        catch (Exception e) {
            log.error("error:" + e.toString());
        }

        return entiList;
    }

    //ɾ����ɫϵͳ�������еļ�¼
    public int deleteRoleOper(Connection oConn, String role_id) throws
        Exception {
        int iCount = 0;

        String sql = " delete from role_oper_pk where role_id = '" + role_id +
            "'";
        log.debug("delect sql is : " + sql);

        try {
            PreparedStatement pstmt = oConn.prepareStatement(sql);
            iCount = pstmt.executeUpdate(sql);
            pstmt.close();
        }
        catch (Exception e) {
            log.debug("error:" + e.toString());
            throw e;
        }
        return iCount;
    }

    //���ɫϵͳ�������в����ϵ��¼
    public int insertRoleOper(Connection oConn, String role_id, String[] oper) throws
        Exception {

        int iCount = 0;

        String sql =
            "insert into role_oper_pk ( role_id, oper_id ) values ( ?,? )";
        log.debug("insert sql is : " + sql);

        int operSize = oper.length;

        for (int i = 0; i < operSize; i++) {

            try {
                PreparedStatement pstmt = oConn.prepareStatement(sql);
                pstmt.setString(1, role_id);
                pstmt.setString(2, oper[i]);

                iCount = pstmt.executeUpdate();
                oConn.commit();
            }
            catch (Exception e) {
                log.debug("error:" + e.toString());
                throw e;
            }
        }
        return iCount;
    }

    //ɾ����ɫϵͳ�������еļ�¼
    public int deleteRoleEnti(Connection oConn, String role_id) throws
        Exception {
        int iCount = 0;

        String sql = " delete from role_enti_pk where role_id = '" + role_id +
            "'";
        log.debug("delect sql is : " + sql);

        try {
            PreparedStatement pstmt = oConn.prepareStatement(sql);
            iCount = pstmt.executeUpdate(sql);
            pstmt.close();
        }
        catch (Exception e) {
            log.debug("error:" + e.toString());
            throw e;
        }
        return iCount;
    }

    //���ɫϵͳ�������в����ϵ��¼
    public int insertRoleEnti(Connection oConn, String role_id, String[] enti) throws
        Exception {

        int iCount = 0;

        String sql =
            "insert into role_enti_pk ( role_id, enti_id ) values ( ?,? )";
        log.debug("insert sql is : " + sql);

        int entiSize = enti.length;

        for (int i = 0; i < entiSize; i++) {

            try {
                PreparedStatement pstmt = oConn.prepareStatement(sql);
                pstmt.setString(1, role_id);
                pstmt.setString(2, enti[i]);

                iCount = pstmt.executeUpdate();
            }
            catch (Exception e) {
                log.debug("error:" + e.toString());
                throw e;
            }
        }
        return iCount;
    }

}
