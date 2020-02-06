package ua.restaurant.srvlt.controller.listener;

import org.apache.log4j.Logger;
import ua.restaurant.srvlt.controller.filter.AuthFilter;
import ua.restaurant.srvlt.model.entity.types.Role;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

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
        LOGGER.debug("Session destroyed id=" + session.getId() + " last accessed time: " + session.getLastAccessedTime());
    }
}

