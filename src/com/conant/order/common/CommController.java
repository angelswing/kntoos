package com.conant.order.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.conant.order.dao.FunctionDao;
import com.conant.order.util.Logger;
import com.conant.order.util.ProcessException;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;



/**
 * <p>Title: Online-Order System</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: </p>
 *
 * @author Martin
 * @version 1.0
 */

public class CommController extends AbstractController {

    private static Logger log = Logger.getLogger("commController", Logger.DEBUG,true);
    private FunctionDao functionDao;
    

    protected ModelAndView handleRequestInternal(HttpServletRequest request,
                                                 HttpServletResponse response
            ) throws Exception {

        String targetURL = null;
        targetURL = request.getParameter("targetURL");
        String loginId = (String) request.getSession().getAttribute("loginId");
        List list = null;
        if("login".equals(targetURL)){
            list = functionDao.getFunctionInfoList(loginId);
            if(null != list && list.size() > 0){
                //log.debugT("function list size ==="+list.size());
                SessionMap.getOneTreeInstance().add(list);
            }
        }
        return new ModelAndView(targetURL);

    }

	public void setFunctionDao(FunctionDao functionDao) throws ProcessException {
		this.functionDao = functionDao;
	} 
}
