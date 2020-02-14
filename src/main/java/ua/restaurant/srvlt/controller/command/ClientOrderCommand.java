package ua.restaurant.srvlt.controller.command;

import org.apache.log4j.Logger;
import ua.restaurant.srvlt.exceptions.NotEnoughItemsException;
import ua.restaurant.srvlt.exceptions.UserNotFoundException;
import ua.restaurant.srvlt.model.service.ClientOrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static ua.restaurant.srvlt.constants.StringConstants.*;

public class ClientOrderCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ClientOrderCommand.class);

    private ClientOrderService clientOrderService = new ClientOrderService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute(USERNAME_ATTRIBUTE);
        long quantity = Long.parseLong(request.getParameter(QUANTITY_ATTRIBUTE));
        long menuItemId = Long.parseLong(request.getParameter(MENU_ITEM_ID_ATTRIBUTE));

        request.setAttribute(ERROR, null);
        try {
            clientOrderService.saveNewOrder(username, menuItemId, quantity);
        } catch (NotEnoughItemsException e) {
            request.setAttribute(ERROR, QUANTITY_ATTRIBUTE);
        } catch (UserNotFoundException e) {
            LOGGER.error(e.getMessage());
        }


        return CLIENT_MAIN_REDIRECT;
    }
}
