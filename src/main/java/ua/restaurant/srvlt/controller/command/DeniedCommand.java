package ua.restaurant.srvlt.controller.command;

import ua.restaurant.srvlt.controller.command.utility.CommandUtility;

import javax.servlet.http.HttpServletRequest;

import static ua.restaurant.srvlt.constant.StringConstants.DENIED_PAGE;

public class DeniedCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        CommandUtility.removeUserFromSessionAndContext(request);
        return DENIED_PAGE;
    }
}
