package com.conant.ums.util;

import java.util.*;

/**
 * <p> 读取配置文件的配置信息，配置文件的文件名格式：fileName.properties。文件内容格式：stringKey=value。 </p>
 */
public class Config {
    private Hashtable ht = new Hashtable();

    /**
     *构造方法，获取配置文件的内容。
     *@param String configFileName，配置文件的文件名，注意，不含“.properties”部分。
     */
    public Config() {
        _Config("config");
    }

    public Config(String configName) {
        _Config(configName);
    }

    private void _Config(String configFileName) {
        try {
            ResourceBundle resources = ResourceBundle.getBundle(configFileName,
                Locale.getDefault());
            Enumeration enumeration = resources.getKeys();
            while (enumeration.hasMoreElements()) {
                String key = (String) enumeration.nextElement();
                String value = resources.getString(key).trim();
                ht.put(key, value);
            }
        }
        catch (MissingResourceException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     *取单个配置键的值的方法：获取单个配置键的值的内容。
     *@param String key，配置文件的键名。
     *@return String value，为该键的值。
     */
    public String getValue(String key) {
        return (String) ht.get(key);
    }

    public Object getObject(String key) {
        return ht.get(key);
    }

    /**
     *取整个配置文件的内容。
     *@return Hashtable，为该配置文件的全部内容的hash表。
     */
    public Hashtable getConfig() {
        return ht;
    }

    public static void main(java.lang.String[] args) {
        String configName;
        String key;
        String value;
        Hashtable mHt;
        Config mCfg;
        System.out.println("Begin load Config");
        //mCfg = new Config("config");
        mCfg = new Config();
        key = "DB.Username";
        value = mCfg.getValue(key);
        System.out.println("key=" + key + ";value=" + value);
        mHt = mCfg.getConfig();
        Enumeration enumeration = mHt.keys();
        while (enumeration.hasMoreElements()) {
            key = (String) enumeration.nextElement();
            value = (String) mHt.get(key);
            System.out.println("key=" + key + ";value=" + value);
        }
    }
}
