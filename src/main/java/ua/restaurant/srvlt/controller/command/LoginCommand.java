package ua.restaurant.srvlt.controller.command;


import org.apache.log4j.Logger;
import ua.restaurant.srvlt.controller.command.utility.CommandUtility;
import ua.restaurant.srvlt.exceptions.UserNotFoundException;
import ua.restaurant.srvlt.model.entity.type.Role;
import ua.restaurant.srvlt.model.service.LoginService;

import javax.servlet.http.HttpServletRequest;

import static ua.restaurant.srvlt.constants.StringConstants.*;
import static ua.restaurant.srvlt.model.entity.type.Role.*;

public class LoginCommand implements Command {

    private LoginService loginService = new LoginService();

    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter(USERNAME_ATTRIBUTE);
        String pass = request.getParameter(PASSWORD_ATTRIBUTE);

        if (username == null || username.equals("") || pass == null || pass.equals("")) {
            return LOGIN_PAGE;
        }

        boolean isPasswordCorrect = false;
        Role role = null;
        try {
            isPasswordCorrect = loginService.isPasswordCorrect(username, pass);
            role = loginService.getUserRoleByUsername(username);
        } catch (UserNotFoundException e) {
            return LOGIN_PAGE + CREDENTIALS_ERROR_PARAM;
        }

        if (!isPasswordCorrect) {
            return LOGIN_PAGE + CREDENTIALS_ERROR_PARAM;
        } else return loginAndGetMainPage(request, username, role);

    }

    private String loginAndGetMainPage(HttpServletRequest request, String username, Role role) {
        if (role.equals(ADMIN)) {
            CommandUtility.addUserToLoggedUsers(request, username);
            CommandUtility.setUserAttributes(request, role, username);
            return ADMIN_MAIN_REDIRECT;
        } else if (role.equals(CLIENT)) {
            CommandUtility.addUserToLoggedUsers(request, username);
            CommandUtility.setUserAttributes(request, role, username);
            return CLIENT_MAIN_REDIRECT;
        } else {
            CommandUtility.setUserAttributes(request, UNKNOWN, username);
            return LOGIN_PAGE;
        }
    }

}
