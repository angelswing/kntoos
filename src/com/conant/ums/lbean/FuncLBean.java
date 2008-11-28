package com.conant.ums.lbean;

import java.sql.*;
import java.util.*;

import com.conant.ums.data.*;
import com.conant.ums.db.DbAccess;
import com.conant.ums.util.*;
import com.conant.ums.util.tree.*;

/**
 * @date:</p>
 * @author:
 * @version 1.0
 */
public class FuncLBean extends BaseLBean
{
    String datetime = new SysTime().getSysDate();

    String loginTime = new SysTime().getSysTimeSemicolon();

    public FuncLBean()
    {
    }

    //得到用户登陆信息
    public List loginData(Connection oConn) throws Exception
    {
        List rootList = new ArrayList();
        TreeNode trRoot = null;
        List EntiList = new ArrayList();
        Map DeptMap = new HashMap();

        List entiList = getAllFunc(oConn);

        try
        {
            for (int i = 0; i < entiList.size(); i++)
            {
                Func_t enti = (Func_t) entiList.get(i);
                TreeNode treenode = new TreeNode(enti.getFun_id(),
                                                 enti.getParent_funid(),
                                                 enti.getFun_name(),
                                                 enti.getFun_link());
                EntiList.add(treenode);
                DeptMap.put(enti.getFun_id(), treenode);
            }
            trRoot = TreeHelper.makeTree(EntiList, DeptMap, "#");
            rootList.add(trRoot);
        } catch (Exception e)
        {
            log.debug("error: " + e.toString());
            throw e;
        }
        return rootList;
    }

    //得到用户管理子系统的菜单
    public List getAllFunc(Connection oConn) throws Exception
    {
        List allEntiList = null;
        allEntiList = new ArrayList();
        DbAccess oDba = new DbAccess();
        String sql =
                "select * from user_func order by layer_rank,parent_funid,layer_seq";
        ResultSet rs = oDba.query(oConn, sql);
        int rowscount = 0;
        try
        {
            while (rs.next())
            {
                rowscount++;
                Func_t func = new Func_t();
                func.setFun_id(rs.getString("fun_id"));
                func.setFun_name(rs.getString("fun_name"));
                func.setParent_funid(rs.getString("parent_funid").trim());
                func.setFun_type(rs.getString("fun_type"));
                func.setFun_link(rs.getString("fun_link"));
                allEntiList.add(func);
            }
            rs.close();
            log.debug("select record:" + rowscount);
        } catch (Exception e)
        {
            allEntiList = null;
            log.error("get_set:" + e.getMessage());
            throw e;
        }
        return allEntiList;

    }

    //检查用户是否存在、密码是否正确
    public boolean checkUser(Connection oConn, LoginData loginData) throws
            Exception
    {
        boolean bRet = true;
        String sPasswd = null;
        String sql =
                // "select * from user_user where del_flag='0' and user_tag=?";
                "select * from user_user where del_flag='0' and is_admin='1' and user_tag=?";

        try
        {
            PreparedStatement pstmt = oConn.prepareStatement(sql);
            pstmt.setString(1, loginData.getUserTag().trim());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
            {
                loginData.setUserName(rs.getString("user_name"));
                sPasswd = rs.getString("user_passwd").trim();
            }
            else
            {
                bRet = false;
            }
            rs.close();
            pstmt.close();

            //检查用户密码
            if (bRet)
            {
                if (!sPasswd.equals(loginData.getUserPasswd()))
                {
                    bRet = false;
                }
            }
        } catch (Exception e)
        {
            log.error("select user info from user_user:" + e.getMessage());
            throw e;
        }
        return bRet;
    }

    //检查用户是否已经登陆
    public boolean checkOn(Connection oConn, String usertag) throws
            Exception
    {
        boolean bRet = true;
        String sLogin = null;
        String sql = "select is_login from user_user where user_tag=?";
        try
        {
            PreparedStatement pstmt = oConn.prepareStatement(sql);
            pstmt.setString(1, usertag);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
            {
                sLogin = rs.getString("is_login");
            }
            rs.close();
            pstmt.close();

            if (!sLogin.equals("0"))
            {
                bRet = false;
            }
        } catch (Exception e)
        {
            log.error("select user info from user_user:" + e.getMessage());
            throw e;
        }

        return bRet;
    }

    //检查用户是否被锁定
    public boolean checkLock(Connection oConn, String usertag) throws
            Exception
    {
        boolean bRet = true;
        String sLock = null;
        String sql = "select lock_flag from user_user where user_tag=?";
        try
        {
            PreparedStatement pstmt = oConn.prepareStatement(sql);
            pstmt.setString(1, usertag);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
            {
                sLock = rs.getString("lock_flag");
            }
            rs.close();
            pstmt.close();

            if (!sLock.equals("0"))
            {
                bRet = false;
            }
        } catch (Exception e)
        {
            log.error("select user info from user_user:" + e.getMessage());
            throw e;
        }

        return bRet;
    }

    //检查用户ip是否禁用
    public boolean checkIpFlag(Connection oConn, String usertag, String ip) throws
            Exception
    {
        boolean bRet = true;
        String sIpFlag = null;
        List iplist = new ArrayList();

        String sql = "select limit_ip from user_user where user_tag = '" +
                     usertag + "'";
        log.info("sql is " + sql);
        DbAccess oDba = new DbAccess();
        ResultSet rs = oDba.query(oConn, sql);
        while (rs.next())
        {
            sIpFlag = rs.getString("limit_ip");
        }

        //得到用户ip列表
        sql =
                "select b.ip from user_user a, user_limit_ip b where a.user_tag = '" +
                usertag + "' and a.user_id = b.user_id";
        log.info("sql is " + sql);
        rs = oDba.query(oConn, sql);
        int rowscount = 0;
        while (rs.next())
        {
            rowscount++;
            iplist.add(rs.getString("ip"));
        }

        if (iplist.size() != 0 && sIpFlag != null && sIpFlag.equals("01"))
        {
            for (int i = 0; i < iplist.size(); i++)
            {
                if (iplist.toArray()[i].equals(ip))
                {
                    bRet = false;
                    return bRet;
                }
            }
        }

        if (iplist.size() != 0 && sIpFlag != null && sIpFlag.equals("02"))
        {
            for (int i = 0; i < iplist.size(); i++)
            {
                if (iplist.toArray()[i].equals(ip))
                {
                    bRet = true;
                    return bRet;
                }
            }
            bRet = false;
            return bRet;
        }

        return bRet;
    }

    //检查用户time是否禁用
    public boolean checkTimeFlag(Connection oConn, String usertag) throws
            Exception
    {
        boolean bRet = true;
        String sTimeFlag = null;

        String sql = "select limit_time from user_user where user_tag = '" +
                     usertag + "'";
        log.info("sql is " + sql);
        DbAccess oDba = new DbAccess();
        ResultSet rs = oDba.query(oConn, sql);
        while (rs.next())
        {
            sTimeFlag = rs.getString("limit_time");
        }

        //得到用户ip列表
        sql = "select b.start_time, b.end_time from user_user a, user_limit_time b where a.user_tag = '" +
              usertag + "' and a.user_id = b.user_id";
        log.info("sql is " + sql);
        rs = oDba.query(oConn, sql);
        int rowscount = 0;

        while (rs.next())
        {
            if (sTimeFlag != null && sTimeFlag.equals("01"))
            {
                if (rs.getString("end_time").compareTo(datetime) > 0 &&
                    datetime.compareTo(rs.getString("start_time")) > 0)
                {
                    bRet = false;
                    return bRet;
                }
            }

            if (sTimeFlag != null && sTimeFlag.equals("02"))
            {
                if (rs.getString("end_time").compareTo(datetime) > 0 &&
                    datetime.compareTo(rs.getString("start_time")) > 0)
                {
                    bRet = true;
                    return bRet;
                }
                bRet = false;
                return bRet;
            }
            rowscount++;
        }

        return bRet;
    }

    //通过userTag得到用户其他信息
    public F130_UserMgt updateLogin(Connection oConn, String userTag) throws
            Exception
    {

        F130_UserMgt f130_UserMgt = new F130_UserMgt();

        String sql = "select * from user_user where user_tag = '" + userTag +
                     "'";

        log.debug("sql is " + sql);
        DbAccess oDba = new DbAccess();
        ResultSet rs = oDba.query(oConn, sql);

        while (rs.next())
        {
            f130_UserMgt.setUser_id(rs.getString("user_id").trim());
            f130_UserMgt.setUser_name(rs.getString("user_name").trim());
            f130_UserMgt.setAdd_date(rs.getString("add_date"));
            f130_UserMgt.setEmail(rs.getString("email"));
            f130_UserMgt.setAddress(rs.getString("address"));
            f130_UserMgt.setMobile(rs.getString("mobile"));
            f130_UserMgt.setHome_tel(rs.getString("home_tel"));
            if (rs.getString("lock_flag").trim().equals("0"))
            {
                f130_UserMgt.setLock_flag(ComGlobal.LOCK_No);
            }
            else
            {
                f130_UserMgt.setLock_flag(ComGlobal.LOCK_Yes);
            }
        }

        return f130_UserMgt;
    }

    public String deptName(Connection oConn, String userTag) throws
            Exception
    {

        String deptName = "";

        String sql =
                "select c.dept_name from user_user a, user_dept_pk b, user_dept c where a.user_tag = '" +
                userTag +
                "' and a.user_id = b.user_id and b.dept_id = c.dept_id";

        log.debug("sql is " + sql);
        DbAccess oDba = new DbAccess();
        ResultSet rs = oDba.query(oConn, sql);

        while (rs.next())
        {
            deptName = rs.getString("dept_name").trim();
        }

        return deptName;
    }

    public String area(Connection oConn, String userTag) throws
            Exception
    {

        String area = "";

        String sql =
                "select c.area from user_user a, user_dept_pk b, user_dept c where a.user_tag = '" +
                userTag +
                "' and a.user_id = b.user_id and b.dept_id = c.dept_id";

        log.debug("sql is " + sql);
        DbAccess oDba = new DbAccess();
        ResultSet rs = oDba.query(oConn, sql);

        while (rs.next())
        {
            if (rs.getString("area") != null)
            {
                area = rs.getString("area").trim();
            }
            else
            {
                area = "";
            }
        }

        return area;
    }

    //检查用户是否存在、密码是否正确
    public boolean checkLoing(Connection oConn, String userTag, String passwd) throws
            Exception
    {
        boolean bRet = true;
        String sPasswd = null;
        String sql =
                "select * from user_user where del_flag='0' and user_tag=? and is_admin='0'";

        try
        {
            PreparedStatement pstmt = oConn.prepareStatement(sql);
            pstmt.setString(1, userTag.trim());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
            {
                sPasswd = Decode.decrypt(rs.getString("user_passwd"));
            }
            else
            {
                bRet = false;
            }
            rs.close();
            pstmt.close();

            //检查用户密码
            if (bRet)
            {
                if (!sPasswd.equals(passwd))
                {
                    bRet = false;
                }
            }
        } catch (Exception e)
        {
            log.error("select user info from user_user:" + e.getMessage());
            throw e;
        }
        return bRet;
    }

    //将登陆的用户信息放到内存中，并返回一个5位随机数
    public int updateInfo(Connection oConn, String userTag, String sip) throws
            Exception
    {
        Random random = new Random();
        int result = random.nextInt(100000);
        String loginId = String.valueOf(result);

        F130_UserMgt f130_UserMgt = new F130_UserMgt();

        F130_UserMgt loginData = updateLogin(oConn, userTag);
        f130_UserMgt.setUser_id(loginData.getUser_id());
        f130_UserMgt.setUser_tag(userTag);
        f130_UserMgt.setUser_name(loginData.getUser_name());
        f130_UserMgt.setAdd_date(loginData.getAdd_date());
        f130_UserMgt.setLogin_ip(sip);
        f130_UserMgt.setLogin_time(loginTime);
        f130_UserMgt.setLock_flag(loginData.getLock_flag());
        SessionMap.map.put(loginId, f130_UserMgt);

        return result;
    }

    //判断修改用户时密码是否正确
    public boolean isRight(Connection oConn, String userTag, String userPasswd) throws
            Exception
    {

        boolean bRet = true;
        String sPasswd = null;
        String sql = "select * from user_user where user_tag = ?";

        try
        {
            PreparedStatement pstmt = oConn.prepareStatement(sql);
            pstmt.setString(1, userTag.trim());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
            {
                sPasswd = Decode.decrypt(rs.getString("user_passwd"));
            }
            else
            {
                bRet = false;
            }
            rs.close();
            pstmt.close();

            //检查用户密码
            if (bRet)
            {
                if (!sPasswd.equals(userPasswd))
                {
                    bRet = false;
                }
            }
        } catch (Exception e)
        {
            log.error("select user info from user_user:" + e.getMessage());
            throw e;
        }
        return bRet;
    }

}
