package com.conant.ums.interfaces;

/**
 * <p>Title: 系统操作类</p>
 * <p>Description: 提供给用户管理子系统查询的操作信息</p>
 * <p>Copyright:</p>
 * <p>Company:</p>
 * @author
 * @version 1.0
 */
public class OperationInfo {
  /**
   * 操作ID
   */
  private long operId;
  /**
   * 操作名称
   */
  private String operName;
  /**
   * 父操作ID
   * 如果没有父操作则为 0
   */
  private long parentId;
  /**
   * 操作类型，判断该操作是否是树的叶子结点
   * 0:叶子结点
   * 1:枝结点
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
