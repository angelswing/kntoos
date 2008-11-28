package com.conant.order.web.form;

/**
 * <p>Title: Online Order Management System</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: conant</p>
 *
 * @author Martin
 * @version 1.0
 */

public class LoginForm
{

    private long id;
    private String userName;
    private String password;
    private String valiCode;

    public void setValiCode(String valiCode)
    {
        this.valiCode = valiCode;
    }

    public String getValiCode()
    {
        return valiCode;
    }

    public long getId()
    {
        return id;
    }

    public String getPassword()
    {
        return password;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }
}
