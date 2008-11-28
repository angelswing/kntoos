package com.conant.ums.db;

import java.util.Hashtable;

import com.conant.ums.util.*;

/**
 * <p> 说明：
 *         从db.properties取得数据库连接的配置信息
 * </p>
 */
public class DbConfig {
    private static Config cfg = new Config("db");
    private static Hashtable htPara = new Hashtable();

    public static Object getPara() {
        return (getPara(""));
    }

    public static Object getPara(String key) {
        Object oPara = null;

        String tmpKey = null;
        String DbKey = null;
        if (key == "") {
            tmpKey = "";
            DbKey = "__Default__";
        }
        else {
            tmpKey = key + ".";
            DbKey = key;
        }

        oPara = htPara.get(DbKey);
        if (oPara == null) {
            String mType = cfg.getValue(tmpKey + "Type");

            if (mType.equalsIgnoreCase("datasource")) {
                //如果是通过datasource访问数据库
                oPara = getDataSourcePara(key);
            }
            else {
                //如果是通过jdbc驱动访问数据库
                oPara = getJdbcPara(key);
            }
            htPara.put(DbKey, oPara);
        }
        return (oPara);
    }

    /**
     *取应用服务器数据源的配置方法：以缺省数据源的名称取得对应的配置信息。
     *@return DataSourcePara value，为数据源配置的值。
     */
    public static DataSourcePara getDataSourcePara() {
        return (DbConfig.getDataSourcePara(""));
    }

    /**
     *取应用服务器数据源的配置方法：以该数据源的名称取得对应的配置信息。
     *@param String key，数据源的键名。
     *@return DataSourcePara value，为数据源配置的值。
     */
    public static DataSourcePara getDataSourcePara(String key) {
        DataSourcePara mDataSourcePara = new DataSourcePara(key);
        //配置属性前缀
        String tmpKey = null;

        //System.out.println( "key=" + key);

        if (key == "") {
            tmpKey = "";
            mDataSourcePara.DbKey = "__Default__";
        }
        else {
            tmpKey = key + ".";
            mDataSourcePara.DbKey = key;
        }

        //System.out.println( "tmpKey=" + tmpKey);

        String mType = cfg.getValue(tmpKey + "Type");
        /*
             if(!mType.equals("datasource")){
              //System.out.println( "mType=" + mType);
              return (null);
             }
         */

        mDataSourcePara.InitContext = cfg.getValue(tmpKey + "InitContext");
        mDataSourcePara.ProviderUrl = cfg.getValue(tmpKey + "ProviderUrl");
        mDataSourcePara.DataSource = cfg.getValue(tmpKey + "DataSource");

        mDataSourcePara.UserName = cfg.getValue(tmpKey + "UserName");
        //mDataSourcePara.Password 	= cfg.getValue(tmpKey+"Password");
        mDataSourcePara.Encrypt = new Boolean(cfg.getValue(tmpKey + "Encrypt")).
            booleanValue();

        if (mDataSourcePara.Encrypt) {
            mDataSourcePara.Password = Decode.decrypt(cfg.getValue(tmpKey +
                "Password"));
        }
        else {
            mDataSourcePara.Password = cfg.getValue(tmpKey + "Password");
        }

        if (mDataSourcePara.UserName == null ||
            mDataSourcePara.UserName.length() == 0) {
            mDataSourcePara.UserName = "";
            Log.debug(tmpKey + "UserName is null!  ");
        }
        else {
            Log.debug(tmpKey + "UserName length is : " +
                      mDataSourcePara.UserName.length());
        }

        /** Get ConvertInDb config **/
        String ConvertInDb;
        String ConvertInDbFrom;
        String ConvertInDbTo;

        ConvertInDb = cfg.getValue(tmpKey + "ConvertInDb");
        //Log.debug(">>>>ConvertInDb = " + ConvertInDb);
        if (ConvertInDb == null || ConvertInDb.length() == 0) {
            ConvertInDbFrom = null;
            ConvertInDbTo = null;
        }
        else {
            int iD = ConvertInDb.indexOf(",");
            //Log.debug(">>>>iD =" + iD);
            if (iD == -1) {
                ConvertInDbFrom = null;
                ConvertInDbTo = ConvertInDb;
            }
            else if (iD == 0) {
                ConvertInDbFrom = null;
                ConvertInDbTo = ConvertInDb.substring(iD + 1);
            }
            else {
                ConvertInDbFrom = ConvertInDb.substring(0, iD);
                ConvertInDbTo = ConvertInDb.substring(iD + 1);
            }
        }

        mDataSourcePara.ConvertInDbFrom = ConvertInDbFrom;
        mDataSourcePara.ConvertInDbTo = ConvertInDbTo;

        /** Get ConvertOutDb config **/
        String ConvertOutDb;
        String ConvertOutDbFrom;
        String ConvertOutDbTo;

        ConvertOutDb = cfg.getValue(tmpKey + "ConvertOutDb");
        //Log.debug("ConvertOutDb = " + ConvertOutDb);
        if (ConvertOutDb == null || ConvertOutDb.length() == 0) {
            ConvertOutDbFrom = null;
            ConvertOutDbTo = null;
        }
        else {
            int iD = ConvertOutDb.indexOf(",");
            Log.debug("iD =" + iD);
            if (iD == -1) {
                ConvertOutDbFrom = null;
                ConvertOutDbTo = ConvertOutDb;
            }
            else if (iD == 0) {
                ConvertOutDbFrom = null;
                ConvertOutDbTo = ConvertOutDb.substring(iD + 1);
            }
            else {
                ConvertOutDbFrom = ConvertOutDb.substring(0, iD);
                ConvertOutDbTo = ConvertOutDb.substring(iD + 1);
            }
        }

        mDataSourcePara.ConvertOutDbFrom = ConvertOutDbFrom;
        mDataSourcePara.ConvertOutDbTo = ConvertOutDbTo;

        Log.debug(tmpKey + "DbKey=" + mDataSourcePara.DbKey);
        Log.debug(tmpKey + "InitContext=" + mDataSourcePara.InitContext);
        Log.debug(tmpKey + "ProviderUrl=" + mDataSourcePara.ProviderUrl);
        Log.debug(tmpKey + "DataSource=" + mDataSourcePara.DataSource);
        Log.debug(tmpKey + "UserName=" + mDataSourcePara.UserName);
        Log.debug(tmpKey + "Password=" + mDataSourcePara.Password);
        Log.debug(tmpKey + "Encrypt=" + mDataSourcePara.Encrypt);

        Log.debug(tmpKey + "ConvertInDbFrom=" + mDataSourcePara.ConvertInDbFrom);
        Log.debug(tmpKey + "ConvertInDbTo=" + mDataSourcePara.ConvertInDbTo);
        Log.debug(tmpKey + "ConvertOutDbFrom=" +
                  mDataSourcePara.ConvertOutDbFrom);
        Log.debug(tmpKey + "ConvertOutDbTo=" + mDataSourcePara.ConvertOutDbTo);

        return (mDataSourcePara);
    }

    /**
     *取JDBC的配置方法：以缺省JDBC的名称取得对应的配置信息。
     *@return JdbcPara value，为数据源配置的值。
     */
    public static JdbcPara getJdbcPara() {
        JdbcPara mJdbcPara = new JdbcPara();

        mJdbcPara = DbConfig.getJdbcPara("");
        return (mJdbcPara);
    }

    /**
     *取JDBC的配置方法：以该JDBC的名称取得对应的配置信息。
     *@param String key，JDBC的键名。
     *@return JdbcPara value，为数据源配置的值。
     */
    public static JdbcPara getJdbcPara(String key) {
        JdbcPara mJdbcPara = new JdbcPara(key);
        //配置属性前缀
        String tmpKey = null;

        if (key == "") {
            tmpKey = "";
            mJdbcPara.DbKey = "__Default__";
        }
        else {
            tmpKey = key + ".";
            mJdbcPara.DbKey = key;
        }

        //System.out.println( "tmpKey=" + tmpKey);

        String mType = cfg.getValue(tmpKey + "Type");
        /*
             if(!mType.equals("jdbc")){
              //System.out.println( "mType=" + mType);
              return (null);
             }
         */
        mJdbcPara.DbDriver = cfg.getValue(tmpKey + "DbDriver");
        mJdbcPara.DbUrl = cfg.getValue(tmpKey + "DbUrl");
        mJdbcPara.UserName = cfg.getValue(tmpKey + "UserName");
        //mJdbcPara.Password 	= cfg.getValue(tmpKey+"Password");
        mJdbcPara.Encrypt = new Boolean(cfg.getValue(tmpKey + "Encrypt")).
            booleanValue();

        if (mJdbcPara.Encrypt) {
            mJdbcPara.Password = Decode.decrypt(cfg.getValue(tmpKey +
                "Passord"));
        }
        else {
            mJdbcPara.Password = cfg.getValue(tmpKey + "Password");
        }

        mJdbcPara.InitPoolSize = new Integer(cfg.getValue(tmpKey +
            "InitPoolSize")).intValue();
        mJdbcPara.MaxPoolSize = new Integer(cfg.getValue(tmpKey + "MaxPoolSize")).
            intValue();
        mJdbcPara.Expired = new Integer(cfg.getValue(tmpKey + "Expired")).
            intValue();

        if (mJdbcPara.UserName == null || mJdbcPara.UserName.length() == 0) {
            mJdbcPara.UserName = "";
            Log.debug(tmpKey + "UserName is null!  ");
        }
        else {
            Log.debug(tmpKey + "UserName length is : " +
                      mJdbcPara.UserName.length());
        }

        /** Get ConvertInDb config **/
        String ConvertInDb;
        String ConvertInDbFrom;
        String ConvertInDbTo;

        ConvertInDb = cfg.getValue(tmpKey + "ConvertInDb");
        if (ConvertInDb == null || ConvertInDb.length() == 0) {
            ConvertInDbFrom = null;
            ConvertInDbTo = null;
        }
        else {
            int iD = ConvertInDb.indexOf(",");
            if (iD == -1) {
                ConvertInDbFrom = null;
                ConvertInDbTo = ConvertInDb;
            }
            else if (iD == 0) {
                ConvertInDbFrom = null;
                ConvertInDbTo = ConvertInDb.substring(iD + 1);
            }
            else {
                ConvertInDbFrom = ConvertInDb.substring(0, iD);
                ConvertInDbTo = ConvertInDb.substring(iD + 1);
            }
        }

        mJdbcPara.ConvertInDbFrom = ConvertInDbFrom;
        mJdbcPara.ConvertInDbTo = ConvertInDbTo;

        /** Get ConvertOutDb config **/
        String ConvertOutDb;
        String ConvertOutDbFrom;
        String ConvertOutDbTo;

        ConvertOutDb = cfg.getValue(tmpKey + "ConvertOutDb");
        if (ConvertOutDb == null || ConvertOutDb.length() == 0) {
            ConvertOutDbFrom = null;
            ConvertOutDbTo = null;
        }
        else {
            int iD = ConvertOutDb.indexOf(",");
            if (iD == -1) {
                ConvertOutDbFrom = null;
                ConvertOutDbTo = ConvertOutDb;
            }
            else if (iD == 0) {
                ConvertOutDbFrom = null;
                ConvertOutDbTo = ConvertOutDb.substring(iD + 1);
            }
            else {
                ConvertOutDbFrom = ConvertOutDb.substring(0, iD);
                ConvertOutDbTo = ConvertOutDb.substring(iD + 1);
            }
        }

        mJdbcPara.ConvertOutDbFrom = ConvertOutDbFrom;
        mJdbcPara.ConvertOutDbTo = ConvertOutDbTo;

        Log.debug(tmpKey + "DbKey=" + mJdbcPara.DbKey);
        Log.debug(tmpKey + "DbDriver=" + mJdbcPara.DbDriver);
        Log.debug(tmpKey + "DbUrl=" + mJdbcPara.DbUrl);
        Log.debug(tmpKey + "UserName=" + mJdbcPara.UserName);
        Log.debug(tmpKey + "Password=" + mJdbcPara.Password);
        Log.debug(tmpKey + "Encrypt=" + mJdbcPara.Encrypt);
        Log.debug(tmpKey + "InitPoolSize=" + mJdbcPara.InitPoolSize);
        Log.debug(tmpKey + "MaxPoolSize=" + mJdbcPara.MaxPoolSize);
        Log.debug(tmpKey + "Expired=" + mJdbcPara.Expired);

        Log.debug(tmpKey + "ConvertInDbFrom=" + mJdbcPara.ConvertInDbFrom);
        Log.debug(tmpKey + "ConvertInDbTo=" + mJdbcPara.ConvertInDbTo);
        Log.debug(tmpKey + "ConvertOutDbFrom=" + mJdbcPara.ConvertOutDbFrom);
        Log.debug(tmpKey + "ConvertOutDbTo=" + mJdbcPara.ConvertOutDbTo);

        return (mJdbcPara);
    }

    public static void main(java.lang.String[] args) {
        String key;
        DataSourcePara mDataSourcePara;
        JdbcPara mJdbcPara;
        Object oPara;
        /*
               mDataSourcePara = DbConfig.getDataSourcePara();
               if(mDataSourcePara == null){
                mJdbcPara = DbConfig.getJdbcPara();
               }

               mDataSourcePara = DbConfig.getDataSourcePara("Test1");
               if(mDataSourcePara == null){
                mJdbcPara = DbConfig.getJdbcPara("Test1");
               }
         */
        oPara = DbConfig.getPara();
        if (oPara instanceof DataSourcePara) {
            Log.debug("oPara is DataSourcePara");
        }

        if (oPara instanceof JdbcPara) {
            Log.debug("oPara is JdbcPara");
        }

        oPara = DbConfig.getPara("Test1");
        if (oPara instanceof DataSourcePara) {
            Log.debug("oPara is DataSourcePara");
        }

        if (oPara instanceof JdbcPara) {
            Log.debug("oPara is JdbcPara");
        }

    }

}
