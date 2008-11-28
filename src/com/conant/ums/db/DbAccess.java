package com.conant.ums.db;

import java.sql.*;
import java.util.Vector;

import com.conant.ums.util.*;

/**
 * <p>
 *说明：本类是数据库访问接口DataBaseAcc的父类，它提供了数据库连接的基本API。
 *</p>
 *<p>
 *日期：
 *作者：
 *</p>
 **/
public class DbAccess {
    //private static Hashtable htPara = new Hashtable();

    private DataSourcePara mDataSourcePara = null;
    private JdbcPara mJdbcPara = null;

    private BytesConverter mConverter = null;
    Object oPara = null;

    private String mKey = null;
    private String paraKey = null;

    protected Statement mStmt;
    protected ResultSet mRs;
    protected int mErrorCode = 0;

    //Connection
    public DbAccess() {
        this.mKey = "";
        _init(this.mKey);
    }

    public DbAccess(String key) {
        this.mKey = key;
        _init(this.mKey);
    }

    private void _init(String key) {
        String dbKey;
        if (key == null || key == "") {
            dbKey = "__Default__";
        }
        else {
            dbKey = key;
        }

        oPara = DbConfig.getPara(key);
        if (oPara instanceof DataSourcePara) {
            mDataSourcePara = (DataSourcePara) oPara;

            mConverter = new BytesConverter(mDataSourcePara.ConvertInDbFrom,
                                            mDataSourcePara.ConvertInDbTo,
                                            mDataSourcePara.ConvertOutDbFrom,
                                            mDataSourcePara.ConvertOutDbTo);
        }
        else {
            mJdbcPara = (JdbcPara) oPara;

            mConverter = new BytesConverter(mJdbcPara.ConvertInDbFrom,
                                            mJdbcPara.ConvertInDbTo,
                                            mJdbcPara.ConvertOutDbFrom,
                                            mJdbcPara.ConvertOutDbTo);
        }
    }

    /*    public Connection connect() {
            //Object oPara = null;
            //Connection mConn = null;

            if (oPara instanceof DataSourcePara) {
                mConn = DbConnection.connectDataSource(mDataSourcePara);
            }else {
                mConn = DbConnection.connectJdbc(mJdbcPara);
            }
            try {
                mConn.setAutoCommit(true);
            }catch (SQLException e) {
                Log.error(e.getMessage());
                //throw new SQLException ("QUERY ERR:"+sql);
            }
            return mConn;
        }*/

    //根据配置参数，获取一个连接对象
    public static Connection getConnect(String sKey) {
        Object dbPara = DbConfig.getPara(sKey);
        Connection oConn = null;
        if (dbPara instanceof DataSourcePara) {
            oConn = DbConnection.connectDataSource( (DataSourcePara) dbPara);
        }
        else {
            oConn = DbConnection.connectJdbc( (JdbcPara) dbPara);
        }
        return oConn;
    }

    //query
    public ResultSet query(Connection mConn, String sqlIn) {
        Log.debug("SQL INPUT:" + sqlIn);
        String sql = mConverter.convertInDb(sqlIn);
        Log.debug("SQL  STMT:" + sql);

        try {
            mStmt = mConn.createStatement();
            mRs = mStmt.executeQuery(sql);
        }
        catch (SQLException e) {
            Log.error(e.getMessage());
            //throw new SQLException ("QUERY ERR:"+sql);
        }
        return mRs;
    }

    public Vector vquery(Connection mConn, String sqlIn) {
        Log.debug("SQL INPUT:" + sqlIn);
        String sql = mConverter.convertInDb(sqlIn);
        Log.debug("SQL  STMT:" + sql);

        try {
            mStmt = mConn.createStatement();
            mRs = mStmt.executeQuery(sql);
            ResultSetMetaData rsm = mRs.getMetaData();
            int count = rsm.getColumnCount();

            Vector v = new Vector();
            //加入列名
            Vector vHead = new Vector(count);
            for (int i = 1; i <= count; i++) {
                vHead.add(rsm.getColumnName(i));
            }
            Log.debug("vHead:" + vHead.toString());

            v.addElement(vHead);
            int r = 0;
            while (mRs.next()) {
                Object[] obj = new Object[count];
                for (int i = 1; i <= count; i++) {
                    obj[i - 1] = mRs.getObject(i);
                    Log.debug("Data[" + r + "][" + i + "]:" + obj[i -
                              1].toString());
                }
                v.addElement(obj);
                r++;
            }
            if (mRs != null) {
                mRs.close();
            }
            if (mStmt != null) {
                mStmt.close();
            }
            mErrorCode = 0;
            return v;
        }
        catch (SQLException e) {
            Log.error("TAE: " + e.getMessage());
        }
        return null;
    }

    /**
     * 从Vector中按列名取数据
     */
    public static Object getField(Vector vData, String colName, int rowNum) {
        if (rowNum < 1) {
            return null;
        }

        if (! (vData.get(0) instanceof java.util.Vector)) {
            return null;
        }
        Vector vHead = (Vector) vData.get(0);

        int iCol = vHead.indexOf(colName.toUpperCase());
        if (iCol < 0) {
            return null;
        }

        Object[] rowData = (Object[]) vData.get(rowNum);
        if (rowData.length > iCol) {
            return rowData[iCol];
        }
        return null;
    }

    //execute
    public int execute(Connection mConn, String sqlIn) throws SQLException {
        Log.debug("SQL INPUT:" + sqlIn);
        String sql = mConverter.convertInDb(sqlIn);
        Log.debug("SQL  STMT:" + sql);

        int count = 0;
        try {
            mStmt = mConn.createStatement();
            count = mStmt.executeUpdate(sql);
//            mConn.commit();
            if (mStmt != null) {
                mStmt.close();
            }
            return count;
        }
        catch (SQLException e) {
            Log.error("TAE: " + e.getMessage());
            throw e;
        }

    } //end execute

    /**
     * 此方法执行增删改的用户定义的多个SQL语句。
     * @param strSQL[]为一个SQL语句数组。
     * @param int[]为执行后影响的数据库记录的行数的整数数组。
     * @throws {@link DataBaseException}
     */
    public int[] executeBatch(Connection mConn, String[] strSQL) {

        int[] result = null;
        try {
            mStmt = mConn.createStatement();
            for (int i = 0; i < strSQL.length; i++) {
                String sql = mConverter.convertInDb(strSQL[i]);
                mStmt.addBatch(sql);
                Log.debug("BatchSQL[" + i + "]=" + sql);
            }
            result = mStmt.executeBatch();
            if (mStmt != null) {
                mStmt.close();
            }
        }
        catch (SQLException e) {
            Log.error("TAE: " + e.getMessage());
        }

        for (int i = 0; i < result.length; i++) {
            Log.debug("SQL Execute[" + i + "]=" + strSQL[i] + " rows= " +
                      result[i]);
        }
        return result;
    }

    //insert

    //close
    public void close() {
        try {
            if (mRs != null) {
                mRs.close();
            }
            if (mStmt != null) {
                mStmt.close();
            }
            //m_con.close();
        }
        catch (SQLException e) {
            Log.error("TAE: " + e.getMessage());
        }
    } //close

    public String convertInDb(String strIn) {
        return (mConverter.convertInDb(strIn));
    }

    public String convertOutDb(String strIn) {
        return (mConverter.convertOutDb(strIn));
    }

    public static void main(String[] args) {
        System.out.println("Begin test");
        DbAccess mDbAccess = new DbAccess();
        Connection objConn = DbAccess.getConnect("");
        String tableName = "personInfo";
        String tableCol = "*";
        String sql = "select  " + tableCol + " from " + tableName;

        Vector v = mDbAccess.vquery(objConn, sql);
        try {
            if (objConn != null) {
                objConn.commit();
                objConn.close();
            }
        }
        catch (Exception e) {
            Log.error("TAE: " + e.getMessage());
        }

        System.out.println("Out = " + v);

    }
}
