package ua.restaurant.srvlt.controller.command;

import org.apache.log4j.Logger;
import ua.restaurant.srvlt.controller.command.utility.CommandUtility;
import ua.restaurant.srvlt.entity.type.Role;

import javax.servlet.http.HttpServletRequest;

import static ua.restaurant.srvlt.constant.StringConstants.*;

public class LogoutCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute(USERNAME_ATTRIBUTE);
        Role role = (Role) request.getSession().getAttribute(ROLE_ATTRIBUTE);
        CommandUtility.removeUserFromSessionAndContext(request);
        LOGGER.warn(username + " with role " + role + " logged out");
        return LOGIN_PAGE;
    }
}
