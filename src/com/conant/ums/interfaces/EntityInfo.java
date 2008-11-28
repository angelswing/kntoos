package com.conant.ums.interfaces;

/**
 * <p>Title: ϵͳҵ��ʵ����</p>
 * <p>Description: �ṩ���û�������ϵͳ��ѯ��ҵ��ʵ����Ϣ</p>
 * <p>Copyright:</p>
 * <p>Company:</p>
 * @author
 * @version 1.0
 */
public class EntityInfo {
    /**
     * ʵ��ID
     */
    private long entityId;
    /**
     * ʵ������
     */
    private String entityName;
    /**
     * ��ʵ��ID
     * ���û�и�ʵ����Ϊ 0
     */
    private long parentId;
    /**
     * ʵ�����ͣ��жϸ�ʵ���Ƿ�������Ҷ�ӽ��
     * 0:Ҷ�ӽ��
     * 1:֦���
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
