package ua.restaurant.srvlt.controller.command;

import org.apache.log4j.Logger;
import ua.restaurant.srvlt.exception.IdNotFoundExeption;
import ua.restaurant.srvlt.exception.NotEnoughItemsException;
import ua.restaurant.srvlt.service.AdminOrderConfirmationService;

import javax.servlet.http.HttpServletRequest;

import static ua.restaurant.srvlt.constant.StringConstants.*;

public class AdminConfirmOrderCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(AdminConfirmOrderCommand.class);

    private AdminOrderConfirmationService confirmationService =
            new AdminOrderConfirmationService();

    @Override
    public String execute(HttpServletRequest request) {
        long orderId = Long.parseLong(request.getParameter(ID_ATTRIBUTE));
        long requestedQuantity = Long.parseLong(request.getParameter(QUANTITY_ATTRIBUTE));
        try {
            if (confirmationService.isCanBeConfirmed(orderId)) {
                confirmationService.confirmOrder(orderId, requestedQuantity);
            }
        } catch (IdNotFoundExeption e) {
            LOGGER.error(e.getMessage());
            return ADMIN_CONFIRMATION_ERROR_DB_REDIRECT;
        } catch (NotEnoughItemsException e) {
            return ADMIN_CONFIRMATION_ERROR_ITEMS_REDIRECT;
        }
        return ADMIN_CONFIRMATION_REDIRECT;
    }
}
