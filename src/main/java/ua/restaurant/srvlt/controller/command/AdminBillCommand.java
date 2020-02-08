package ua.restaurant.srvlt.controller.command;

import javax.servlet.http.HttpServletRequest;

import static ua.restaurant.srvlt.constants.TextConstants.ADMIN_CONFIRMED_REDIRECT;

public class AdminBillCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return ADMIN_CONFIRMED_REDIRECT;
    }
}
