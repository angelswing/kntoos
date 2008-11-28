package com.conant.order.web.controller;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.conant.order.dao.*;
import com.conant.order.util.Logger;
import com.conant.order.util.ProcessException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import com.conant.ums.interfaces.AuthService;
import com.conant.ums.lbean.Validate;
import com.conant.order.vo.FunctionInfo;
import com.conant.ums.data.F180_OperMgt;

public class FunctionController extends AbstractController {

    private static Logger log = Logger.getLogger("functionController", Logger.DEBUG,
                                                 true);
    private FunctionDao functionDao;

    protected ModelAndView handleRequestInternal(HttpServletRequest request,
                                                 HttpServletResponse response
            ) throws Exception {

        String targetURL = null;
        AuthService as = new Validate();
        List list = null;
        List tempList = null;
        List arrayList = new ArrayList();
        FunctionInfo functionInfo = null;


        targetURL = request.getParameter("targetURL");
        String userName = (String) request.getSession().getAttribute("user_name");
        String loginId = (String) request.getSession().getAttribute("loginId");

        try {
            list = as.operList(userName);//操作权限表
            tempList = functionDao.getFunctionInfoList(loginId);
            if (null != tempList && tempList.size() > 0) {
                for (int i = 0; i < tempList.size(); i++) {
                	functionInfo = (FunctionInfo)tempList.get(i);
                    if (null != list && list.size() > 0) { //和权限列表比较
                        for (int j = 0; j < list.size(); j++) {
                            String operId = ((F180_OperMgt) list.get(j)).getOperId();
                            if (operId.equals(String.valueOf(functionInfo.getId()))) {
                                //System.out.println("operId["+j+"]==="+operId + ";" + String.valueOf(functionInfo.getId()));
                                arrayList.add(functionInfo);
                            }
                        }
                    }
                }//比较完成

            }
            return new ModelAndView(targetURL, "success", arrayList);
        } catch (ProcessException pe) {
            return new ModelAndView(targetURL);
        } catch (Exception ex) {
            return new ModelAndView(targetURL);
        }

    }

	public void setFunctionDao(FunctionDao functionDao) {
		this.functionDao = functionDao;
	}
}
