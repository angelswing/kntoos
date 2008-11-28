package com.conant.order.dao.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import com.conant.order.dao.FunctionDao;
import com.conant.order.util.Logger;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.FunctionInfo;

public class FunctionDaoIMP implements FunctionDao {

	private static Logger log = Logger.getLogger("FunctionDaoIMP",
			Logger.DEBUG, true);
	private HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) throws ProcessException {
		this.template = template;
	}

	/**
	 * ɾ��������
	 * 
	 * @param func_Info
	 *            ������VO
	 * @param loginId
	 *            ,ϵͳ��½ID
	 * @return
	 * @throws ProcessException
	 */
	public void delFunctionInfo(String loginId, FunctionInfo func_Info)
			throws ProcessException {
		try {
			if (null != func_Info) {
				template.delete(func_Info);
			} else {
				throw new ProcessException(217001);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * ɾ�������������
	 * 
	 * @param list
	 *            ��װ������VO��List
	 * @param loginId
	 *            ,ϵͳ��½ID
	 * @return
	 * @throws ProcessException
	 */
	public void delFunctionInfos(String loginId, List list)
			throws ProcessException {
		try {
			if (null != list && list.size() > 0) {
				template.deleteAll(list);
			} else {
				throw new ProcessException(217001);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * ��ѯ��������Ϣ
	 * 
	 * @param id
	 *            , ������id
	 * @param loginId
	 *            ,ϵͳ��½ID
	 * @return FunctionInfo
	 * @throws ProcessException
	 */
	public FunctionInfo getFunctionInfo(String longId, long id)
			throws ProcessException {
		FunctionInfo func_Info = null;
		String hql = "from FunctionInfo m where m.id = " + id;
		List list = template.find(hql);
		if (list.size() == 1) {
			func_Info = (FunctionInfo) list.get(0);
		}
		return func_Info;
	}

	
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
	public List getFunctionListByPid(String longId, long pid)
			throws ProcessException {
		String hql = "from FunctionInfo m where m.func_Pid = " + pid;
		List list = template.find(hql);
		return list;
	}
	
	
	/**
	 * ��ѯ��������Ϣ
	 * 
	 * @param loginId
	 *            ,ϵͳ��½ID
	 * @return ��Ź�����vo�����list����
	 * @throws ProcessException
	 */
	public List getFunctionInfoList(String longId) throws ProcessException {
		List list = template.find("from FunctionInfo order by id");
		return list;
	}

	/**
	 * ���ӹ�����
	 * 
	 * @param func_Info
	 *            , ������VO
	 * @param loginId
	 *            ,ϵͳ��½ID
	 * @return
	 * @throws ProcessException
	 */
	public void insertFunctionInfo(String loginId, FunctionInfo func_Info)
			throws ProcessException {
		try {
			if (null != func_Info) {
				template.save(func_Info);
			} else {
				throw new ProcessException(217001);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProcessException(120001);
		}

	}

	/**
	 * ���ӹ����������
	 * 
	 * @param list
	 *            ��װ������VO��List
	 * @param loginId
	 *            ,ϵͳ��½ID
	 * @return
	 * @throws ProcessException
	 */
	public void insertFunctionInfos(String loginId, List list)
			throws ProcessException {
		try {
			if (null != list && list.size() > 0) {
				template.saveOrUpdateAll(list);
			} else {
				throw new ProcessException(217001);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * �޸Ĺ�����
	 * 
	 * @param func_Info
	 *            ������VO
	 * @param loginId
	 *            ,ϵͳ��½ID
	 * @return
	 * @throws ProcessException
	 */
	public void updateFunctionInfo(String loginId, FunctionInfo func_Info)
			throws ProcessException {
		try {
			if (null != func_Info) {
				template.update(func_Info);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
