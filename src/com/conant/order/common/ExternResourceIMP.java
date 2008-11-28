package com.conant.order.common;


import java.util.*;
import com.conant.ums.interfaces.ExternResource;
import com.conant.order.util.Logger;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.*;
import com.conant.ums.interfaces.OperationInfo;

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

public class ExternResourceIMP implements ExternResource {

    private static Logger log = Logger.getLogger("ExternResourceIMP",
                                                 Logger.DEBUG,
                                                 true);
	
    /**
     *
     * @return List　业务实体列表, 根节点的父节点ID为"#"
     *   　　　　　　　　具体类型为"com.foxconn.cmmsg.user.interfaces.EntityInfo"
     * @throws Exception
     * @todo Implement this com.foxconn.cmmsg.user.interfaces.ExternResource method
     */
    public List getAllEntity() throws Exception {
        return null;
    }
    /**
     *
     * @return List　操作信息列表, 根节点的父节点ID为"#"
     *   　　　　　　　　具体类型为"com.foxconn.cmmsg.user.interfaces.OperationInfo"
     * @throws Exception
     * @todo Implement this com.foxconn.cmmsg.user.interfaces.ExternResource method
     */
    public List getAllOperation() throws Exception {

        List list = new ArrayList();
        List tempList = null;
        FunctionInfo functionInfo = null;
        OperationInfo oper = null;

        tempList = (List) SessionMap.getOneTreeInstance().get(0);

        if(null != tempList && tempList.size() > 0){
            //log.debugT("tempList size==="+tempList.size());
            for(int i = 0;i < tempList.size();i ++){
            	functionInfo = (FunctionInfo)tempList.get(i);
                oper = new OperationInfo();
                oper.setOperId(functionInfo.getId());
                oper.setOperName(functionInfo.getFunc_Name());
                oper.setOperType(functionInfo.getFunc_Type());
                oper.setParentId(functionInfo.getFunc_Pid());
                list.add(oper);
            }
        }else{
            log.errorT("Have not get menu list!");
        }

        return list;
    }
}
