package ua.restaurant.srvlt.controller.command;

import org.apache.log4j.Logger;
import ua.restaurant.srvlt.model.entity.types.OrderStatement;
import ua.restaurant.srvlt.model.service.OrderStatementService;

import javax.servlet.http.HttpServletRequest;

import static ua.restaurant.srvlt.constants.TextConstants.*;

public class AdminRejectOrderCommand implements Command {
    OrderStatementService orderStatementService = new OrderStatementService();
    private static final Logger LOGGER = Logger.getLogger(AdminRejectOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        long id = Long.parseLong(request.getParameter(ID_ATTRIBUTE));
        orderStatementService.updateOrderStatement(OrderStatement.REJECTED, id);
        return ADMIN_CONFIRMATION_REDIRECT;
    }
}
