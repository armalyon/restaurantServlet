package ua.restaurant.srvlt.controller.command;

import org.apache.log4j.Logger;
import ua.restaurant.srvlt.controller.command.utility.CommandUtility;
import ua.restaurant.srvlt.exception.IdNotFoundExeption;
import ua.restaurant.srvlt.exception.NotEnoughItemsException;
import ua.restaurant.srvlt.exception.UserNotFoundException;
import ua.restaurant.srvlt.service.ClientOrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static ua.restaurant.srvlt.constant.StringConstants.*;

public class ClientOrderCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ClientOrderCommand.class);

    private ClientOrderService clientOrderService = new ClientOrderService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute(USERNAME_ATTRIBUTE);
        long quantity = Long.parseLong(request.getParameter(QUANTITY_ATTRIBUTE));
        long menuItemId = Long.parseLong(request.getParameter(MENU_ITEM_ID_ATTRIBUTE));

        try {
            clientOrderService.saveNewOrder(username, menuItemId, quantity);
        } catch (NotEnoughItemsException e) {
            return CLIENT_MAIN_QUANTITY_ERROR_REDIRECT;
        } catch (UserNotFoundException e) {
            LOGGER.error(e.getMessage());
            CommandUtility.removeUserFromSessionAndContext(request);
            return INTERNAL_ERROR_PAGE;
        } catch (IdNotFoundExeption e) {
            LOGGER.error("menu item not found in DB" + e.getMessage());
            return CLIENT_MAIN_ITEM_ERROR_REDIRECT;
        }


        return CLIENT_MAIN_REDIRECT;
    }
}
