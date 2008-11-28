package com.conant.order.common;

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

public class PageMsg {
  /**
   * 页面呈现的信息，主要是错误页面或者成功页面的提示信息
   */
  private String msg;
  /**
   * 页面的返回链接URL，供用户从错误页面和成功页面返回
   */
  private String url;

  private String target;

  public PageMsg() {
  }

  public String getMsg() {
    return msg;
  }

  public String getUrl() {
    return url;
  }

  public String getTarget() {
    return target;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void setTarget(String target) {
    this.target = target;
  }
}
