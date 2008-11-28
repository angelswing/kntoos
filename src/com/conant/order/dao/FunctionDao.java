package com.conant.order.dao;

import java.util.List;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.*;

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
public interface FunctionDao {

    /**
     * ���ӹ�����
     * @param func_Info, ������VO
     * @param loginId,ϵͳ��½ID
     * @return
     * @throws ProcessException
     */
    public void insertFunctionInfo(String loginId,FunctionInfo func_Info) throws ProcessException;

    /**
     * ���ӹ����������
     * @param list ��װ������VO��List
     * @param loginId,ϵͳ��½ID
     * @return
     * @throws ProcessException
     */
    public void insertFunctionInfos(String loginId,List list) throws ProcessException;


    /**
     * ɾ�������������
     * @param list ��װ������VO��List
     * @param loginId,ϵͳ��½ID
     * @return
     * @throws ProcessException
     */
    public void delFunctionInfos(String loginId,List list) throws ProcessException;

    /**
     * ɾ��������
     * @param func_Info ������VO
     * @param loginId,ϵͳ��½ID
     * @return
     * @throws ProcessException
     */
    public void delFunctionInfo(String loginId,FunctionInfo func_Info) throws ProcessException;

    /**
     * �޸Ĺ�����
     * @param func_Info ������VO
     * @param loginId,ϵͳ��½ID
     * @return
     * @throws ProcessException
     */
    public void updateFunctionInfo(String loginId,FunctionInfo func_Info) throws ProcessException;

    /**
     * ��ѯ��������Ϣ
     * @param loginId,ϵͳ��½ID
     * @return ��Ź�����vo�����list����
     * @throws ProcessException
     */
    public List getFunctionInfoList(String longId) throws ProcessException;

    /**
    * ��ѯ��������Ϣ
    * @param id, ������id
    * @param loginId,ϵͳ��½ID
    * @return FunctionInfo
    * @throws ProcessException
    */
   public FunctionInfo getFunctionInfo(String longId,long id) throws ProcessException;
   
	/**
	 * ����pid��ѯ�������б�
	 * 
	 * @param pid
	 *            , ������pid
	 * @param loginId
	 *            ,ϵͳ��½ID
	 * @return List
	 * @throws ProcessException
	 */   
   public List getFunctionListByPid(String longId, long pid) throws ProcessException;

}
