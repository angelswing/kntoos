package com.conant.ums.interfaces;

import java.util.List;

/**
 * <p>Title: �ⲿ��Դע��ӿ�</p>
 * <p>Description: �����˲�����Դ��ʵ����Դ�Ļ�ȡ�ӿ�</p>
 * <p>Copyright:</p>
 * <p>Company: </p>
 * @author
 * @version 1.0
 */
public interface ExternResource {
    /**
     * ��ȡ���е�ҵ��ʵ����Ϣ
     * @return List��ҵ��ʵ���б�, ���ڵ�ĸ��ڵ�IDΪ"-1"
     * ������������������������Ϊ"com.conant.ums.interfaces.EntityInfo"
     * @throws Exception
     */
    public List getAllEntity() throws Exception;
    /**
     * ��ȡ���в����ӿ�
     * @return List��������Ϣ�б�, ���ڵ�ĸ��ڵ�IDΪ"-1"
     * ������������������������Ϊ"com.conant.ums.interfaces.OperationInfo"
     * @throws Exception
     */
    public List getAllOperation() throws Exception;
}
