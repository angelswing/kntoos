package com.conant.ums.interfaces;

import java.util.List;

/**
 * <p>Title: 外部资源注入接口</p>
 * <p>Description: 定义了操作资源、实体资源的获取接口</p>
 * <p>Copyright:</p>
 * <p>Company: </p>
 * @author
 * @version 1.0
 */
public interface ExternResource {
    /**
     * 获取所有的业务实体信息
     * @return List　业务实体列表, 根节点的父节点ID为"-1"
     * 　　　　　　　　具体类型为"com.conant.ums.interfaces.EntityInfo"
     * @throws Exception
     */
    public List getAllEntity() throws Exception;
    /**
     * 获取所有操作接口
     * @return List　操作信息列表, 根节点的父节点ID为"-1"
     * 　　　　　　　　具体类型为"com.conant.ums.interfaces.OperationInfo"
     * @throws Exception
     */
    public List getAllOperation() throws Exception;
}
