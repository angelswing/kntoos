package com.conant.ums.lbean;

import org.apache.commons.logging.*;

/**
 * @author:raokun
 *
 */
public class BaseLBean {
    public Log log, specialLog;

    public BaseLBean() {
        log = LogFactory.getLog(this.getClass());
        specialLog = LogFactory.getLog("specialLogger");
    }

}
