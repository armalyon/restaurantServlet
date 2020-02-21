package ua.restaurant.srvlt.controller.command;

import org.apache.log4j.Logger;
import ua.restaurant.srvlt.entity.type.OrderStatement;
import ua.restaurant.srvlt.service.OrderStatementService;

import javax.servlet.http.HttpServletRequest;

import static ua.restaurant.srvlt.constant.StringConstants.ADMIN_CONFIRMATION_REDIRECT;
import static ua.restaurant.srvlt.constant.StringConstants.ID_ATTRIBUTE;

public class AdminRejectOrderCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(AdminRejectOrderCommand.class);
    OrderStatementService orderStatementService = new OrderStatementService();

    @Override
    public String execute(HttpServletRequest request) {

        long id = Long.parseLong(request.getParameter(ID_ATTRIBUTE));
        orderStatementService.updateOrderStatement(OrderStatement.REJECTED, id);
        return ADMIN_CONFIRMATION_REDIRECT;
    }
}
