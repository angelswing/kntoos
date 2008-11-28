package com.conant.ums.lbean;

import java.sql.*;
import java.util.*;

import com.conant.ums.data.*;
import com.conant.ums.db.*;
import com.conant.ums.lbean.*;
import com.conant.ums.util.*;


public class F140_UserLimLBean
    extends BaseLBean {

    String datetime = new SysTime().getSysTimeSemicolon();

    public F140_UserLimLBean() {
    }

    //更新用户表中的时间和ip限制标识字段
    public int updateData(Connection oConn, F140_UserLim userData) throws
        Exception {

        int iCount = 0;

        String sql =
            "update user_user set limit_time = ?, limit_ip = ? where user_id = ?";
        log.debug("update sql is : " + sql);

        try {
            PreparedStatement pstmt = oConn.prepareStatement(sql);
            int temp = 1;
            pstmt.setString(temp++, userData.getTimetype());
            pstmt.setString(temp++, userData.getIptype());
            pstmt.setString(temp++, userData.getUser_id());
            iCount = pstmt.executeUpdate();
            pstmt.close();
        }
        catch (Exception e) {
            log.debug("error:" + e.toString());
            throw e;
        }
        return iCount;
    }

    //删除用户时间限制信息
    public int deleteTimeGroup(Connection oConn, String user_id) throws
        Exception {

        int iCount = 0;

        String sql = "delete from user_limit_time where user_id = ? ";
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

        return iCount;
    }

    //增加用户时间限制信息
    public int insertTimeGroup(Connection oConn, String user_id,
                               String[] timeGroup) throws
        Exception {

        int iCount = 0;

        int timeSize = timeGroup.length;
        for (int i = 0; i < timeSize; i++) {
            String sql =
                "insert into user_limit_time ( user_id, start_time, end_time, add_date ) "
                + " values ( ?,?,?,? )";
            log.debug("insert sql is : " + sql);
            try {
                PreparedStatement pstmt = oConn.prepareStatement(sql);
                int temp = 1;
                pstmt.setString(temp++, user_id);
                pstmt.setString(temp++, timeGroup[i].substring(0, 10));
                pstmt.setString(temp++, timeGroup[i].substring(11, 21));
                pstmt.setString(temp++, datetime);
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

    //删除用户ip限制信息
    public int deleteIpGroup(Connection oConn, String user_id) throws
        Exception {

        int iCount = 0;

        String sql = "delete from user_limit_ip where user_id = ? ";
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

        return iCount;
    }

    //增加用户ip限制信息
    public int insertIpGroup(Connection oConn, String user_id, String[] ipGroup) throws
        Exception {

        int iCount = 0;

        int ipSize = ipGroup.length;
        for (int i = 0; i < ipSize; i++) {
            String sql = "insert into user_limit_ip ( user_id, ip, add_date ) "
                + " values ( ?,?,? )";
            log.debug("insert sql is : " + sql);
            try {
                PreparedStatement pstmt = oConn.prepareStatement(sql);
                int temp = 1;
                pstmt.setString(temp++, user_id);
                pstmt.setString(temp++, ipGroup[i]);
                pstmt.setString(temp++, datetime);
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

    //在用户表中查询用户的详细信息
    public List selectRecord(Connection oConn, F140_UserLim prm_Data,
                             String orderbyName, String upordown) throws
        Exception {

        int iCount = 0;
        String deptNumb = "";
        String deptName = "";

        ArrayList alResult = new ArrayList();

        String sql = "select * from user_user ";
        String whCondition = "where del_flag = '0' and is_admin = '0' ";

        if (ComString.nvl(prm_Data.getUser_id()).trim().equals("") == false) {
            whCondition += "and user_id = '" +
                ComString.nvl(prm_Data.getUser_id()).trim() + "' ";
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
                F140_UserLim f140_UserMgt = new F140_UserLim();
                f140_UserMgt.setUser_id(rs.getString("user_id").trim());
                f140_UserMgt.setUser_tag(rs.getString("user_tag").trim());
                f140_UserMgt.setUser_name(rs.getString("user_name").trim());
                f140_UserMgt.setTimetype(ComString.nvl(rs.getString(
                    "limit_time")));
                f140_UserMgt.setIptype(ComString.nvl(rs.getString("limit_ip")));
                f140_UserMgt.setAdd_date(rs.getString("add_date"));

                F150_DeptMgtLBean deptBean = new F150_DeptMgtLBean();
                deptNumb = userDeptPK(oConn, f140_UserMgt.getUser_id());
                deptName = deptBean.getDeptName(oConn, deptNumb);
                f140_UserMgt.setDept_name(deptName);

                if (rs.getString("lock_flag").trim().equals("0")) {
                    f140_UserMgt.setLock_flag(ComGlobal.LOCK_No);
                }
                else {
                    f140_UserMgt.setLock_flag(ComGlobal.LOCK_Yes);
                }

                if (F160_UserOnlLBean.isexist(rs.getString("user_tag").trim())) {
                    f140_UserMgt.setIs_login(ComGlobal.LOGIN_Yes);
                }
                else {
                    f140_UserMgt.setIs_login(ComGlobal.LOGIN_No);
                }

                alResult.add(f140_UserMgt);
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

    //得到部门所属的角色列表
    public List getLimitTimeListOptions(Connection oConn, String sUser_id) throws
        Exception {

        int iCount = 0;
        ArrayList limittimelist = new ArrayList();
        DbAccess oDba = new DbAccess();
        String sql =
            "select start_time,end_time from user_limit_time where user_id = '" +
            sUser_id + "' order by add_date";

        ResultSet rs = oDba.query(oConn, sql);

        try {
            while (rs.next()) {
                F140_UserLim time_t = new F140_UserLim();
                String starttime = rs.getString("start_time").trim();
                String endtime = rs.getString("end_time").trim();

                time_t.setLimittimeid(starttime + ":" + endtime);
                time_t.setLimittimename(starttime + " < * < " + endtime);
                limittimelist.add(time_t);
                iCount++;
            }
        }
        catch (Exception e) {
            log.error("error:" + e.toString());
        }

        return limittimelist;
    }

    //得到用户所属的角色列表
    public List getLimitIpListOptions(Connection oConn, String sUser_id) throws
        Exception {

        int iCount = 0;
        ArrayList limitiplist = new ArrayList();
        DbAccess oDba = new DbAccess();
        String sql =
            "select ip from user_limit_ip where user_id = '" +
            sUser_id + "' order by add_date";

        ResultSet rs = oDba.query(oConn, sql);

        try {
            while (rs.next()) {
                F140_UserLim ip_t = new F140_UserLim();
                String sRole_id = rs.getString("ip").trim();

                ip_t.setLimitipid(sRole_id);
                ip_t.setLimitipname(sRole_id);
                limitiplist.add(ip_t);
                iCount++;
            }
        }
        catch (Exception e) {
            log.error("error:" + e.toString());
        }

        return limitiplist;
    }

    //从参数表中取出禁用标签
    public List getParOptions(Connection oConn) throws Exception {

        int iCount = 0;
        ArrayList ParResult = new ArrayList();
        DbAccess oDba = new DbAccess();
        String sql =
            "select * from user_par where par_kind = '01' order by qur_code ";

        ResultSet rs = oDba.query(oConn, sql);

        try {
            while (rs.next()) {
                F140_UserLim par = new F140_UserLim();
                par.setPar_id(ComString.nvl(rs.getString("qur_code")).trim());
                par.setPar_name(ComString.nvl(rs.getString("par_name")).trim());
                ParResult.add(par);
                iCount++;
            }
        }
        catch (Exception e) {
            log.error("error:" + e.toString());
        }

        return ParResult;
    }

    //将某个用户锁定
    public int lockonupdate(Connection oConn, String userId) throws Exception {
        int iRet = 0;

        String sql =
            "update user_user set lock_flag = ? where user_id = ?";
        log.debug("lockonupdate sql is : " + sql);

        try {
            PreparedStatement pstmt = oConn.prepareStatement(sql);
            int temp = 1;
            pstmt.setString(temp++, "1");
            pstmt.setString(temp++, userId);
            iRet = pstmt.executeUpdate();
            pstmt.close();
        }
        catch (Exception e) {
            log.debug("error:" + e.toString());
            throw e;
        }

        return iRet;
    }

    //将某个用户解锁
    public int lockoffupdate(Connection oConn, String userId) throws Exception {
        int iRet = 0;

        String sql =
            "update user_user set lock_flag = ? where user_id = ?";
        log.debug("lockoffupdate sql is : " + sql);

        try {
            PreparedStatement pstmt = oConn.prepareStatement(sql);
            int temp = 1;
            pstmt.setString(temp++, "0");
            pstmt.setString(temp++, userId);
            iRet = pstmt.executeUpdate();
            pstmt.close();
        }
        catch (Exception e) {
            log.debug("error:" + e.toString());
            throw e;
        }

        return iRet;
    }

}
