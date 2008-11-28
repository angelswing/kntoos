package com.conant.ums.interfaces;

import java.util.List;

import com.conant.ums.data.F130_UserMgt;

/**
 * <p>Title: �û���Ȩ����ӿ�</p>
 * <p>Description: �������û�����Ȩ�ޡ�ʵ��Ȩ����֤�����ӿ�</p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author
 * @version 1.0
 */
public interface AuthService {
    /**
     * ��ȡ���и��û�������ҵ��ʵ����Ϣ
     * @return List���û�ҵ��ʵ���б�,����Ϊ:String entiId
     * param user_tag���û���½�ʺ�
     * @throws Exception
     */
    public List entiList(String userTag) throws Exception;

    /**
     * ��ȡ���и��û�������ϵͳ������Ϣ
     * @return List���û�ϵͳ�����б�,����Ϊ:String operId
     * param user_tag���û���½�ʺ�
     * @throws Exception
     */
    public List operList(String userTag) throws Exception;

    /**
     * ҵ��ʵ��Ȩ�޵���֤����
     * @return true or fasle
     * @param enti_id��ҵ��ʵ��id, user_tag���û���½�ʺ�
     * @throws Exception
     */
    public boolean isEnti(String entiId, String userTag) throws Exception;

    /**
     * �û�����Ȩ�޵���֤����
     * @return true or fasle
     * @param oper_id��ϵͳ����id, user_tag���û���½�ʺ�
     * @throws Exception
     */
    public boolean isOper(String operId, String userTag) throws Exception;

    /**
     * �û�����Ȩ�޵���֤����
     * @return true or fasle
     * @param enti_id��ҵ��ʵ��id, oper_id��ϵͳ����id, user_tag���û���½�ʺ�
     * @throws Exception
     */
    public boolean isEntiOPer(String entiId, String operId, String userTag) throws
        Exception;

    /**
     * �û�����
     * @return 5λ��������ɹ�, 0���û������ڻ��������, -1���û�������, -2���û�ip������, -3���û�ʱ��������, -4:�����ǳ�ʼ����
     * @param usertag���û���½�ʺ�, passwd���û�����, ip:�û���½ip
     * @throws Exception
     */
    public int login(String usertag, String passwd, String ip) throws Exception;

    /**
     * �û�����
     * @return �û���Ϣ����F130_UserMgt
     * @param usertag���û���½�ʺ�
     * @throws Exception
     */
    public F130_UserMgt loginData(String usertag) throws Exception;

    /**
     * �û��ǳ�
     * @param loginId����½�󷵻ص�5λ�����
     * @throws Exception
     */
    public void logout(String loginId) throws Exception;

    /**
     * �޸�����
     * @return < 0:���������
     * @param usertag���û���½�ʺ�, passwd���û�����
     * @throws Exception
     */
    public int changePass(String usertag, String oldPasswd, String newPasswd) throws Exception;

    /**
     * �޸��û���Ϣ
     * @return < 0:�޸�ʧ��
     * @param usertag���û���½�ʺ�, UserInfo���û���Ϣ
     * @throws Exception
     */
    public int changeUserInfo(String usertag, UserInfo userInfo) throws Exception;

    /**
     * ��ȡ���в�����Ϣ
     * @return List�������б�
     * @throws Exception
     */
    public List deptGroup() throws Exception;

}
