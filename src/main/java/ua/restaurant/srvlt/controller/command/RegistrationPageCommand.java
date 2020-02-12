package ua.restaurant.srvlt.controller.command;

import javax.servlet.http.HttpServletRequest;

import static ua.restaurant.srvlt.constants.StringConstants.*;

public class RegistrationPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return REGISTRATION_PAGE;
    }
}
