package com.conant.order.common;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


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

public class SessionFilter implements Filter {
    private static final String[] nochecks = {
            "welcome.ord","login.ord"
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

                if (session.getAttribute("loginId") == null) {
                    String uri = request.getRequestURI();
                    boolean bcheck = true;
                    for (int i = 0; i < nochecks.length; i++) {
                        if (uri.indexOf(nochecks[i]) >= 0) {
                            bcheck = false;
                            break;
                        }
                    }
                    if (bcheck) {
                        System.out.print("filtering: " + uri + "?" +
                                         request.getQueryString());
                        String cpath = request.getContextPath();
                        response.sendRedirect(cpath + "/timeout.html");
                        return;
                    }
                }
            }
            chain.doFilter(req, res);
        } catch (Exception e) {
            System.out.print(e);
        }
    }

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
        System.out.print("Filter Event: Destroy");
        return;
    }
}
