package com.conant.ums.db;

import java.sql.*;
import java.util.Hashtable;
import javax.naming.*;
import javax.sql.DataSource;

import com.conant.ums.util.Log;

/**
 * <p>
 *说明：本类是数据库访问接口DataBaseAcc的父类，它提供了数据库连接的基本API。
 *</p>
 *<p>
 *日期：
 *作者：
 *</p>
 **/
public class DbConnection {
    private static Hashtable htDS = new Hashtable();
    //private static DataSourcePara mDataSourcePara = null;
    //private static JdbcPara mJdbcPara = null;

    //DataSourceConnection
    public static Connection connectDataSource(DataSourcePara mDataSourcePara) {
        DataSource ds = null;
        String dsKey = null;
        Connection mConn = null;

        ds = (DataSource) htDS.get(mDataSourcePara.DbKey);
        if (ds == null) {
            //mDataSourcePara = DbConfig.getDataSourcePara(key);

            Hashtable ht = new Hashtable();
            ht.put(Context.INITIAL_CONTEXT_FACTORY, mDataSourcePara.InitContext);
            ht.put(Context.PROVIDER_URL, mDataSourcePara.ProviderUrl);
            try {
                Context ctx = new InitialContext(ht);
                ds = (DataSource) ctx.lookup(mDataSourcePara.DataSource);
                htDS.put(mDataSourcePara.DbKey, ds);
            }
            catch (Exception e) {
                Log.error("DataSourceConnection Error:" + e.getMessage());
            }

        }

        try {
            Log.debug("In DbConnection UserName=" + mDataSourcePara.UserName +
                      ";length=" + mDataSourcePara.UserName.length());

            if (mDataSourcePara.UserName == null ||
                mDataSourcePara.UserName == "") {
                mConn = ds.getConnection();
            }
            else {
                mConn = ds.getConnection(mDataSourcePara.UserName,
                                         mDataSourcePara.Password);
            }
            return (mConn);
        }
        catch (SQLException e) {
            Log.error("DataSourceConnection Error:" + e.getMessage());
        }
        return (mConn);
    }

    //JdbcConnection
    public static Connection connectJdbc(JdbcPara mJdbcPara) {
        Connection mConn = null;
        Log.debug("classpath is " + System.getProperty("java.class.path"));
        Log.debug("In connectJdbc,mJdbcPara =" + mJdbcPara);
        try {
            Class.forName(mJdbcPara.DbDriver);
        }
        catch (ClassNotFoundException e) {
            Log.error("Can't find Class name for Database Driver: " +
                      mJdbcPara.DbDriver);
            //throw new SQLException("Can't find Class name for Database Driver: " + mJdbcPara.DbDriver);
        }
        try {
            mConn = DriverManager.getConnection(mJdbcPara.DbUrl,
                                                mJdbcPara.UserName,
                                                mJdbcPara.Password);
            return (mConn);
        }
        catch (SQLException e) {
            Log.error("GetConnection error: DbUrl=" + mJdbcPara.DbUrl
                      + ";UserName=" + mJdbcPara.UserName + ";Password=" +
                      mJdbcPara.Password);
            //throw new SQLException("Can't find Class name for Database Driver: " + mJdbcPara.DbDriver);
        }

        return (mConn);
    }

}
