package ua.restaurant.srvlt.controller.command;

import ua.restaurant.srvlt.controller.command.utility.CommandUtility;
import ua.restaurant.srvlt.model.entity.User;
import ua.restaurant.srvlt.model.pagination.Page;
import ua.restaurant.srvlt.model.service.ClientsService;

import javax.servlet.http.HttpServletRequest;

import static ua.restaurant.srvlt.constants.StringConstants.ADMIN_USERS_PAGE;
import static ua.restaurant.srvlt.constants.StringConstants.PAGE_ATTRIBUTE;

public class AdminUsersPageCommand implements Command {

    private static final int PAGE_SIZE = 5;
    private ClientsService clientsService = new ClientsService();

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = CommandUtility.getPageNumber(request);
        Page<User> page = clientsService.getAllClients(currentPage, PAGE_SIZE);
        request.setAttribute(PAGE_ATTRIBUTE, page);
        return ADMIN_USERS_PAGE;
    }
}
