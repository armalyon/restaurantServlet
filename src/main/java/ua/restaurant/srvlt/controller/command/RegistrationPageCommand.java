package ua.restaurant.srvlt.controller.command;

import ua.restaurant.srvlt.constants.TextConstants;

import javax.servlet.http.HttpServletRequest;

import static ua.restaurant.srvlt.constants.TextConstants.*;

public class RegistrationPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return REGISTRATION_PAGE;
    }
}
