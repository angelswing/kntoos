package com.conant.order.web.controller;


import java.util.*;
import javax.servlet.http.*;
import org.springframework.validation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.mvc.*;
import com.conant.order.util.Logger;
import com.conant.order.web.form.LoginForm;
import com.conant.order.util.ProcessException;
import com.conant.order.common.PageMsg;
import com.conant.ums.interfaces.AuthService;
import com.conant.ums.lbean.Validate;
import com.conant.ums.data.F130_UserMgt;
import com.conant.ums.data.F180_OperMgt;
import com.conant.order.common.SessionMap;

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
public class LoginController extends SimpleFormController {


    private static Logger log = Logger.getLogger("login", Logger.DEBUG,
                                                 true);
//    private CpeProcessorItf cpeItf;

    public LoginController() {
        this.setCommandClass(LoginForm.class);
        this.setCacheSeconds(100);
    }

    //提交表单动作
    protected ModelAndView onSubmit(
            HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors) throws ProcessException,
            Exception {
        //0:初始设置
    	
        LoginForm loginInfo = (LoginForm) command;
        PageMsg msg = new PageMsg();
        msg.setUrl("login.ord");
        msg.setTarget("_self");
        
        try {
            //1:获取session
            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(10 * 60);
            String ip = request.getRemoteAddr();
            if (ip == null) {
                ip = "IP未知";
            }

            //2:检查验证码和密码等
//            if (!loginInfo.getValiCode().equals(session.getAttribute("rand"))) {
//                msg.setMsg("验证码错误或失效");
//                return new ModelAndView("common/err", "error", msg);
//            }
            //check username and password
            AuthService as = new Validate();

            if (null == loginInfo.getUserName()
                || "".equals(loginInfo.getUserName().trim())) {
                msg.setMsg("Please input user name!");

                return new ModelAndView("common/err", "error", msg);
            }
            if (null == loginInfo.getPassword()
                || "".equals(loginInfo.getPassword().trim())) {
                msg.setMsg("Please input password");
                return new ModelAndView("common/err", "error", msg);
            }
            int result = as.login(loginInfo.getUserName().trim(),
                                  loginInfo.getPassword().trim(), ip);

            F130_UserMgt userData = as.loginData(loginInfo.getUserName().trim());

            //3:处理登录结果数据
            // @return 1：成功, 0：用户不存在或密码错误, -1：用户被锁定,
            // -2：用户ip受限制, -3：用户时间受限制
//            System.out.println("result==="+result);
            if (result <= 0) {
                String info = "登录失败";
                switch (result) {
                case 0:
                    info = "用户不存在或密码错误";
                    break;
                case -1:
                    info = "用户被锁定";
                    break;
                case -2:
                    info = "用户IP受限制";
                    break;
                case -3:
                    info = "用户时间受限制";
                    break;
                case -4:
                    return new ModelAndView("reset_password", "userId",
                                            loginInfo.getUserName());
                default:
                    info = "登录失败";
                }
                msg.setMsg(info);
                return new ModelAndView("common/err", "error", msg);
            }
            //cmdlist
            List list = as.operList(loginInfo.getUserName());
            HashMap map = new HashMap();
            for (int i = 0; i < list.size(); i++) {
                map.put(((F180_OperMgt) list.get(i)).getOperId(),
                        Integer.valueOf("1"));

            }
            String loginId = String.valueOf(result);

            //4:如果session中存在loginId,则清空该session中的所有内容
            try {
                if (session.getAttribute("loginId") != null) {
                    //调用退出接口
                    as.logout((String) session.getAttribute("loginId"));
                    //
                    SessionMap.getOneInstance().remove((String) session.
                            getAttribute("loginId"));
                    //write log
                    String user_name = (String) session.getAttribute(
                            "user_name");

                    Enumeration en = session.getAttributeNames();
                    String e = "";
                    while (en.hasMoreElements()) {
                        e = (String) en.nextElement();
                        session.removeAttribute(e);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //5:设置相关属性
            session.setAttribute("cmdMap", map);
            session.setAttribute("user_name", loginInfo.getUserName()); //用户帐号
            session.setAttribute("loginId", loginId);

            session.setAttribute("userName", userData.getUser_name()); //user_name用户姓名
            session.setAttribute("email", userData.getEmail()); //email邮箱
            session.setAttribute("address", userData.getAddress()); //address地址
            session.setAttribute("mobile", userData.getMobile()); //mobile手机
            session.setAttribute("home_tel", userData.getHome_tel()); //home_tel家庭电话
            session.setAttribute("dept_name", userData.getDept_name()); //dept_name部门名称
            session.setAttribute("area", userData.getArea()); //dept_name部门名称

            session.setAttribute("ip", ip);
            if (session.getAttribute("refreshpage") != null) {
                session.removeAttribute("refreshpage");
            }
            SessionMap.getOneInstance().put(loginId, session);

            //6:写日志
//            LogFactory.getLogProcessor().writeOperateLog(401002,
//                    loginInfo.getUserName(), ip, true, "登录成功");

            //7:返回
//            System.out.println("login id ===="+session.getAttribute("loginId"));

            return new ModelAndView(this.getSuccessView());
        } catch (ProcessException e) {
            msg.setMsg(e.getErrorReason());
            return new ModelAndView("common/err", "error", msg);
        }
    }


//    public void setCpeItf(CpeProcessorItf cpeItf)
//    {
//        this.cpeItf = cpeItf;
//    }

}
