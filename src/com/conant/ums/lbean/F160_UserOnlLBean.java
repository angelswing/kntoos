package com.conant.ums.lbean;

import java.sql.*;
import java.util.*;

import com.conant.ums.data.F130_UserMgt;
import com.conant.ums.db.DbAccess;
import com.conant.ums.util.*;
import com.conant.ums.interfaces.CaseComparator;


public class F160_UserOnlLBean
    extends BaseLBean {

    public F160_UserOnlLBean() {
    }

    //在用户表中查询用户的详细信息
    public List selectRecord(Connection oConn, F130_UserMgt prm_Data) throws
        Exception {

        int iCount = 0;
        String deptNumb = "";

        ArrayList alResult = new ArrayList();
        F130_UserLBean userBean = new F130_UserLBean();

        String sql = "select * from user_user ";
        String whCondition = "where del_flag = '0' and is_admin = '0' ";

        if (ComString.nvl(prm_Data.getUser_id()).trim().equals("") == false) {
            whCondition += "and user_id = '" +
                ComString.nvl(prm_Data.getUser_id()).trim() + "' ";
            deptNumb = userBean.userDeptPK(oConn, prm_Data.getUser_id().trim());
        }
        if (ComString.nvl(prm_Data.getUser_name()).trim().equals("") == false) {
            whCondition += "and user_name like '%" +
                prm_Data.getUser_name().trim() + "%' ";
        }
        if (ComString.nvl(prm_Data.getUser_tag()).trim().equals("") == false) {
            whCondition += "and user_tag like '%" +
                prm_Data.getUser_tag().trim() + "%' ";
        }

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
                f130_UserMgt.setEmail(ComString.nvl(rs.getString("email")));
                f130_UserMgt.setAddress(ComString.nvl(rs.getString("address")));
                f130_UserMgt.setHome_tel(ComString.nvl(rs.getString("home_tel")));
                f130_UserMgt.setMobile(ComString.nvl(rs.getString("mobile")));
                f130_UserMgt.setAdd_date(rs.getString("add_date"));
                f130_UserMgt.setLogin_time(rs.getString("login_time"));
                f130_UserMgt.setLogin_ip(rs.getString("login_ip"));
                f130_UserMgt.setDeptid(deptNumb);

                if (rs.getString("lock_flag").trim().equals("0")) {
                    f130_UserMgt.setLock_flag(ComGlobal.LOCK_No);
                }
                else {
                    f130_UserMgt.setLock_flag(ComGlobal.LOCK_Yes);
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

    //在用户表中查询用户的详细信息
    public List selectInfo() throws Exception {
        int iCount = 0;

        ArrayList alResult = new ArrayList();
        for (Iterator it = SessionMap.map.values().iterator(); it.hasNext(); ) {
            F130_UserMgt loginData = (F130_UserMgt) it.next();

            F130_UserMgt f130_UserMgt = new F130_UserMgt();
            f130_UserMgt.setUser_id(loginData.getUser_id());
            f130_UserMgt.setUser_tag(loginData.getUser_tag());
            f130_UserMgt.setUser_name(loginData.getUser_name());
            f130_UserMgt.setAdd_date(loginData.getAdd_date());
            f130_UserMgt.setLogin_ip(loginData.getLogin_ip());
            f130_UserMgt.setLogin_time(loginData.getLogin_time());

            alResult.add(f130_UserMgt);
            iCount++;
        }

        Collections.sort(alResult, new CaseComparator());

        return alResult;
    }

    //为了用户信息,用户限制模块中查询某个用户的在线状态
    public static boolean isexist(String userTag) throws Exception {
        boolean exist_flag = false;

        for (Iterator it = SessionMap.map.values().iterator(); it.hasNext(); ) {
            F130_UserMgt loginData = (F130_UserMgt) it.next();
            if (userTag.equals(loginData.getUser_tag().trim())) {
                exist_flag = true;
            }
        }

        return exist_flag;
    }

}
