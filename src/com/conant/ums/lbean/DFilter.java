package com.conant.ums.lbean;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class DFilter
    extends BaseLBean implements Filter {
    private static final String[] nochecks = {
        "/welcome.jsp","user/login.jsp","log/"
    }; 

    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException,
        ServletException {
        try {
            if (req instanceof HttpServletRequest &&
                res instanceof HttpServletResponse) {
                HttpServletRequest request = (HttpServletRequest) req;
                HttpServletResponse response = (HttpServletResponse) res;
                HttpSession session = request.getSession(true);
                //session检测，过期就重新登录
                if (session.getAttribute("LoginData") == null) {
                    String uri = request.getRequestURI();
                    boolean bcheck = true;
                    for (int i = 0; i < nochecks.length; i++) {
                        if (uri.indexOf(nochecks[i]) >= 0) {
                            bcheck = false;
                        }
                    }
                    if (bcheck) {
                        log.debug("filtering: " + uri + "?" +
                                  request.getQueryString());
                        String cpath = request.getContextPath();
                        //"http://"+request.getServerName()+":"+request.getServerPort()
                        response.sendRedirect(cpath + "/user/timeout.html");
                        return;
                    }
                }
                /*
                 * //stream filter FilterOWrapper fresp = new FilterOWrapper(response);
                 * chain.doFilter(request, fresp);
                                             fresp.closeFilter();
                 */
            }
            chain.doFilter(req, res);
            //wrong ways:
            //request.getRequestDispatcher("/error.jsp").forward(request,
            // response);
            //res.sendRedirect(req.getRequestURI());
        }
        catch (Exception e) {
            log.debug(e);
        }
    }

    //for filter
    protected FilterConfig filterConfig;

    public void init(FilterConfig filterconfig) {
        filterConfig = filterconfig;
    }

    public FilterConfig getFilterConfig() {
        return filterConfig;
    }

    public void setFilterConfig(FilterConfig filterconfig) {
        filterConfig = filterconfig;
    }

    public void destroy() {
        log.debug("Filter Event: Destroy");
        return;
    }
}
