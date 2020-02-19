package ua.restaurant.srvlt.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.restaurant.srvlt.constant.StringConstants.LANG_ATTRIBUTE;

public class LocaleFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResp = (HttpServletResponse) servletResponse;

        String localeName = servletRequest.getParameter(LANG_ATTRIBUTE);
        if (localeName != null) {
            httpReq.getSession().setAttribute(LANG_ATTRIBUTE, localeName);
        }
        filterChain.doFilter(httpReq, httpResp);
    }

    @Override
    public void destroy() {

    }
}
