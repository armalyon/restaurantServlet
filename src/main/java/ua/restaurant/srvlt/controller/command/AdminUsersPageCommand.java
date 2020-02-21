package ua.restaurant.srvlt.controller.command;

import ua.restaurant.srvlt.controller.command.utility.CommandUtility;
import ua.restaurant.srvlt.dto.pagination.Page;
import ua.restaurant.srvlt.entity.User;
import ua.restaurant.srvlt.service.ClientsService;

import javax.servlet.http.HttpServletRequest;

import static ua.restaurant.srvlt.constant.StringConstants.ADMIN_USERS_PAGE;
import static ua.restaurant.srvlt.constant.StringConstants.PAGE_ATTRIBUTE;

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
