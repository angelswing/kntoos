package com.conant.ums.lbean;

import java.sql.*;
import java.util.*;

import com.conant.ums.data.F110_RoleMgt;
import com.conant.ums.db.DbAccess;
import com.conant.ums.util.*;

public class F110_RoleMgtLBean
    extends BaseLBean {
    String datetime = new SysTime().getSysTimeSemicolon();

    public F110_RoleMgtLBean() {
    }

    //�ж���������¼�����Ǹ��¼�¼
    public int insert(Connection oConn, F110_RoleMgt prm_Data) throws Exception {

        int iRet = 0;

        if (ComString.nvl(prm_Data.getRole_id()).trim().equals("") == true) {
            iRet = insertData(oConn, prm_Data);
        }
        else {
            iRet = updateData(oConn, prm_Data);
        }

        return iRet;
    }

    //���½�ɫ��Ϣ���еļ�¼��Ϣ
    public int updateData(Connection oConn, F110_RoleMgt prm_Data) throws
        Exception {

        int iRet = 0;

        String sql = "update user_role set role_name = '" +
            BytesConverter.asc2gb(prm_Data.getRole_name()) + "', "
            + "role_depict = '" +
            BytesConverter.asc2gb(prm_Data.getRole_depict()) +
            "', upd_date = '" + datetime + "' where role_id = '" +
            prm_Data.getRole_id() + "'";

        log.debug("update sql :" + sql);

        DbAccess oDba = new DbAccess();
        iRet = oDba.execute(oConn, sql);

        return iRet;
    }

    //���ɫ��Ϣ���в����¼��Ϣ
    public int insertData(Connection oConn, F110_RoleMgt prm_Data) throws
        Exception {

        int iRet = 0;

        String sql = "insert into user_role ( role_id, role_name, role_depict, del_flag, add_date, add_userid )"
            + " values ( ?,?,?,?,?,? )";
        log.debug("insert sql is : " + sql);

        String MaxRoleid = getMaxRoleid(oConn);

        try {
            PreparedStatement pstmt = oConn.prepareStatement(sql);
            pstmt.setString(1, MaxRoleid);
            pstmt.setString(2, BytesConverter.asc2gb(prm_Data.getRole_name()));
            pstmt.setString(3, BytesConverter.asc2gb(prm_Data.getRole_depict()));
            pstmt.setString(4, "0");
            pstmt.setString(5, datetime);
            pstmt.setString(6, prm_Data.getAdd_userid());

            iRet = pstmt.executeUpdate();
        }
        catch (Exception e) {
            log.debug("error:" + e.toString());
            throw e;
        }

        return iRet;
    }

    //�õ���ɫ����������ˮ�ţ����ң�1
    private String getMaxRoleid(Connection oConn) throws Exception {

        String iCount = "0";
        String sRoleId = "0000000001";
        DbAccess oDba = new DbAccess();

        String sql = "select count(*) from user_role";

        ResultSet rs = oDba.query(oConn, sql);
        while (rs.next()) {
            iCount = rs.getString(1);
        }
        if (iCount.equals("0")) {
            return sRoleId;
        }
        else {

            sql = "select max(role_id) + 1 from user_role";

            rs = oDba.query(oConn, sql);

            try {
                while (rs.next()) {
                    sRoleId = ComString.zeroFormat(rs.getString(1), 10);
                }
            }
            catch (Exception e) {
                log.debug("error:" + e.toString());
                throw e;
            }

        }
        return sRoleId;
    }

    //��ѯ��ɫ��Ϣ
    public List SelectData(Connection oConn, F110_RoleMgt prm_Data) throws
        Exception {

        int iCount = 0;
        ArrayList alResult = new ArrayList();

        String sql = "select * from user_role ";
        String whCondition = "where del_flag = '0' ";

        if (ComString.nvl(prm_Data.getRole_id()).trim().equals("") == false) {
            whCondition += "and role_id = '" +
                ComString.nvl(prm_Data.getRole_id()).trim() + "' ";
        }
        if (ComString.nvl(prm_Data.getRole_name()).trim().equals("") == false) {
            whCondition += "and role_name like '%" +
                BytesConverter.asc2gb(prm_Data.getRole_name()).trim() + "%' ";
        }
        if (ComString.nvl(prm_Data.getRole_depict()).trim().equals("") == false) {
            whCondition += "and role_depict like '%" +
                BytesConverter.asc2gb(prm_Data.getRole_depict()).trim() + "%' ";
        }

        sql += whCondition + "order by role_id desc";
        log.debug("select sql : " + sql);
        DbAccess oDba = new DbAccess();
        ResultSet rs = oDba.query(oConn, sql);
        try {
            while (rs.next()) {
                F110_RoleMgt F110_RoleMgt = new F110_RoleMgt();
                F110_RoleMgt.setRole_id(ComString.nvl(rs.getString("role_id")).
                                        trim());
                F110_RoleMgt.setRole_name(ComString.nvl(rs.getString(
                    "role_name")).trim());
                F110_RoleMgt.setRole_depict(ComString.nvl(rs.getString(
                    "role_depict")).trim());
                F110_RoleMgt.setAdd_date(ComString.nvl(rs.getString("add_date")).
                                         trim());
                alResult.add(F110_RoleMgt);
                iCount++;
            }
        }
        catch (Exception e) {
            log.debug("error: " + e.toString());
            throw e;
        }

        return alResult;
    }

    //�õ���ɫ�б�
    public List getRoleListOptions(Connection oConn) throws Exception {

        int iCount = 0;
        ArrayList role_list = new ArrayList();
        DbAccess oDba = new DbAccess();
        String sql =
            "select * from user_role where del_flag = '0' order by role_id";

        ResultSet rs = oDba.query(oConn, sql);

        try {
            while (rs.next()) {
                F110_RoleMgt role = new F110_RoleMgt();
                role.setRole_id(ComString.nvl(rs.getString("role_id")).trim());
                role.setRole_name(ComString.nvl(rs.getString("role_name")).
                                  trim());
                role_list.add(role);
                iCount++;
            }
        }
        catch (Exception e) {
            log.error("error:" + e.toString());
        }

        return role_list;
    }

    //ͨ����ɫ��ˮ�����õ���ɫ����
    public String getRoleName(Connection oConn, String role_id) throws
        Exception {

        int iCount = 0;
        String sRoleName = "";
        String sql = "select role_name from user_role where role_id = '" +
            role_id + "'";

        DbAccess oDba = new DbAccess();
        ResultSet rs = oDba.query(oConn, sql);

        try {
            while (rs.next()) {
                sRoleName = ComString.nvl(rs.getString(1)).trim();
                iCount++;
            }
        }
        catch (Exception e) {
            log.debug("error:" + e.toString());
            throw e;
        }

        return sRoleName;
    }

    //����ɫ����ĳ����ɫ���Ƿ����û�
    public boolean checkRoleUser(Connection oConn, String role_id) throws
        Exception {
        boolean delRole = true;

        String sql = "select user_id from user_role_pk where role_id = '" +
            role_id + "'";

        DbAccess oDba = new DbAccess();
        ResultSet rs = oDba.query(oConn, sql);

        try {
            while (rs.next()) {
                delRole = false;
            }
        }
        catch (Exception e) {
            log.debug("error:" + e.toString());
            throw e;
        }

        return delRole;
    }

    //����ɫ����ĳ����ɫ���Ƿ��в���
    public boolean checkRoleDept(Connection oConn, String role_id) throws
        Exception {
        boolean delRole = true;

        String sql = "select dept_id from dept_role_pk where role_id = '" +
            role_id + "'";

        DbAccess oDba = new DbAccess();
        ResultSet rs = oDba.query(oConn, sql);

        try {
            while (rs.next()) {
                delRole = false;
            }
        }
        catch (Exception e) {
            log.debug("error:" + e.toString());
            throw e;
        }

        return delRole;
    }

    //�ڽ�ɫ���г���ɾ����ɫ
    public int quiteDelete(Connection oConn, String role_id) throws
        Exception {

        int iCount = 0;

        String sql = "delete from user_role where role_id = ? ";
        log.debug("delete sql is : " + sql);

        try {
            PreparedStatement pstmt = oConn.prepareStatement(sql);
            int temp = 1;
            pstmt.setString(temp++, role_id);
            iCount = pstmt.executeUpdate();
            pstmt.close();
        }
        catch (Exception e) {
            log.debug("error:" + e.toString());
            throw e;
        }

        return iCount;
    }

    //�жϽ�ɫ�Ƿ��ظ�
    public boolean isExist(Connection oConn, String roleName) throws
        Exception {

        boolean exist_flag = false;
        String tablename = "user_role";
        String whcondition = "role_name = '" + BytesConverter.asc2gb(roleName) +
            "'";
        String sql = "select * from " + tablename + " where " + whcondition;

        try {
            PreparedStatement pstmt = oConn.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet ret = pstmt.executeQuery();
            if (ret.next()) {
                exist_flag = true;
            }
            pstmt.close();
        }
        catch (Exception e) {
            log.debug("error: " + e.toString());
            throw e;
        }

        return exist_flag;
    }

    //�жϽ�ɫ�޸�ʱ�����ظ�����
    public boolean isUpdateExist(Connection oConn, String role_id,
                                 String roleName) throws
        Exception {

        boolean exist_flag = false;
        String tablename = "user_role";
        String whcondition = "role_id != '" + role_id + "' and role_name = '" +
            BytesConverter.asc2gb(roleName) +
            "'";
        String sql = "select * from " + tablename + " where " + whcondition;

        try {
            PreparedStatement pstmt = oConn.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet ret = pstmt.executeQuery();
            if (ret.next()) {
                exist_flag = true;
            }
            pstmt.close();
        }
        catch (Exception e) {
            log.debug("error: " + e.toString());
            throw e;
        }

        return exist_flag;
    }

    //��һЩ��ɫ��Ȩ�޸���ĳ����ɫ
    public int updateParentRole(Connection oConn, String role_id, String[] parentRoleGroup) throws
        Exception {

        int iCount = 0;

        String sql = "insert into test (id,content) select 3,content from test where id='2'";

        return iCount;
    }

}
