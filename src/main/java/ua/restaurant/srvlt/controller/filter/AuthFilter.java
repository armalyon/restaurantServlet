package ua.restaurant.srvlt.controller.filter;

import org.apache.log4j.Logger;
import ua.restaurant.srvlt.controller.command.utility.CommandUtility;
import ua.restaurant.srvlt.model.entity.type.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ua.restaurant.srvlt.constants.StringConstants.*;
import static ua.restaurant.srvlt.model.entity.type.Role.*;

public class AuthFilter implements Filter {
    private static final String LOGOUT = "/logout";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        final HttpServletResponse httpResponce = (HttpServletResponse) servletResponse;
        final HttpSession session = httpRequest.getSession();
        final String username = (String) session.getAttribute(USERNAME_ATTRIBUTE);

        Role role = (Role) session.getAttribute(ROLE_ATTRIBUTE);
        String path = httpRequest.getRequestURI();

        boolean isLoggedIn = (session != null &&
                session.getAttribute(USERNAME_ATTRIBUTE) != null &&
                session.getAttribute(ROLE_ATTRIBUTE) != null);

        boolean isDenied = path.endsWith(DENIED);
        boolean isLogoutRequest = path.endsWith(LOGOUT);
        boolean isLoginRequest = path.contains(LOGIN);
        boolean isRegistrationRequest = path.contains(REGISTRATION) || path.contains(REGISTER);

        if (path.equals("/")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (isLoggedIn) {
            if (isRegistrationRequest || isLoginRequest) {
                httpResponce.sendRedirect(LOGOUT);
            } else {
                if (isPathCorrespondsRole(path, role) || isLogoutRequest ) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    CommandUtility.removeUserFromSessionAndContext(httpRequest);
                    throw new SecurityException();
                }
            }
        } else {
            if (isRegistrationRequest || isLoginRequest) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                httpResponce.sendRedirect("login");
            }
        }
    }

    private boolean isPathCorrespondsRole(String path, Role role) {
        if (role.equals(CLIENT) && path.contains(ADMIN.name().toLowerCase())) return false;
        if (role.equals(ADMIN) && path.contains(CLIENT.name().toLowerCase())) return false;
        return true;
    }

    @Override
    public void destroy() {

    }
}

