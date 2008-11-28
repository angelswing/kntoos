package com.conant.ums.lbean;

import java.sql.*;
import java.util.*;

import com.conant.ums.data.F150_DeptMgt;
import com.conant.ums.db.DbAccess;
import com.conant.ums.util.*;


public class F150_DeptMgtLBean
    extends BaseLBean {
    String datetime = new SysTime().getSysTimeSemicolon();

    public F150_DeptMgtLBean() {
    }

    //判断是新增记录，还是更新记录
    public int insert(Connection oConn, F150_DeptMgt prm_Data) throws Exception {

        int iRet = 0;

        if (ComString.nvl(prm_Data.getDept_id()).trim().equals("") == true) {
            iRet = insertData(oConn, prm_Data);
        }
        else {
            iRet = updateData(oConn, prm_Data, "update");
        }

        return iRet;
    }

    //删除信息操作的转向
    public int delete(Connection oConn, F150_DeptMgt prm_Data) throws Exception {

        int iRet = updateData(oConn, prm_Data, "delete");

        return iRet;
    }

    //更新部门信息表中的记录信息
    public int updateData(Connection oConn, F150_DeptMgt prm_Data,
                          String prm_Flag) throws Exception {

        int iRet = 0;

        if(prm_Data.getArea().equals("")||prm_Data.getArea()==null){
            prm_Data.setArea("湖北省");
        }

        String sql = "update user_dept set ";

        if (prm_Flag.equals("delete") == true) {
            sql += "del_flag = '1', ";
        }
        else if (prm_Flag.equals("update") == true) {
            sql += "dept_name = '" + prm_Data.getDept_name() + "', "
                + "dept_desc = '" + BytesConverter.asc2gb(prm_Data.getDept_desc()) +
                "', "
                + "principal = '" + BytesConverter.asc2gb(prm_Data.getPrincipal()) +
                "', "
                + "telephone = '" + prm_Data.getTelephone() + "', "
                + "faxes = '" + prm_Data.getFaxes() + "', "
                + "area = '" + BytesConverter.asc2gb(prm_Data.getArea()) + "', "
                + "parent_dept_id = '" + prm_Data.getParent_dept_id() + "', ";
        }
        sql += "upd_date = '" + datetime + "', "
            + "upd_userid = '" + prm_Data.getUpd_userid() + "' ";
        if (prm_Flag.equals("delete") == true) {
            sql += "where dept_id = '" + prm_Data.getSelecteddept_id() + "'";
        }
        else if (prm_Flag.equals("update") == true) {
            sql += "where dept_id = '" + prm_Data.getDept_id() + "'";
        }
        log.debug("update sql :" + sql);

        DbAccess oDba = new DbAccess();
        iRet = oDba.execute(oConn, sql);

        return iRet;
    }

    //向部门角色关系表中插入关系记录
    public int insertDeptRole(Connection oConn, String dept_id,
                              String[] deptrole) throws Exception {

        int iCount = 0;
        int deptNum = 0;

        if (ComString.nvl(dept_id).trim().equals("")) {
            deptNum = Integer.parseInt(getMaxDeptid(oConn)) - 1;
            dept_id = ComString.zeroFormat(Integer.toString(deptNum), 15);
        }

        String sql =
            "insert into dept_role_pk ( dept_id, role_id, upd_date ) values ( ?,?,? )";
        log.debug("insert sql is : " + sql);

        int roleSize = deptrole.length;

        for (int i = 0; i < roleSize; i++) {
            try {
                PreparedStatement pstmt = oConn.prepareStatement(sql);
                pstmt.setString(1, dept_id);
                pstmt.setString(2, deptrole[i]);
                pstmt.setString(3, datetime);

                iCount = pstmt.executeUpdate();
            }
            catch (Exception e) {
                log.debug("error:" + e.toString());
                throw e;
            }
        }
        return iCount;

    }

    //更新部门角色关系表的关系记录
    public int updateDeptRole(Connection oConn, String dept_id,
                              String[] deptrole) throws Exception {

        int iCount = 0;

        DbAccess oDba = new DbAccess();
        String sql = "delete from dept_role_pk where dept_id = '" + dept_id +
            "'";
        log.debug("delete sql is : " + sql);

        iCount = oDba.execute(oConn, sql);

        if(deptrole != null){
            try {
         	iCount = insertDeptRole(oConn, dept_id, deptrole);
     	    }
      	    catch (Exception e) {
     	        log.debug("error:" + e.toString());
    	        throw e;
   	    }
	}

        return iCount;
    }

    //向部门信息表中插入记录信息
    public int insertData(Connection oConn, F150_DeptMgt prm_Data) throws
        Exception {

        int iRet = 0;

        if(prm_Data.getArea().equals("")||prm_Data.getArea()==null){
            prm_Data.setArea("湖北省");
        }

        String sql = "insert into user_dept ( dept_id, dept_name, dept_desc, principal, telephone, faxes, del_flag, parent_dept_id, add_date, add_userid, area )"
            + " values ( ?,?,?,?,?,?,?,?,?,?,? )";
        log.debug("insert sql is : " + sql);

        String MaxDeptid = getMaxDeptid(oConn);

        try {
            PreparedStatement pstmt = oConn.prepareStatement(sql);
            pstmt.setString(1, MaxDeptid);
            pstmt.setString(2, prm_Data.getDept_name());
            pstmt.setString(3, BytesConverter.asc2gb(prm_Data.getDept_desc()));
            pstmt.setString(4, BytesConverter.asc2gb(prm_Data.getPrincipal()));
            pstmt.setString(5, prm_Data.getTelephone());
            pstmt.setString(6, prm_Data.getFaxes());
            pstmt.setString(7, "0");
            pstmt.setString(8, prm_Data.getParent_dept_id());
            pstmt.setString(9, datetime);
            pstmt.setString(10, prm_Data.getAdd_userid());
            pstmt.setString(11, BytesConverter.asc2gb(prm_Data.getArea()));

            iRet = pstmt.executeUpdate();
        }
        catch (Exception e) {
            log.debug("error:" + e.toString());
            throw e;
        }

        return iRet;
    }
    //得到部门表中最大的流水号，并且＋1
    private String getMaxDeptid(Connection oConn) throws Exception {

        String iCount = "0";
        String sRoleId = "000000000000001";
        DbAccess oDba = new DbAccess();

        String sql = "select count(*) from user_dept";

        ResultSet rs = oDba.query(oConn, sql);
        while (rs.next()) {
            iCount = rs.getString(1);
        }
        if (iCount.equals("0")) {
            return sRoleId;
        }
        else {

            sql = "select max(dept_id) + 1 from user_dept";

            rs = oDba.query(oConn, sql);

            try {
                while (rs.next()) {
                    sRoleId = ComString.zeroFormat(rs.getString(1), 15);
                }
            }
            catch (Exception e) {
                log.debug("error:" + e.toString());
                throw e;
            }

        }
        return sRoleId;
    }

    //查询部门信息
    public List SelectData(Connection oConn, F150_DeptMgt prm_Data) throws
        Exception {

        int iCount = 0;

        ArrayList alResult = new ArrayList();

        String sql = "select * from user_dept ";
        String whCondition = "where del_flag = '0' ";

        if (ComString.nvl(prm_Data.getDept_id()).trim().equals("") == false) {
            whCondition += "and dept_id = '" +
                ComString.nvl(prm_Data.getDept_id()).trim() + "' ";
        }
        if (ComString.nvl(prm_Data.getDept_desc()).trim().equals("") == false) {
            whCondition += "and dept_desc like '%" +
                BytesConverter.asc2gb(prm_Data.getDept_desc()).trim() + "%' ";
        }
        if (ComString.nvl(prm_Data.getDept_name()).trim().equals("") == false) {
            whCondition += "and dept_name like '%" +
                ComString.nvl(prm_Data.getDept_name()).trim() + "%' ";
        }

        sql += whCondition + "order by dept_id desc";
        log.debug("select sql : " + sql);
        DbAccess oDba = new DbAccess();

        ResultSet rs = oDba.query(oConn, sql);

        try {
            while (rs.next()) {
                F150_DeptMgt f120_deptmgt = new F150_DeptMgt();
                f120_deptmgt.setDept_id(ComString.nvl(rs.getString("dept_id").
                    trim()));
                f120_deptmgt.setDept_name(rs.getString("dept_name").trim());
                f120_deptmgt.setDept_desc(rs.getString("dept_desc").trim());
                f120_deptmgt.setPrincipal(ComString.nvl(rs.getString(
                    "principal")));
                f120_deptmgt.setTelephone(ComString.nvl(rs.getString(
                    "telephone")));
                f120_deptmgt.setFaxes(ComString.nvl(rs.getString("faxes")));
                f120_deptmgt.setParent_dept_id(rs.getString("parent_dept_id"));
                f120_deptmgt.setAdd_date(rs.getString("add_date"));
                f120_deptmgt.setArea(rs.getString("area"));
                alResult.add(f120_deptmgt);
                iCount++;
            }
        }
        catch (Exception e) {
            log.debug("error: " + e.toString());
            throw e;
        }

        return alResult;
    }

    //通过部门流水号来得到部门名称
    public String getDeptName(Connection oConn, String prm_deptid) throws
        Exception {

        String sDeptName = "";
        String sql = "select dept_name from user_dept where dept_id = '" +
            prm_deptid + "' and del_flag = '0'";

        DbAccess oDba = new DbAccess();
        ResultSet rs = oDba.query(oConn, sql);

        try {
            while (rs.next()) {
                sDeptName = ComString.nvl(rs.getString("dept_name")).trim();
            }
        }
        catch (Exception e) {
            log.debug("error: " + e.toString());
            throw e;
        }

        return sDeptName;
    }

    //得到部门列表
    public List getDeptListOptions(Connection oConn) throws Exception {

        int iCount = 0;
        ArrayList dept_list = new ArrayList();
        DbAccess oDba = new DbAccess();
        String sql =
            "select * from user_dept where del_flag = '0' order by dept_id";

        ResultSet rs = oDba.query(oConn, sql);

        try {
            while (rs.next()) {
                F150_DeptMgt dept = new F150_DeptMgt();
                dept.setDept_id(ComString.nvl(rs.getString("dept_id")).trim());
                dept.setDept_name(ComString.nvl(rs.getString("dept_name")).
                                    trim());
                dept_list.add(dept);
                iCount++;
            }
        }
        catch (Exception e) {
            log.error("error:" + e.toString());
        }

        return dept_list;
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
                F150_DeptMgt dept_role_t = new F150_DeptMgt();
                String sRole_id = ComString.nvl(rs.getString("role_id")).trim();
                dept_role_t.setDept_role_id(sRole_id);

                F110_RoleMgtLBean roleBean = new F110_RoleMgtLBean();
                dept_role_t.setDept_role_name(roleBean.getRoleName(oConn,
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

    //检查部门表中某个部门下是否有用户
    public boolean checkDeptUser(Connection oConn, String dept_id) throws
        Exception {
        boolean delDept = true;

        String sql = "select user_id from user_dept_pk where dept_id = '" +
            dept_id + "'";

        DbAccess oDba = new DbAccess();
        ResultSet rs = oDba.query(oConn, sql);

        try {
            while (rs.next()) {
                delDept = false;
            }
        }
        catch (Exception e) {
            log.debug("error:" + e.toString());
            throw e;
        }

        return delDept;
    }

    //在部门表中彻底删除部门,并将部门角色中的关系记录清空
    public int quiteDelete(Connection oConn, String dept_id) throws
        Exception {

        int iCount = 0;

        String sql = "delete from user_dept where dept_id = ? ";
        log.debug("delete sql is : " + sql);
        try {
            PreparedStatement pstmt = oConn.prepareStatement(sql);
            int temp = 1;
            pstmt.setString(temp++, dept_id);
            iCount = pstmt.executeUpdate();
            pstmt.close();
        }
        catch (Exception e) {
            log.debug("error:" + e.toString());
            throw e;
        }

        sql = "delete from dept_role_pk where dept_id = ? ";
        log.debug("delete sql is : " + sql);
        try {
            PreparedStatement pstmt0 = oConn.prepareStatement(sql);
            int temp0 = 1;
            pstmt0.setString(temp0++, dept_id);
            iCount = pstmt0.executeUpdate();
            pstmt0.close();
        }
        catch (Exception e) {
            log.debug("error:" + e.toString());
            throw e;
        }

        return iCount;
    }

    //判断部门是否重复
    public boolean isExist(Connection oConn, String deptName) throws
        Exception {

        boolean exist_flag = false;
        String tablename = "user_dept";
        String whcondition = "dept_name = '" + deptName +
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

    //判断部门修改时不能重复命名
    public boolean isUpdateExist(Connection oConn, String dept_id, String deptName) throws
        Exception {

        boolean exist_flag = false;
        String tablename = "user_dept";
        String whcondition = "dept_id != '" + dept_id + "' and dept_name = '" + deptName +
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

}
