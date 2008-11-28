package com.conant.ums.db;

public class JdbcPara
    extends BaseDbPara {
    String DbKey;
    String DbDriver;
    String DbUrl;
    String UserName;
    String Password;
    boolean Encrypt;
    String ConvertInDbFrom;
    String ConvertInDbTo;
    String ConvertOutDbFrom;
    String ConvertOutDbTo;
    int InitPoolSize;
    int MaxPoolSize;
    int Expired;

    public JdbcPara() {
        DbKey = "__Default__";
        DbDriver = null;
        DbUrl = null;
        UserName = null;
        Password = null;
        Encrypt = false;

        ConvertInDbFrom = null;
        ConvertInDbTo = null;
        ConvertOutDbFrom = null;
        ConvertOutDbTo = null;

        InitPoolSize = 1;
        MaxPoolSize = 10;
        Expired = 60;
    }

    public JdbcPara(String key) {
        DbKey = key;
        DbDriver = null;
        DbUrl = null;
        UserName = null;
        Password = null;
        Encrypt = false;

        ConvertInDbFrom = null;
        ConvertInDbTo = null;
        ConvertOutDbFrom = null;
        ConvertOutDbTo = null;

        InitPoolSize = 1;
        MaxPoolSize = 10;
        Expired = 60;
    }
}
