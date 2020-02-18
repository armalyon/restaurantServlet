package ua.restaurant.srvlt.controller.command;

import ua.restaurant.srvlt.controller.command.utility.CommandUtility;
import ua.restaurant.srvlt.dto.pagination.Page;
import ua.restaurant.srvlt.model.entity.Order;
import ua.restaurant.srvlt.model.service.OrdersService;

import javax.servlet.http.HttpServletRequest;

import static ua.restaurant.srvlt.constants.StringConstants.*;

public class ClientOrdersPageCommand implements Command {
    private static final int PAGE_SIZE = 5;

    private OrdersService ordersService = new OrdersService();

    @Override
    public String execute(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute(USERNAME_ATTRIBUTE);
        String pageNumberString = request.getParameter(PAGE_ATTRIBUTE);
        int pageNumber = CommandUtility.getPageNumber(request);
        if (pageNumberString == null) pageNumber = 0;
        Page<Order> page = ordersService.getOrdersByUserame(username, pageNumber, PAGE_SIZE);
        request.setAttribute(PAGE_ATTRIBUTE, page);
        return CLIENT_ORDERS_PAGE;
    }
}
