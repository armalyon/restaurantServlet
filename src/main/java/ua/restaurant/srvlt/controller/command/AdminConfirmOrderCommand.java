package ua.restaurant.srvlt.controller.command;

import org.apache.log4j.Logger;
import ua.restaurant.srvlt.exceptions.IdNotFoundExeption;
import ua.restaurant.srvlt.exceptions.NotEnoughItemsException;
import ua.restaurant.srvlt.model.service.AdminOrderConfirmationService;

import javax.servlet.http.HttpServletRequest;

import static ua.restaurant.srvlt.constants.StringConstants.*;

public class AdminConfirmOrderCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(AdminConfirmOrderCommand.class);

    private AdminOrderConfirmationService confirmationService =
            new AdminOrderConfirmationService();

    @Override
    public String execute(HttpServletRequest request) {
        long orderId = Long.parseLong(request.getParameter(ID_ATTRIBUTE));
        long requestedQuantity = Long.parseLong(request.getParameter(QUANTITY_ATTRIBUTE));
        try {
            confirmationService.isCanBeConfirmed(orderId);
        } catch (IdNotFoundExeption idNotFoundExeption) {
            LOGGER.error(idNotFoundExeption.getMessage());
        } catch (NotEnoughItemsException e) {
            //TODO handling for FE
            e.printStackTrace();
        }

        try {
            confirmationService.confirmOrder(orderId, requestedQuantity);
        } catch (IdNotFoundExeption idNotFoundExeption) {
            LOGGER.error(idNotFoundExeption.getMessage());
        }


        return ADMIN_CONFIRMATION_REDIRECT;
    }
}
