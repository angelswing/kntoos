package com.conant.ums.lbean;

import java.sql.*;
import java.util.*;

import com.conant.ums.data.*;
import com.conant.ums.db.DbAccess;
import com.conant.ums.interfaces.AuthService;
import com.conant.ums.util.*;
import com.conant.ums.interfaces.UserInfo;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright:</p>
 * <p>Company: </p>
 * @author
 * @version 1.0
 */
public class Validate extends BaseLBean implements AuthService
{
    public Validate()
    {
    }

    //获取所有该用户所属的业务实体信息
    public List entiList(String userTag) throws Exception
    {
        Connection oConn = null;
        oConn = DbAccess.getConnect("");
        DbAccess oDba = new DbAccess();

        List allEntiList = new ArrayList();

        String tablename = "user_user a, user_role_pk b, role_enti_pk c";
        String whcondition = "a.user_tag = '" + userTag +
                             "' and b.user_id = a.user_id and c.role_id = b.role_id";
        String sql = "select distinct c.enti_id from " + tablename + " where " +
                     whcondition;

        ResultSet rs = oDba.query(oConn, sql);
        int rowscount = 0;
        try
        {
            while (rs.next())
            {
                rowscount++;
                F170_EntityMgt EntityData = new F170_EntityMgt();
                EntityData.setEntiId(rs.getString("enti_id"));
                allEntiList.add(EntityData);
            }
            rs.close();
        } catch (Exception e)
        {
            allEntiList = null;
            throw e;
        }
        return allEntiList;
    }

    //获取所有该用户所属的业务实体信息
    public List operList(String userTag) throws Exception
    {
        Connection oConn = null;
        oConn = DbAccess.getConnect("");
        DbAccess oDba = new DbAccess();

        List allOperList = new ArrayList();

        String tablename = "user_user a, user_role_pk b, role_oper_pk c";
        String whcondition = "a.user_tag = '" + userTag +
                             "' and b.user_id = a.user_id and c.role_id = b.role_id";
        String sql = "select distinct c.oper_id from " + tablename + " where " +
                     whcondition;

        ResultSet rs = oDba.query(oConn, sql);
        int rowscount = 0;
        try
        {
            while (rs.next())
            {
                rowscount++;
                F180_OperMgt operaterData = new F180_OperMgt();
                operaterData.setOperId(rs.getString("oper_id"));
                allOperList.add(operaterData);
            }
            rs.close();
        } catch (Exception e)
        {
            allOperList = null;
            throw e;
        }
        return allOperList;
    }

    //检查用户是否具有该业务实体的权限
    public boolean isEnti(String enti_id, String user_tag) throws Exception
    {

        boolean isEnti = false;
        String tablename = "user_user a, user_role_pk b, role_enti_pk c";
        String whcondition = "a.user_tag = '" + user_tag +
                             "' and b.user_id = a.user_id and c.role_id = b.role_id and c.enti_id = '" +
                             enti_id + "'";
        String sql = "select c.enti_id from " + tablename + " where " +
                     whcondition;

        try
        {
            Connection oConn = null;
            oConn = DbAccess.getConnect("");

            PreparedStatement pstmt = oConn.prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet ret = pstmt.executeQuery();
            if (ret.next())
            {
                isEnti = true;
            }
            pstmt.close();
        } catch (Exception e)
        {
            throw e;
        }

        return isEnti;
    }

    //检查用户是否具有该系统操作的权限
    public boolean isOper(String oper_id, String user_tag) throws Exception
    {

        boolean isOper = false;
        String tablename = "user_user a, user_role_pk b, role_oper_pk c";
        String whcondition = "a.user_tag = '" + user_tag +
                             "' and b.user_id = a.user_id and c.role_id = b.role_id and c.oper_id = '" +
                             oper_id + "'";
        String sql = "select c.oper_id from " + tablename + " where " +
                     whcondition;

        try
        {
            Connection oConn = null;
            oConn = DbAccess.getConnect("");

            PreparedStatement pstmt = oConn.prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet ret = pstmt.executeQuery();
            if (ret.next())
            {
                isOper = true;
            }
            pstmt.close();
        } catch (Exception e)
        {
            throw e;
        }

        return isOper;
    }

    //检查用户是否对该业务实体有该系统操作的权限
    public boolean isEntiOPer(String enti_id, String oper_id, String user_tag) throws
            Exception
    {

        boolean isEntiOPer = false;
        String tablename =
                "user_user a, user_role_pk b, role_enti_pk c, role_oper_pk d";
        String whcondition = "a.user_tag = '" + user_tag + "' and b.user_id = a.user_id and c.role_id = b.role_id and d.role_id = b.role_id and c.enti_id = '" +
                             enti_id + "' and d.oper_id = '" + oper_id + "'";
        String sql = "select c.enti_id, d.oper_id from " + tablename +
                     " where " +
                     whcondition;

        try
        {
            Connection oConn = null;
            oConn = DbAccess.getConnect("");

            PreparedStatement pstmt = oConn.prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet ret = pstmt.executeQuery();
            if (ret.next())
            {
                isEntiOPer = true;
            }
            pstmt.close();
        } catch (Exception e)
        {
            throw e;
        }

        return isEntiOPer;
    }

    //用户登入验证
    public int login(String usertag, String passwd, String sIp) throws
            Exception
    {
        int flag = 1;
        Connection oConn = null;
        oConn = DbAccess.getConnect("");

        FuncLBean funcLBean = new FuncLBean();
        //String sPasswd = ComString.MD5Encode(passwd);

        //检查用户是否存在、密码是否正确
        if (!funcLBean.checkLoing(oConn, usertag, passwd))
        {
            flag = 0;
            return flag;
        }

        //检查用户是否被锁定
        if (!funcLBean.checkLock(oConn, usertag))
        {
            flag = -1;
            return flag;
        }

        //检查ip是否被限制
        if (!funcLBean.checkIpFlag(oConn, usertag, sIp))
        {
            flag = -2;
            return flag;
        }

        //检查time是否被限制
        if (!funcLBean.checkTimeFlag(oConn, usertag))
        {
            flag = -3;
            return flag;
        }

        //检查密码是否为初始密码
        if (passwd.equals(ComGlobal.CPASSWD))
        {
            flag = -4;
            return flag;
        }

        flag = funcLBean.updateInfo(oConn, usertag, sIp);

        return flag;
    }

    //查询用户信息
    public F130_UserMgt loginData(String userTag) throws
            Exception
    {
        F130_UserMgt userData = new F130_UserMgt();
        Connection oConn = null;
        oConn = DbAccess.getConnect("");

        FuncLBean funcLBean = new FuncLBean();

        F130_UserMgt loginData = funcLBean.updateLogin(oConn, userTag);
        userData.setUser_id(loginData.getUser_id());
        userData.setUser_tag(userTag);
        userData.setUser_name(loginData.getUser_name());
        userData.setEmail(loginData.getEmail());
        userData.setAddress(loginData.getAddress());
        userData.setMobile(loginData.getMobile());
        userData.setHome_tel(loginData.getHome_tel());
        userData.setDept_name(funcLBean.deptName(oConn, userTag));
        userData.setArea(funcLBean.area(oConn, userTag));

        return userData;
    }

    //用户登出验证
    public void logout(String loginId) throws Exception
    {
        if (SessionMap.map.containsKey(loginId))
        {
            SessionMap.map.remove(loginId);
        }
    }

    //修改密码
    public int changePass(String usertag, String oldPasswd, String newPasswd) throws
            Exception
    {
        int iCount = 0;
        FuncLBean funcLBean = new FuncLBean();
        Connection oConn = null;
        oConn = DbAccess.getConnect("");
        String sql =
                " update user_user set user_passwd = ? where user_tag = ?";
        log.debug("update sql is : " + sql);

        if (funcLBean.isRight(oConn, usertag, oldPasswd))
        {
            try
            {
                PreparedStatement pstmt = oConn.prepareStatement(sql);
                int temp = 1;

                //pstmt.setString(temp++, ComString.MD5Encode(newPasswd));
                pstmt.setString(temp++, Decode.encrypt(newPasswd));
                pstmt.setString(temp++, usertag);
                //pstmt.setString(temp++, ComString.MD5Encode(oldPasswd));

                iCount = pstmt.executeUpdate();
                pstmt.close();
            } catch (Exception e)
            {
                log.debug("error:" + e.toString());
                throw e;
            }
        }
        else
        {
            iCount = -1;
        }

        return iCount;
    }

    //修改用户信息
    public int changeUserInfo(String UserTag, UserInfo userInfo) throws
            Exception
    {
        int iCount = 0;

        Connection oConn = null;
        oConn = DbAccess.getConnect("");
        String sql =
                " update user_user set email = ?, address = ?, home_tel = ?, mobile = ? where user_tag = ?";
        log.debug("update sql is : " + sql);

        try
        {
            PreparedStatement pstmt = oConn.prepareStatement(sql);
            int temp = 1;

            pstmt.setString(temp++, userInfo.getEmail());
            pstmt.setString(temp++, userInfo.getAddress());
            pstmt.setString(temp++, userInfo.getHome_tel());
            pstmt.setString(temp++, userInfo.getMobile());
            pstmt.setString(temp++, UserTag);

            iCount = pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e)
        {
            log.debug("error:" + e.toString());
            throw e;
        }

        return iCount;
    }

    //获取所有部门信息
    public List deptGroup() throws Exception
    {
        int iCount = 0;
        ArrayList deptList = new ArrayList();
        Connection oConn = null;
        oConn = DbAccess.getConnect("");
        DbAccess oDba = new DbAccess();
        String sql =
                "select * from user_dept order by dept_id";

        ResultSet rs = oDba.query(oConn, sql);

        try
        {
            while (rs.next())
            {
                F150_DeptMgt dept = new F150_DeptMgt();
                dept.setDept_id(ComString.nvl(rs.getString("dept_id")).trim());
                dept.setDept_name(ComString.nvl(rs.getString("dept_name")).
                                  trim());
                deptList.add(dept);
                iCount++;
            }
        } catch (Exception e)
        {
            log.error("error:" + e.toString());
        }

        return deptList;
    }

}
