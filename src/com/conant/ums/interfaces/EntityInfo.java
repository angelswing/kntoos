package com.conant.ums.interfaces;

/**
 * <p>Title: 系统业务实体类</p>
 * <p>Description: 提供给用户管理子系统查询的业务实体信息</p>
 * <p>Copyright:</p>
 * <p>Company:</p>
 * @author
 * @version 1.0
 */
public class EntityInfo {
    /**
     * 实体ID
     */
    private long entityId;
    /**
     * 实体名称
     */
    private String entityName;
    /**
     * 父实体ID
     * 如果没有父实体则为 0
     */
    private long parentId;
    /**
     * 实体类型，判断该实体是否是树的叶子结点
     * 0:叶子结点
     * 1:枝结点
     */
    private int entityType;


    public EntityInfo() {
    }

    public String getEntityName() {
        return entityName;
    }

    public int getEntityType() {
        return entityType;
    }

    public long getParentId() {
        return parentId;
    }

    public long getEntityId() {
        return entityId;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public void setEntityType(int entityType) {
        this.entityType = entityType;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public void setEntityId(long entityId) {
        this.entityId = entityId;
    }
}
