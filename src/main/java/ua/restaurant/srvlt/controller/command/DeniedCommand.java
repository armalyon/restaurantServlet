package ua.restaurant.srvlt.controller.command;

import javax.servlet.http.HttpServletRequest;
import static ua.restaurant.srvlt.constants.TextConstants.*;

public class DeniedCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
       CommandUtility.removeUserFromSessionAndContext(request);
        return DENIED_PAGE;
    }
}
