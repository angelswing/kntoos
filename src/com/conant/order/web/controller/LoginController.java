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

    //�ύ������
    protected ModelAndView onSubmit(
            HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors) throws ProcessException,
            Exception {
        //0:��ʼ����
    	
        LoginForm loginInfo = (LoginForm) command;
        PageMsg msg = new PageMsg();
        msg.setUrl("login.ord");
        msg.setTarget("_self");
        
        try {
            //1:��ȡsession
            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(10 * 60);
            String ip = request.getRemoteAddr();
            if (ip == null) {
                ip = "IPδ֪";
            }

            //2:�����֤��������
//            if (!loginInfo.getValiCode().equals(session.getAttribute("rand"))) {
//                msg.setMsg("��֤������ʧЧ");
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

            //3:�����¼�������
            // @return 1���ɹ�, 0���û������ڻ��������, -1���û�������,
            // -2���û�ip������, -3���û�ʱ��������
//            System.out.println("result==="+result);
            if (result <= 0) {
                String info = "��¼ʧ��";
                switch (result) {
                case 0:
                    info = "�û������ڻ��������";
                    break;
                case -1:
                    info = "�û�������";
                    break;
                case -2:
                    info = "�û�IP������";
                    break;
                case -3:
                    info = "�û�ʱ��������";
                    break;
                case -4:
                    return new ModelAndView("reset_password", "userId",
                                            loginInfo.getUserName());
                default:
                    info = "��¼ʧ��";
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

            //4:���session�д���loginId,����ո�session�е���������
            try {
                if (session.getAttribute("loginId") != null) {
                    //�����˳��ӿ�
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
            //5:�����������
            session.setAttribute("cmdMap", map);
            session.setAttribute("user_name", loginInfo.getUserName()); //�û��ʺ�
            session.setAttribute("loginId", loginId);

            session.setAttribute("userName", userData.getUser_name()); //user_name�û�����
            session.setAttribute("email", userData.getEmail()); //email����
            session.setAttribute("address", userData.getAddress()); //address��ַ
            session.setAttribute("mobile", userData.getMobile()); //mobile�ֻ�
            session.setAttribute("home_tel", userData.getHome_tel()); //home_tel��ͥ�绰
            session.setAttribute("dept_name", userData.getDept_name()); //dept_name��������
            session.setAttribute("area", userData.getArea()); //dept_name��������

            session.setAttribute("ip", ip);
            if (session.getAttribute("refreshpage") != null) {
                session.removeAttribute("refreshpage");
            }
            SessionMap.getOneInstance().put(loginId, session);

            //6:д��־
//            LogFactory.getLogProcessor().writeOperateLog(401002,
//                    loginInfo.getUserName(), ip, true, "��¼�ɹ�");

            //7:����
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
