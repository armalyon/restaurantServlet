package ua.restaurant.srvlt.controller.command;

import ua.restaurant.srvlt.controller.command.utility.CommandUtility;
import ua.restaurant.srvlt.model.entity.Order;
import ua.restaurant.srvlt.model.entity.type.OrderStatement;
import ua.restaurant.srvlt.model.pagination.Page;
import ua.restaurant.srvlt.model.service.AdminOrderService;

import javax.servlet.http.HttpServletRequest;

import static ua.restaurant.srvlt.constants.StringConstants.ADMIN_CONFIRMATION_PAGE;
import static ua.restaurant.srvlt.constants.StringConstants.PAGE_ATTRIBUTE;

public class AdminConfirmationPageCommand implements Command {
    private static final int PAGE_SIZE = 5;
    private AdminOrderService adminOrderService = new AdminOrderService();

    @Override
    public String execute(HttpServletRequest request) {
       int pageNumber = CommandUtility.getPageNumber(request);
        Page<Order> page =
                adminOrderService.getOrdersByStatement(
                        OrderStatement.WAITING, pageNumber, PAGE_SIZE);
        request.setAttribute(PAGE_ATTRIBUTE, page);
        return ADMIN_CONFIRMATION_PAGE;
    }
}
