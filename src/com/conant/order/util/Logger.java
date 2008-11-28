package com.conant.order.util;

import java.util.*;
import java.io.*;
import java.text.*;

/**
 * <p>Title: Online-Order System</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: </p>
 *
 * @author Martin
 * @version 1.0
 */


/**
 * Description:    ��־�����
 *  1.��־��Ϣ�ֵȼ�,�ӵ͵�������Ϊ��DEBUG,INFO,WARN,ERROR����
 *    ���ڹ������ʱ������ʱ�ı������������־�ȼ�����ֻ����ȼ���WARN���ϵ���־��Ϣ
 *  2.���Խ���־������ļ���ͬʱ���������Ļ
 *  3.��־�ļ������Ŀ¼Ĭ��ΪϵͳTEMPĿ¼
 *  4.��־�ļ�ÿ�����һ����������ļ���С�ﵽĳһ����ֵ(20M)����������һ���ļ�
 * �����ʹ�÷�����
 *  ��һ������ȡ��־����: getLogger;
 *  �ڶ��������������־��Ϣ�� debug,info,warn,error,exception;
 *                            debugT,infoT,warnT,errorT
 *  ���������ͷ���־����releaseLogger;
 * ʾ������ main()
 */

public class Logger implements java.io.Serializable {
    //��־����
    public static final int ALL = 0;
    public static final int DEBUG = ALL + 1;
    public static final int INFO = DEBUG + 1;
    public static final int WARN = INFO + 1;
    public static final int ERROR = WARN + 1;
    public static final int NONE = 0x7fffffff;

    private static final String[] LOG_LEVEL = {
                                              "ALL",
                                              "DEBUG",
                                              "INFO",
                                              "WARN",
                                              "ERROR"
    };
    private SimpleDateFormat formatter = new SimpleDateFormat(
            "HH:mm:ss");

    private SimpleDateFormat fileformatter = new SimpleDateFormat(
            "yyyyMMdd-HHmmss");

    private File file = null;
    private long MAX_FILE_SIZE = 20480000; // the maximum size of each log file

    private PrintWriter fileWriter;

    private String logDir = null;

    private int CUR_DATE;

    private String logName = "syslog";

    private int logLevel = ALL; // default loglevel

    private boolean toScreen = true; //��־��Ϣ�Ƿ��������Ļ

    private static final HashMap loggers = new HashMap();

    /**
     * �����־����
     * @param logName ��־�������ƣ��磺MyLOg,ConfigLOg,...
     * @param logLevel�����������͵ȼ�����ЧȡֵΪ��ALL,DEBUG,INFO,WARN,ERROR
     * @param toScreen �Ƿ��������Ļ
     * @return ��־����
     */
    public static synchronized Logger getLogger(String logName, int logLevel,
                                                boolean toScreen) {
        Logger logger = (Logger) loggers.get(logName);
        if (logger == null) {
            logger = new Logger(logName, logLevel, toScreen);
            loggers.put(logName, logger);
        } else {
            logger.setLogLevel(logLevel);
        }
        return logger;
    }

    /**
     * �����־����
     * @param logDir ��־�ļ���ŵ�Ŀ¼
     * @param logName ��־�������ƣ��磺MyLOg,ConfigLOg,...
     * @param logLevel�����������͵ȼ�����ЧȡֵΪ��ALL,DEBUG,INFO,WARN,ERROR
     * @param toScreen �Ƿ��������Ļ
     * @return ��־����
     */
    public static synchronized Logger getLogger(String logDir, String logName,
                                                int logLevel, boolean toScreen) {
        Logger logger = (Logger) loggers.get(logName);
        if (logger == null) {
            logger = new Logger(logName, logLevel, toScreen);
            logger.logDir = logDir;
            loggers.put(logName, logger);
        }
        return logger;

    }

    /**
     * �ͷ���־����
     * @param logger ��־����
     */
    public static void releaseLogger(Logger logger) {
        if (logger != null) {
            logger.close();
            loggers.remove(logger.logName);
        }
    }

    /**
     * �ͷ����е���־����
     */
    public static void releaseAllLogger() {
        Iterator iter = Logger.loggers.values().iterator();
        while (iter.hasNext()) {
            Logger logger = (Logger) iter.next();
            logger.close();
        }
        Logger.loggers.clear();
    }

    /**
     * ��ȡ��־�ļ����Ŀ¼
     * @return
     */
    private String getLogDir() {
        return EnvironmentHelper.getInstance().getHomePath() + File.separator +
                "log"
                + File.separator + this.logName;
        /*
         return System.getProperty( "user.home" ) + File.separator + "log"
                    + File.separator + "srv" + File.separator + this.logName;
         */
    }

    private void close() {
        if (this.fileWriter != null) {
            fileWriter.close();
        }
    }

    /**
     * ���캯���������಻�ܹ������־����ֻ��ͨ��
     * @param logFileName  ��־�������ƣ��磺MyLOg,ConfigLOg,...
     * @param logLevel �����������͵ȼ�����ЧȡֵΪ��ALL,DEBUG,INFO,WARN,ERROR
     * @param toScreen �Ƿ��������Ļ
     */
    private Logger(String logFileName, int logLevel, boolean toScreen) {
        logName = logFileName;

        logDir = getLogDir();

        if (logLevel > ERROR || logLevel < ALL)
            this.logLevel = ALL;
        else
            this.logLevel = logLevel;

        this.toScreen = toScreen;

        setNewWriter();
    }

    /**
     * ���һ���� YYYYMMDD|20031105�İ�λ����
     * @return
     */
    private int getCurrDateDigital() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String date = df.format(Calendar.getInstance().getTime());
        int curDate = 0;
        try {
            curDate = Integer.parseInt(date);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return curDate;
    }

    /**
     * �Ƿ���Ҫ������һ����־�ļ�
     * ����������µ�һ�죬������־�ļ���С�ﵽ������ֵ������Ҫ������һ����־�ļ�
     * @return
     */
    private boolean isNewLogFileRequired() {
        if (file == null)
            return false;
        else
            return (getCurrDateDigital() > CUR_DATE) ||
                    (file.length() > MAX_FILE_SIZE);
    }

    /**
     * ����һ���µ�PrintWriter���󣬹�д��־��
     */
    private synchronized void setNewWriter() {
        if (fileWriter != null)
            fileWriter.close();

        if (getCurrDateDigital() > CUR_DATE) {
            CUR_DATE = getCurrDateDigital();

            //logDir = logDir + File.separator + logName;

            new File(logDir).mkdirs();
        } else {
            String nfile = logDir + File.separator +
                           fileformatter.format(Calendar.getInstance().getTime()) +
                           ".log";
            file.renameTo(new File(nfile));
        }

        String logFile = logDir + File.separator + CUR_DATE + ".log";
        if (this.toScreen == true) {
            System.out.println("LOG FILE:" + logFile);
        }

        try {
            file = new File(logFile);
            fileWriter = new PrintWriter(new FileWriter(file, true));
        } catch (IOException ex) {
            System.err.println("Fatal Error [" + ex.toString() + "]!" +
                               " cannot write to the log file [" + logFile +
                               "].");
            System.err.println("System.out is used.");
            fileWriter = new PrintWriter(System.out);
        }
    }

    /**
     * �����־����־�ļ�(����Ļ)
     * @param message  Ҫ�������־��Ϣ
     * @param logLevel ��־��Ϣ�ȼ�
     * @param timeMark �Ƿ����ʱ����
     */
    private synchronized void log(Object message, int logLevel,
                                  boolean timeMark) {
        if (this.logLevel > logLevel || fileWriter == null)
            return;

        if (isNewLogFileRequired()) {
            setNewWriter();
        }
        //����ʱ��
        String buff;
        if (timeMark == true) {
            buff = formatter.format(Calendar.getInstance().getTime())
                   + "[" + LOG_LEVEL[logLevel] + "]";
        } else
            buff = "";

        buff += message;

        if (this.toScreen) {
            if (logLevel >= WARN)
                System.err.println(buff);
            else
                System.out.println(buff);
        }

        //д���ļ�
        fileWriter.println(buff);
        fileWriter.flush();
    }

    /**
     * �����ʱ���ǵ�DEUBG�������־��Ϣ
     * @param message Ҫ�������־��Ϣ
     */
    public void debugT(Object message) {
        log(message, DEBUG, true);
    }

    /**
     * �������ʱ���ǵ�DEUBG�������־��Ϣ
     * @param message Ҫ�������־��Ϣ
     */
    public void debug(Object message) {
        log(message, DEBUG, false);
    }

    /**
     * �����ʱ���ǵ�INFO�������־��Ϣ
     * @param message Ҫ�������־��Ϣ
     */
    public void infoT(Object message) {
        log(message, INFO, true);
    }

    /**
     * �������ʱ���ǵ�INFO�������־��Ϣ
     * @param message Ҫ�������־��Ϣ
     */
    public void info(Object message) {
        log(message, INFO, false);
    }

    /**
     * �����ʱ���ǵ�WARN�������־��Ϣ
     * @param message Ҫ�������־��Ϣ
     */
    public void warnT(Object message) {
        log(message, WARN, true);
    }

    /**
     * �������ʱ���ǵ�WARN�������־��Ϣ
     * @param message Ҫ�������־��Ϣ
     */
    public void warn(Object message) {
        log(message, WARN, false);
    }

    /**
     * �����ʱ���ǵ�ERROR�������־��Ϣ
     * @param message Ҫ�������־��Ϣ
     */
    public void errorT(Object message) {
        log(message, ERROR, true);
    }

    /**
     * �������ʱ���ǵ�ERROR�������־��Ϣ
     * @param message Ҫ�������־��Ϣ
     */
    public void error(Object message) {
        log(message, ERROR, false);
    }

    /**
     * �����ʱ���ǵ�Exception��ϸ��Ϣ
     * @param throwable �쳣����
     */
    public void exception(Throwable throwable) {
        if (fileWriter == null || logLevel == NONE)
            return;

        if (isNewLogFileRequired()) {
            setNewWriter();
        }

        String buff = formatter.format(Calendar.getInstance().getTime());

        buff += "[EXCEPTION] Thr:" + Thread.currentThread().getName();

        if (this.toScreen) {
            System.err.println(buff);
            throwable.printStackTrace();
        }

        fileWriter.println(buff);
        throwable.printStackTrace(fileWriter);
        fileWriter.flush();

    }

    public void setToScreen(boolean toScreen) {
        this.toScreen = toScreen;
    }

    public void setLogLevel(int logLevel) {
        this.logLevel = logLevel;
    }

    public int getLogLevel() {
        return logLevel;
    }

    public static void main(String[] args) {
        //Step 1: ��ȡ��־����
        Logger logger = Logger.getLogger("mylog", ALL, true);

        //Step 2: ������ּ������־��Ϣ
        logger.debugT("here is debug log with timemark");
        logger.debug("Here is debug log without timemark");

        logger.infoT("here is info log with timemark");
        logger.info("Here is info log without timemark");

        logger.warnT("here is warn log with timemark");
        logger.warn("Here is warn log without timemark");

        logger.errorT("Here is error log with timemark");
        logger.error("Here is error log without timemark");

        try {
            int[] a = null;
            a[0] = 0;
        } catch (Exception e) {
            //����쳣��Ϣ
            logger.exception(e);
        }

        //Step 3: �ͷ���־����
        Logger.releaseLogger(logger);
    }
}
