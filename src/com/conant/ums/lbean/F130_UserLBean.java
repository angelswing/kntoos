package com.conant.ums.lbean;

import java.sql.*;
import java.util.*;

import com.conant.ums.data.F130_UserMgt;
import com.conant.ums.db.DbAccess;
import com.conant.ums.util.*;


public class F130_UserLBean
    extends BaseLBean {

    String datetime = new SysTime().getSysTimeSemicolon();

    public F130_UserLBean() {
    }

    //新增用户
    public int insertData(Connection oConn, F130_UserMgt userData) throws
        Exception {

        int iCount = 0;

        String sTableName = "user_user";
        String sFieldName = " (user_id, user_tag, user_name, user_passwd, email, address, home_tel, mobile, add_date, add_userid)";
        String sValue = "(?,?,?,?,?,?,?,?,?,?)";
        String sql = "insert into " + sTableName + sFieldName + " values " +
            sValue;

        log.debug("insert sql is : " + sql);

        try {
            PreparedStatement pstmt = oConn.prepareStatement(sql);
            this.setUserData(pstmt, userData, true);

            iCount = pstmt.executeUpdate();
            pstmt.close();
        }
        catch (Exception e) {
            log.debug("error:" + e.toString());
            throw e;
        }

        return iCount;
    }

    //更新用户信息 *这里密码先看是否为空来判断是否修改，而后在存入数据库前加密
    public int updateData(Connection oConn, F130_UserMgt userData) throws
        Exception {

        int iCount = 0;

        String sql = "update user_user set user_name = ?, ";
        if (userData.getUser_passwd().equals("") == false) {
            sql = sql + "user_passwd = ?, ";
        }
        sql = sql +
            "email = ?, address = ?, home_tel = ?, mobile = ?, upd_date = ?, upd_userid = ? where user_id = ? ";
        log.debug("update sql is : " + sql);

        try {
            PreparedStatement pstmt = oConn.prepareStatement(sql);
            this.setUserData(pstmt, userData, false);
            iCount = pstmt.executeUpdate();
            pstmt.close();
        }
        catch (Exception e) {
            log.debug("error:" + e.toString());
            throw e;
        }
        return iCount;
    }

    //彻底删除用户，并将用户部门、用户角色中的关系记录清空
    public int deleteData(Connection oConn, String sUser_id) throws Exception {
        int iCount = 0;

        String sql = "delete from user_user where user_id = ? ";
        log.debug("delect sql is : " + sql);
        try {
            PreparedStatement pstmt = oConn.prepareStatement(sql);
            int temp = 1;
            pstmt.setString(temp++, sUser_id);
            iCount = pstmt.executeUpdate();
            pstmt.close();
        }
        catch (Exception e) {
            log.debug("error:" + e.toString());
            throw e;
        }

        sql = "delete from user_role_pk where user_id = ? ";
        log.debug("delete sql is : " + sql);
        try {
            PreparedStatement pstmt0 = oConn.prepareStatement(sql);
            int temp0 = 1;
            pstmt0.setString(temp0++, sUser_id);
            iCount = pstmt0.executeUpdate();
            pstmt0.close();
        }
        catch (Exception e) {
            log.debug("error:" + e.toString());
            throw e;
        }

        sql = "delete from user_dept_pk where user_id = ? ";
        log.debug("delete sql is : " + sql);
        try {
            PreparedStatement pstmt2 = oConn.prepareStatement(sql);
            int temp2 = 1;
            pstmt2.setString(temp2++, sUser_id);
            iCount = pstmt2.executeUpdate();
            pstmt2.close();
        }
        catch (Exception e) {
            log.debug("error:" + e.toString());
            throw e;
        }

        return iCount;
    }

    //向用户角色关系表更新用户所属角色
    public int updateRole(Connection oConn, String user_id, String[] role) throws
        Exception {

        int iCount = 0;

        String sql = "delete from user_role_pk where user_id = ? ";
        log.debug("delete sql is : " + sql);

        try {
            PreparedStatement pstmt = oConn.prepareStatement(sql);
            int temp = 1;
            pstmt.setString(temp++, user_id);
            iCount = pstmt.executeUpdate();
            pstmt.close();
        }
        catch (Exception e) {
            log.debug("error:" + e.toString());
            throw e;
        }

        if (role != null) {
            try {
                iCount = insertRole(oConn, user_id, role);
            }
            catch (Exception e) {
                log.debug("error:" + e.toString());
                throw e;
            }
        }
        return iCount;
    }

    //向用户角色关系表增加用户所属角色
    public int insertRole(Connection oConn, String user_id, String[] role) throws
        Exception {

        int iCount = 0;

        int roleSize = role.length;
        for (int i = 0; i < roleSize; i++) {
            String sql =
                "insert into user_role_pk ( user_id, role_id, add_date ) "
                + " values ( ?,?,? )";
            log.debug("insert sql is : " + sql);
            try {
                PreparedStatement pstmt = oConn.prepareStatement(sql);
                int temp0 = 1;
                pstmt.setString(temp0++, user_id);
                pstmt.setString(temp0++, role[i]);
                pstmt.setString(temp0++, datetime);
                iCount = pstmt.executeUpdate();
                pstmt.close();
            }
            catch (Exception e) {
                log.debug("error:" + e.toString());
                throw e;
            }
        }

        return iCount;
    }

    //向用户部门关系表更新用户所属部门
    public int updateDept(Connection oConn, F130_UserMgt userData) throws
        Exception {

        int iCount = 0;

        String sql = "delete from user_dept_pk where user_id = ? ";
        log.debug("delete sql is : " + sql);

        try {
            PreparedStatement pstmt = oConn.prepareStatement(sql);
            int temp = 1;
            pstmt.setString(temp++, userData.getUser_id());
            iCount = pstmt.executeUpdate();
            pstmt.close();
        }
        catch (Exception e) {
            log.debug("error:" + e.toString());
            throw e;
        }

        if (!userData.getDeptid().equals("")) {
            sql = "insert into user_dept_pk ( user_id, dept_id, add_date ) "
                + " values ( ?,?,? )";
            log.debug("insert sql is : " + sql);
            try {
                PreparedStatement pstmt0 = oConn.prepareStatement(sql);
                int temp0 = 1;
                pstmt0.setString(temp0++, userData.getUser_id());
                pstmt0.setString(temp0++, userData.getDeptid());
                pstmt0.setString(temp0++, datetime);
                iCount = pstmt0.executeUpdate();
                pstmt0.close();
            }
            catch (Exception e) {
                log.debug("error:" + e.toString());
                throw e;
            }
        }
        return iCount;
    }

    //在用户表中查询用户的详细信息
    public List selectRecord(Connection oConn, F130_UserMgt prm_Data,
                             String orderbyName, String upordown) throws
        Exception {

        int iCount = 0;
        String deptNumb = "";

        ArrayList alResult = new ArrayList();

        String sql = "select * from user_user ";
        String whCondition = "where is_admin = '0' ";

        if (ComString.nvl(prm_Data.getUser_id()).trim().equals("") == false) {
            whCondition += "and user_id = '" +
                ComString.nvl(prm_Data.getUser_id()).trim() + "' ";
            deptNumb = userDeptPK(oConn, prm_Data.getUser_id().trim());
        }
        if (ComString.nvl(prm_Data.getUser_name()).trim().equals("") == false) {
            whCondition += "and user_name like '%" +
                prm_Data.getUser_name().trim() + "%' ";
        }
        if (ComString.nvl(prm_Data.getUser_tag()).trim().equals("") == false) {
            whCondition += "and user_tag like '%" +
                prm_Data.getUser_tag().trim() + "%' ";
        }

        String orderCond = "";
        if (orderbyName != null && orderbyName.equals("") == false) {
            orderCond = " order by " + orderbyName;
            if (upordown != null && upordown.equals("1")) {
                orderCond += " desc ";
            }
        }

        sql += whCondition + orderCond;
        log.debug("sql is " + sql);
        DbAccess oDba = new DbAccess();
        ResultSet rs = oDba.query(oConn, sql);

        try {
            while (rs.next()) {
                F130_UserMgt f130_UserMgt = new F130_UserMgt();
                f130_UserMgt.setUser_id(rs.getString("user_id").trim());
                f130_UserMgt.setUser_tag(rs.getString("user_tag").trim());
                f130_UserMgt.setUser_name(rs.getString("user_name").trim());
                f130_UserMgt.setEmail(ComString.nvl(rs.getString("email")));
                f130_UserMgt.setAddress(ComString.nvl(rs.getString("address")));
                f130_UserMgt.setHome_tel(ComString.nvl(rs.getString("home_tel")));
                f130_UserMgt.setMobile(ComString.nvl(rs.getString("mobile")));
                f130_UserMgt.setAdd_date(rs.getString("add_date"));
                f130_UserMgt.setDeptid(deptNumb);
                f130_UserMgt.setDept_name(userDeptName(oConn, f130_UserMgt.getUser_id()));

                if(rs.getString("lock_flag").trim().equals("0")){
                    f130_UserMgt.setLock_flag(ComGlobal.LOCK_No);
                }else{
                    f130_UserMgt.setLock_flag(ComGlobal.LOCK_Yes);
                }

                if (F160_UserOnlLBean.isexist(rs.getString("user_tag").trim())) {
                    f130_UserMgt.setIs_login(ComGlobal.LOGIN_Yes);
                }
                else {
                    f130_UserMgt.setIs_login(ComGlobal.LOGIN_No);
                }

                alResult.add(f130_UserMgt);
                iCount++;
            }
        }
        catch (Exception e) {
            log.debug("error: " + e.toString());
            throw e;
        }

        return alResult;
    }

    public List selectRecordSelect(Connection oConn, F130_UserMgt prm_Data) throws
        Exception {

        int iCount = 0;

        ArrayList alResult = new ArrayList();

        String sql = "select a.* from user_user a, user_dept_pk b ";
        String whCondition = "where a.is_admin = '0' ";

        if (ComString.nvl(prm_Data.getUser_name()).trim().equals("") == false) {
            whCondition += "and a.user_name like '%" +
                prm_Data.getUser_name().trim() + "%' ";
        }
        if (ComString.nvl(prm_Data.getUser_tag()).trim().equals("") == false) {
            whCondition += "and a.user_tag like '%" +
                prm_Data.getUser_tag().trim() + "%' ";
        }

        whCondition += "and a.user_id = b.user_id and b.dept_id = '" + prm_Data.getDeptid() + "' order by a.add_date desc";

        sql += whCondition;
        log.debug("sql is " + sql);
        DbAccess oDba = new DbAccess();
        ResultSet rs = oDba.query(oConn, sql);

        try {
            while (rs.next()) {
                F130_UserMgt f130_UserMgt = new F130_UserMgt();
                f130_UserMgt.setUser_id(rs.getString("user_id").trim());
                f130_UserMgt.setUser_tag(rs.getString("user_tag").trim());
                f130_UserMgt.setUser_name(rs.getString("user_name").trim());
                f130_UserMgt.setAdd_date(rs.getString("add_date"));
                f130_UserMgt.setDept_name(userDeptName(oConn, f130_UserMgt.getUser_id()));

                if(rs.getString("lock_flag").trim().equals("0")){
                    f130_UserMgt.setLock_flag(ComGlobal.LOCK_No);
                }else{
                    f130_UserMgt.setLock_flag(ComGlobal.LOCK_Yes);
                }

                if (F160_UserOnlLBean.isexist(rs.getString("user_tag").trim())) {
                    f130_UserMgt.setIs_login(ComGlobal.LOGIN_Yes);
                }
                else {
                    f130_UserMgt.setIs_login(ComGlobal.LOGIN_No);
                }

                alResult.add(f130_UserMgt);
                iCount++;
            }
        }
        catch (Exception e) {
            log.debug("error: " + e.toString());
            throw e;
        }

        return alResult;
    }

    //得到用户所属的部门 @raokun 2006-08-09
    public String userDeptPK(Connection oConn, String sUser_id) throws
        Exception {

        int iCount = 0;
        String sRoleId = "";
        String sql = "select dept_id from user_dept_pk where user_id = '" +
            sUser_id + "' and del_flag = '0'";

        DbAccess oDba = new DbAccess();
        ResultSet rs = oDba.query(oConn, sql);

        try {
            while (rs.next()) {
                sRoleId = rs.getString(1).trim();
                iCount++;
            }
        }
        catch (Exception e) {
            log.debug("error:" + e.toString());
            throw e;
        }

        return sRoleId;
    }

    //得到用户所属的部门名称 @raokun 2007-09-03
    public String userDeptName(Connection oConn, String sUser_id) throws
        Exception {

        int iCount = 0;
        String sRoleId = "";
        String sql = "select a.dept_name from user_dept a, user_dept_pk b where b.user_id = '" +
            sUser_id + "' and a.dept_id = b.dept_id";

        DbAccess oDba = new DbAccess();
        ResultSet rs = oDba.query(oConn, sql);

        try {
            while (rs.next()) {
                sRoleId = rs.getString(1).trim();
                iCount++;
            }
        }
        catch (Exception e) {
            log.debug("error:" + e.toString());
            throw e;
        }

        return sRoleId;
    }

    //判断用户帐号是否重复
    public boolean isExist(Connection oConn, String userTag) throws
        Exception {

        boolean exist_flag = false;
        String tablename = "user_user";
        String whcondition = "user_tag = '" + BytesConverter.asc2gb(userTag) +
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

    //负责列出要新增、更新的数据，使代码更加简洁
    public void setUserData(PreparedStatement pstmt,
                            F130_UserMgt userData,
                            boolean isInsert) throws Exception {
        int temp = 1;
        if (isInsert) {
            pstmt.setString(temp++, userData.getUser_id());
            pstmt.setString(temp++, BytesConverter.asc2gb(userData.getUser_tag()));
        }

        pstmt.setString(temp++, BytesConverter.asc2gb(userData.getUser_name()));

        if (userData.getUser_passwd().equals("") == false) {
            //pstmt.setString(temp++, ComString.MD5Encode(userData.getUser_passwd()));
            pstmt.setString(temp++, Decode.encrypt(userData.getUser_passwd()));
        }

        pstmt.setString(temp++, userData.getEmail());
        pstmt.setString(temp++, BytesConverter.asc2gb(userData.getAddress()));
        pstmt.setString(temp++, userData.getHome_tel());
        pstmt.setString(temp++, userData.getMobile());

        if (isInsert) {
            pstmt.setString(temp++, datetime);
            pstmt.setString(temp++, userData.getAdd_userid());
        }
        else {
            pstmt.setString(temp++, datetime);
            pstmt.setString(temp++, userData.getUpd_userid());
        }

        if (!isInsert) {
            pstmt.setString(temp++, userData.getUser_id());
        }

    }

    //得到用户表中最大的流水号，并且＋1
    public String getMaxUserid(Connection oConn) throws Exception {

        int iCount = 0;
        String sUserId = "01";
        String sql = "select max(user_id) + 1 from user_user";

        DbAccess oDba = new DbAccess();
        ResultSet rs = oDba.query(oConn, sql);

        try {
            while (rs.next()) {
                sUserId = ComString.zeroFormat(rs.getString(1), 15);
                iCount++;
            }
        }
        catch (Exception e) {
            log.debug("error:" + e.toString());
            throw e;
        }

        return sUserId;
    }

    //得到部门所属的角色列表
    public List getDeptRoleListOptions(Connection oConn, String sDept_id) throws
        Exception {

        int iCount = 0;
        ArrayList dept_role_list = new ArrayList();
        DbAccess oDba = new DbAccess();
        String sql =
            "select role_id from dept_role_pk where del_flag = '0' and dept_id = '" +
            sDept_id + "' order by role_id";

        ResultSet rs = oDba.query(oConn, sql);

        try {
            while (rs.next()) {
                F130_UserMgt dept_role_t = new F130_UserMgt();
                String sRole_id = ComString.nvl(rs.getString("role_id")).trim();
                dept_role_t.setRole_id(sRole_id);

                F110_RoleMgtLBean roleBean = new F110_RoleMgtLBean();
                dept_role_t.setRole_name(roleBean.getRoleName(oConn,
                    sRole_id));
                dept_role_list.add(dept_role_t);
                iCount++;
            }
        }
        catch (Exception e) {
            log.error("error:" + e.toString());
        }

        return dept_role_list;
    }

    //得到用户所属的角色列表
    public List getUserRoleListOptions(Connection oConn, String sUser_id) throws
        Exception {

        int iCount = 0;
        ArrayList user_role_list = new ArrayList();
        DbAccess oDba = new DbAccess();
        String sql =
            "select role_id from user_role_pk where user_id = '" +
            sUser_id + "' order by role_id";

        ResultSet rs = oDba.query(oConn, sql);

        try {
            while (rs.next()) {
                F130_UserMgt user_role_t = new F130_UserMgt();
                String sRole_id = ComString.nvl(rs.getString("role_id")).trim();
                user_role_t.setUser_role_id(sRole_id);

                F110_RoleMgtLBean roleBean = new F110_RoleMgtLBean();
                user_role_t.setUser_role_name(roleBean.getRoleName(oConn,
                    sRole_id));
                user_role_list.add(user_role_t);
                iCount++;
            }
        }
        catch (Exception e) {
            log.error("error:" + e.toString());
        }

        return user_role_list;
    }

    //修改密码
    public int changePass( Connection oConn, F130_UserMgt prm_Data ) throws Exception {

        int iCount = 0;

        String sql = " update user_user set user_passwd = ? where user_tag = ? ";

        log.debug( "update sql is : " + sql );

        try {
            PreparedStatement pstmt = oConn.prepareStatement( sql );
            int temp = 1;

            pstmt.setString(temp++, prm_Data.getUser_passwd());
            pstmt.setString( temp++, prm_Data.getUser_tag() );

            iCount = pstmt.executeUpdate();
            pstmt.close();
        } catch ( Exception e ){
            log.debug( "error:" + e.toString() );
            throw e;
        }
        return iCount;
     }

}
