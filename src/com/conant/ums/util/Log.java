package com.conant.ums.util;

import org.apache.commons.logging.LogFactory;

/**
 * <p> 说明：本类是记录系统日志和调试信息日志的模块，其配置信息来自文件log.properties。 对于系统日志： 调用方法: writeMsg(String prefix,int level, String str)。
 * 其中prefix为日志文件名的前缀，例如日志文件名的全名可能为：user_2006-07-27.log，这时前缀为“user”。 参数level的说明见log.properties的系统日志跟踪级别说明。 参数str为系统日志消息。
 * 简化调用方法: write(String str) write(int level, String str) 对于调试日志： 可以调用方法1、writeDebug(String prefix,int level, String str)。
 * 可以调用方法1、writeDebug(int level, String str)。 </p>
 */
public class Log {
    private static org.apache.commons.logging.Log log = LogFactory.getLog(com.conant.ums.util.Log.class);
    private static org.apache.commons.logging.Log specialLog = LogFactory.
        getLog("specialLogger");

    /**
     *写系统日志信息。将调试信息写进日志记录文档。
     *@param str是日志信息。
     */
    public synchronized static void debug(String str) {
        log.debug(str);
    }

    /**
     *写系统日志信息。将错误信息写进日志记录文档。
     *@param str是日志信息。
     */
    public synchronized static void error(String str) {
        log.error(str);
    }

    /**
     *写系统日志信息。将严重错误信息写进日志记录文档。
     *@param str是日志信息。
     */
    public synchronized static void fatal(String str) {
        log.fatal(str);
    }

    /**
     *写系统日志信息。将一般信息写进日志记录文档。
     *@param str是日志信息。
     */
    public synchronized static void info(String str) {
        log.info(str);
    }

    /**
     *写系统日志信息。将特殊信息写进单另的日志记录文档。
     *@param str是日志信息。
     */
    public synchronized static void specialLog(String str) {
        specialLog.info(str);
    }

    /** 测试用方法。 */
    public static void main(String[] args) {
        Log.debug("zhangji's test log!!!");
        Log.error("zhangji's error log!!!");
        Log.fatal("zhangji's fatal log!!!");
        Log.info("zhangji's info log!!!");
        Log.specialLog("special log test");
    }
}
