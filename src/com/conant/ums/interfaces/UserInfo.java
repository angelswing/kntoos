package com.conant.ums.interfaces;

public class UserInfo
{
    private String mobile;
    private String email;
    private String address;
    private String home_tel;
    public UserInfo()
    {
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setHome_tel(String home_tel)
    {
        this.home_tel = home_tel;
    }

    public String getMobile()
    {
        return mobile;
    }

    public String getEmail()
    {
        return email;
    }

    public String getAddress()
    {
        return address;
    }

    public String getHome_tel()
    {
        return home_tel;
    }

}
