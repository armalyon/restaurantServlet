package ua.restaurant.srvlt.controller.listener;

import org.apache.log4j.Logger;
import ua.restaurant.srvlt.constants.TextConstants;
import ua.restaurant.srvlt.controller.filter.AuthFilter;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Set;

public class ContextListener implements ServletContextListener {
    private static final Logger LOGGER = Logger.getLogger(ServletContextListener.class);


    @Override
    public void contextInitialized(ServletContextEvent sce) {
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        Set<String> loggedUsers = (Set<String>) context.getAttribute(TextConstants.LOGGED_USERS_ATTRIBUTE);
        LOGGER.warn("Context destroyed. logged users=" + loggedUsers);
    }
}
