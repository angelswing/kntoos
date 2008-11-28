package com.conant.ums.util;

import org.apache.commons.logging.LogFactory;

/**
 * <p> ˵���������Ǽ�¼ϵͳ��־�͵�����Ϣ��־��ģ�飬��������Ϣ�����ļ�log.properties�� ����ϵͳ��־�� ���÷���: writeMsg(String prefix,int level, String str)��
 * ����prefixΪ��־�ļ�����ǰ׺��������־�ļ�����ȫ������Ϊ��user_2006-07-27.log����ʱǰ׺Ϊ��user���� ����level��˵����log.properties��ϵͳ��־���ټ���˵���� ����strΪϵͳ��־��Ϣ��
 * �򻯵��÷���: write(String str) write(int level, String str) ���ڵ�����־�� ���Ե��÷���1��writeDebug(String prefix,int level, String str)��
 * ���Ե��÷���1��writeDebug(int level, String str)�� </p>
 */
public class Log {
    private static org.apache.commons.logging.Log log = LogFactory.getLog(com.conant.ums.util.Log.class);
    private static org.apache.commons.logging.Log specialLog = LogFactory.
        getLog("specialLogger");

    /**
     *дϵͳ��־��Ϣ����������Ϣд����־��¼�ĵ���
     *@param str����־��Ϣ��
     */
    public synchronized static void debug(String str) {
        log.debug(str);
    }

    /**
     *дϵͳ��־��Ϣ����������Ϣд����־��¼�ĵ���
     *@param str����־��Ϣ��
     */
    public synchronized static void error(String str) {
        log.error(str);
    }

    /**
     *дϵͳ��־��Ϣ�������ش�����Ϣд����־��¼�ĵ���
     *@param str����־��Ϣ��
     */
    public synchronized static void fatal(String str) {
        log.fatal(str);
    }

    /**
     *дϵͳ��־��Ϣ����һ����Ϣд����־��¼�ĵ���
     *@param str����־��Ϣ��
     */
    public synchronized static void info(String str) {
        log.info(str);
    }

    /**
     *дϵͳ��־��Ϣ����������Ϣд���������־��¼�ĵ���
     *@param str����־��Ϣ��
     */
    public synchronized static void specialLog(String str) {
        specialLog.info(str);
    }

    /** �����÷����� */
    public static void main(String[] args) {
        Log.debug("zhangji's test log!!!");
        Log.error("zhangji's error log!!!");
        Log.fatal("zhangji's fatal log!!!");
        Log.info("zhangji's info log!!!");
        Log.specialLog("special log test");
    }
}
