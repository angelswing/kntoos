package com.conant.ums.interfaces;

import java.util.List;

import com.conant.ums.data.F130_UserMgt;

/**
 * <p>Title: 用户鉴权服务接口</p>
 * <p>Description: 定义了用户操作权限、实体权限验证方法接口</p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author
 * @version 1.0
 */
public interface AuthService {
    /**
     * 获取所有该用户所属的业务实体信息
     * @return List　用户业务实体列表,类型为:String entiId
     * param user_tag：用户登陆帐号
     * @throws Exception
     */
    public List entiList(String userTag) throws Exception;

    /**
     * 获取所有该用户所属的系统操作信息
     * @return List　用户系统操作列表,类型为:String operId
     * param user_tag：用户登陆帐号
     * @throws Exception
     */
    public List operList(String userTag) throws Exception;

    /**
     * 业务实体权限的验证方法
     * @return true or fasle
     * @param enti_id：业务实体id, user_tag：用户登陆帐号
     * @throws Exception
     */
    public boolean isEnti(String entiId, String userTag) throws Exception;

    /**
     * 用户操作权限的验证方法
     * @return true or fasle
     * @param oper_id：系统操作id, user_tag：用户登陆帐号
     * @throws Exception
     */
    public boolean isOper(String operId, String userTag) throws Exception;

    /**
     * 用户操作权限的验证方法
     * @return true or fasle
     * @param enti_id：业务实体id, oper_id：系统操作id, user_tag：用户登陆帐号
     * @throws Exception
     */
    public boolean isEntiOPer(String entiId, String operId, String userTag) throws
        Exception;

    /**
     * 用户登入
     * @return 5位随机数：成功, 0：用户不存在或密码错误, -1：用户被锁定, -2：用户ip受限制, -3：用户时间受限制, -4:密码是初始密码
     * @param usertag：用户登陆帐号, passwd：用户密码, ip:用户登陆ip
     * @throws Exception
     */
    public int login(String usertag, String passwd, String ip) throws Exception;

    /**
     * 用户登入
     * @return 用户信息对象：F130_UserMgt
     * @param usertag：用户登陆帐号
     * @throws Exception
     */
    public F130_UserMgt loginData(String usertag) throws Exception;

    /**
     * 用户登出
     * @param loginId：登陆后返回的5位随机数
     * @throws Exception
     */
    public void logout(String loginId) throws Exception;

    /**
     * 修改密码
     * @return < 0:旧密码错误
     * @param usertag：用户登陆帐号, passwd：用户密码
     * @throws Exception
     */
    public int changePass(String usertag, String oldPasswd, String newPasswd) throws Exception;

    /**
     * 修改用户信息
     * @return < 0:修改失败
     * @param usertag：用户登陆帐号, UserInfo：用户信息
     * @throws Exception
     */
    public int changeUserInfo(String usertag, UserInfo userInfo) throws Exception;

    /**
     * 获取所有部门信息
     * @return List　部门列表
     * @throws Exception
     */
    public List deptGroup() throws Exception;

}
