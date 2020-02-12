package ua.restaurant.srvlt.controller.command;

import javax.servlet.http.HttpServletRequest;

import static ua.restaurant.srvlt.constants.StringConstants.*;

public class LoginPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return LOGIN_PAGE;
    }
}
