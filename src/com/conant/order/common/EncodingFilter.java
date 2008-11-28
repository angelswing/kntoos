package com.conant.order.common;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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

public class EncodingFilter implements Filter {

    private FilterConfig filterConfig;
    private String encoding = "gb2312";
    private static final String[] nochecks = {
            "user/"
    };

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        String args = filterConfig.getInitParameter("encoding");
        if (args != null) {
            encoding = args;
        }
    }

    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain filterChain) throws IOException,
            ServletException {
        try {
            if (req instanceof HttpServletRequest &&
                res instanceof HttpServletResponse) {
                HttpServletRequest request = (HttpServletRequest) req;
                String uri = request.getRequestURI();
                boolean bcheck = true;
                for (int i = 0; i < nochecks.length; i++) {
                    if (uri.indexOf(nochecks[i]) >= 0) {
                        System.out.println("Not filt:" + uri);
                        bcheck = false;
                        break;
                    }
                }
                if (bcheck) {
                    req.setCharacterEncoding(encoding);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        filterChain.doFilter(req, res);
    }

    public void destroy() {
    }
}
