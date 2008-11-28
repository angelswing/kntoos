package com.conant.ums.util;

/**
 * <p> ˵����
 *         �ṩ����MessageResources���壬ȡ�ö�Ӧ����Ϣ
 * </p>
 */
public class GetMsg {
	private static Config cfg = new Config("MessageResources");

    /**
     *ȡ�������ü���ֵ�ķ�������ȡ�������ü���ֵ�����ݡ�
     *@param String key�������ļ��ļ�����
     *@return String value��Ϊ�ü���ֵ��
     */
    public static String getMsg(String key) {
		//��ȡMessageResources������Ϣ��
	    //Config cfg = new Config("MessageResources");
        return (String)cfg.getValue(key);
    }

    /**
     *ȡ�������ü���ֵ�ķ�������ȡ�������ü���ֵ�����ݡ�
     *@param String key�������ļ��ļ�����
     *@return String value��Ϊ�ü���ֵ��
     */
    public static String getMsg(String resources, String key) {
		//��ȡMessageResources������Ϣ��
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
