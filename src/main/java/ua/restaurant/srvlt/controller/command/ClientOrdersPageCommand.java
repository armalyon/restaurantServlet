package ua.restaurant.srvlt.controller.command;

import org.apache.log4j.Logger;
import ua.restaurant.srvlt.model.entity.Order;
import ua.restaurant.srvlt.model.pagination.Page;
import ua.restaurant.srvlt.model.service.OrdersService;

import javax.servlet.http.HttpServletRequest;

import static ua.restaurant.srvlt.constants.TextConstants.*;

public class ClientOrdersPageCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ClientOrdersPageCommand.class);
    private static final int PAGE_SIZE = 5;

    private OrdersService ordersService = new OrdersService();

    @Override
    public String execute(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute(USERNAME_ATTRIBUTE);
        String pageNumberString = request.getParameter(PAGE_ATTRIBUTE);
        int pageNumber;
        if (pageNumberString == null) pageNumber = 0;
        else pageNumber = Integer.parseInt(pageNumberString) - 1;
        Page<Order> page = ordersService.getOrdersByName(username, pageNumber, PAGE_SIZE);
        request.setAttribute(PAGE_ATTRIBUTE, page);
        return CLIENT_ORDERS_PAGE;
    }
}
