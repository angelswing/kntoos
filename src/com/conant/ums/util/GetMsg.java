package com.conant.ums.util;

/**
 * <p> 说明：
 *         提供根据MessageResources定义，取得对应的信息
 * </p>
 */
public class GetMsg {
	private static Config cfg = new Config("MessageResources");

    /**
     *取单个配置键的值的方法：获取单个配置键的值的内容。
     *@param String key，配置文件的键名。
     *@return String value，为该键的值。
     */
    public static String getMsg(String key) {
		//读取MessageResources设置信息。
	    //Config cfg = new Config("MessageResources");
        return (String)cfg.getValue(key);
    }

    /**
     *取单个配置键的值的方法：获取单个配置键的值的内容。
     *@param String key，配置文件的键名。
     *@return String value，为该键的值。
     */
    public static String getMsg(String resources, String key) {
		//读取MessageResources设置信息。
	    Config cofig = new Config(resources);
        return (String)cofig.getValue(key);
    }

    public static void main(java.lang.String[] args) {
        String key;
        String value;
        String outValue;
        key = "com.add.success";
        value = BytesConverter.asc2gb(GetMsg.getMsg(key));

        System.out.println("key=" + key + ";value=" + value);

		key = "com.update.success";
        value = GetMsg.getMsg("MessageResources",key);
        outValue = BytesConverter.asc2gb(value);
        //outValue = BytesConverter.convert(value,"GB2312");

        System.out.println("key=" + key + ";value=" + value + ";outValue=" + outValue);

		key = "com.select.fail";
        value = GetMsg.getMsg("MessageResources",key);
        outValue = BytesConverter.asc2gb(value);

        System.out.println("key=" + key + ";value=" + value + ";outValue=" + outValue);

    }

}
