package com.conant.ums.db;

public class DataSourcePara
    extends BaseDbPara {
    String DbKey;
    String InitContext;
    String ProviderUrl;
    String DataSource;
    String UserName;
    String Password;
    boolean Encrypt;
    String ConvertInDbFrom;
    String ConvertInDbTo;
    String ConvertOutDbFrom;
    String ConvertOutDbTo;

    public DataSourcePara() {
        DbKey = "__Default__";
        InitContext = null;
        ProviderUrl = null;
        DataSource = null;
        UserName = null;
        Password = null;
        Encrypt = false;

        ConvertInDbFrom = null;
        ConvertInDbTo = null;
        ConvertOutDbFrom = null;
        ConvertOutDbTo = null;
    }

    public DataSourcePara(String key) {
        DbKey = key;
        InitContext = null;
        ProviderUrl = null;
        DataSource = null;
        UserName = null;
        Password = null;
        Encrypt = false;

        ConvertInDbFrom = null;
        ConvertInDbTo = null;
        ConvertOutDbFrom = null;
        ConvertOutDbTo = null;
    }
}
