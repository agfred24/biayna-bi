package com.biayna.bi.web;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"}, description = "Session Checker Filter")
public class SessionCheckerFilter implements Filter {
    private FilterConfig config = null;

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
        config.getServletContext().log("Initializing SessionCheckerFilter");
    }

    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain)
            throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        /**
        // Check to see if user's session attribute contains an attribute
        // named "authenticated". If the attribute is not exists redirect
        // user to the login page.
        **/
        if (!request.getRequestURI().endsWith("login.html") &&
                request.getSession().getAttribute("authenticated") == null) {
            response.sendRedirect(request.getContextPath() + "/accounts/login.html");
        }
        chain.doFilter(req, res);
    }

    public void destroy() {
        config.getServletContext().log("Destroying SessionCheckerFilter");
    }
}