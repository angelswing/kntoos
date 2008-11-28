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
     * 增加功能项
     * @param func_Info, 功能项VO
     * @param loginId,系统登陆ID
     * @return
     * @throws ProcessException
     */
    public void insertFunctionInfo(String loginId,FunctionInfo func_Info) throws ProcessException;

    /**
     * 增加功能项（批量）
     * @param list 封装功能项VO的List
     * @param loginId,系统登陆ID
     * @return
     * @throws ProcessException
     */
    public void insertFunctionInfos(String loginId,List list) throws ProcessException;


    /**
     * 删除功能项（批量）
     * @param list 封装功能项VO的List
     * @param loginId,系统登陆ID
     * @return
     * @throws ProcessException
     */
    public void delFunctionInfos(String loginId,List list) throws ProcessException;

    /**
     * 删除功能项
     * @param func_Info 功能项VO
     * @param loginId,系统登陆ID
     * @return
     * @throws ProcessException
     */
    public void delFunctionInfo(String loginId,FunctionInfo func_Info) throws ProcessException;

    /**
     * 修改功能项
     * @param func_Info 功能项VO
     * @param loginId,系统登陆ID
     * @return
     * @throws ProcessException
     */
    public void updateFunctionInfo(String loginId,FunctionInfo func_Info) throws ProcessException;

    /**
     * 查询功能项信息
     * @param loginId,系统登陆ID
     * @return 存放功能项vo对象的list集合
     * @throws ProcessException
     */
    public List getFunctionInfoList(String longId) throws ProcessException;

    /**
    * 查询功能项信息
    * @param id, 功能项id
    * @param loginId,系统登陆ID
    * @return FunctionInfo
    * @throws ProcessException
    */
   public FunctionInfo getFunctionInfo(String longId,long id) throws ProcessException;
   
	/**
	 * 根据pid查询功能项列表
	 * 
	 * @param pid
	 *            , 功能项pid
	 * @param loginId
	 *            ,系统登陆ID
	 * @return List
	 * @throws ProcessException
	 */   
   public List getFunctionListByPid(String longId, long pid) throws ProcessException;

}
