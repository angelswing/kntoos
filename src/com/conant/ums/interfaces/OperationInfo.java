package com.conant.ums.interfaces;

/**
 * <p>Title: ϵͳ������</p>
 * <p>Description: �ṩ���û�������ϵͳ��ѯ�Ĳ�����Ϣ</p>
 * <p>Copyright:</p>
 * <p>Company:</p>
 * @author
 * @version 1.0
 */
public class OperationInfo {
  /**
   * ����ID
   */
  private long operId;
  /**
   * ��������
   */
  private String operName;
  /**
   * ������ID
   * ���û�и�������Ϊ 0
   */
  private long parentId;
  /**
   * �������ͣ��жϸò����Ƿ�������Ҷ�ӽ��
   * 0:Ҷ�ӽ��
   * 1:֦���
   */
  private int operType;

  public OperationInfo() {
  }

    public long getOperId() {
        return operId;
    }

    public String getOperName() {
        return operName;
    }

    public int getOperType() {
        return operType;
    }

    public long getParentId() {
        return parentId;
    }

    public void setOperId(long operId) {
        this.operId = operId;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public void setOperType(int operType) {
        this.operType = operType;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }
}
