package ua.restaurant.srvlt.controller.listener;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;
import java.util.Set;

import static ua.restaurant.srvlt.constants.StringConstants.LOGGED_USERS_ATTRIBUTE;
import static ua.restaurant.srvlt.constants.StringConstants.USERNAME_ATTRIBUTE;

public class SessionListener implements HttpSessionListener {
    private static final Logger LOGGER = Logger.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        LOGGER.debug("new session created id=" + session.getId());

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        Set<String> loggedUsers = (HashSet<String>) session.getServletContext()
                .getAttribute(LOGGED_USERS_ATTRIBUTE);
        loggedUsers.remove(session.getAttribute(USERNAME_ATTRIBUTE));
    }
}

