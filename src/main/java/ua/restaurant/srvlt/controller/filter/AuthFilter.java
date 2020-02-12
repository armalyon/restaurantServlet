package ua.restaurant.srvlt.controller.filter;

import org.apache.log4j.Logger;
import ua.restaurant.srvlt.model.entity.type.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ua.restaurant.srvlt.constants.StringConstants.*;

public class AuthFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(AuthFilter.class);


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
            LOGGER.debug("isLoggedIn");
            if (isRegistrationRequest || isLoginRequest) {
                LOGGER.debug("isRegistrationRequest || isLoginRequest");
                httpResponce.sendRedirect("/logout");
            } else {
                if (isPathCorrect(path, role) || isLogoutRequest ) {
                    LOGGER.debug("isPathCorrect(path, role)");
                    filterChain.doFilter(servletRequest, servletResponse);

                } else {
                    LOGGER.debug("!isPathCorrect(path, role)");
                   // CommandUtility.removeUserFromSessionAndContext(httpRequest);
                    throw new SecurityException();
                }
            }

        } else {
            LOGGER.debug("!isLoggedIn");
            if (isRegistrationRequest || isLoginRequest) {
                LOGGER.debug("(isRegistrationRequest || isLoginRequest)");
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                LOGGER.debug("!isRegistrationRequest || !isLoginRequest");
                httpResponce.sendRedirect("login");
            }
        }

    }

    private boolean isPathCorrect(String path, Role role) {
        return path.contains(role.name().toLowerCase()) || path.contains("favicon.ico");
    }

    @Override
    public void destroy() {

    }
}

