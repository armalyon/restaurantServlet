package ua.restaurant.srvlt.controller.command;

import javax.servlet.http.HttpServletRequest;

import static ua.restaurant.srvlt.constants.TextConstants.ADMIN_CONFIRMATION_PAGE;

public class AdminConfirmationPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return ADMIN_CONFIRMATION_PAGE;
    }
}
