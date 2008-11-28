package com.conant.ums.data;

import java.io.Serializable;

import com.conant.ums.util.SysTime;

public class BaseData
    implements Serializable {
    private String add_date;
    private String add_time;
    private String add_userid;
    private String upd_date;
    private String upd_time;
    private String upd_userid;
    private int curLineNo;

    public BaseData() {
        setDateTime();
    }

    public String getAdd_date() {
        return add_date;
    }

    public void setAdd_date(String add_date) {
        this.add_date = add_date;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getAdd_userid() {
        return add_userid;
    }

    public void setAdd_userid(String add_userid) {
        this.add_userid = add_userid;
    }

    public String getUpd_date() {
        return upd_date;
    }

    public void setUpd_date(String upd_date) {
        this.upd_date = upd_date;
    }

    public String getUpd_time() {
        return upd_time;
    }

    public void setUpd_time(String upd_time) {
        this.upd_time = upd_time;
    }

    public String getUpd_userid() {
        return upd_userid;
    }

    public void setUpd_userid(String upd_userid) {
        this.upd_userid = upd_userid;
    }

    //设置当前日期及时间@raokun:2006-08-08
    public void setDateTime() {
        SysTime sysTime = new SysTime();
        add_date = sysTime.getSysDate();
        add_time = sysTime.getSysTime();
        upd_date = sysTime.getSysDate();
        upd_time = sysTime.getSysTime();
    }

    public int getCurLineNo() {
        return curLineNo;
    }

    public void setCurLineNo(int curLineNo) {
        this.curLineNo = curLineNo;
    }

}
