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
   * ҳ����ֵ���Ϣ����Ҫ�Ǵ���ҳ����߳ɹ�ҳ�����ʾ��Ϣ
   */
  private String msg;
  /**
   * ҳ��ķ�������URL�����û��Ӵ���ҳ��ͳɹ�ҳ�淵��
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
