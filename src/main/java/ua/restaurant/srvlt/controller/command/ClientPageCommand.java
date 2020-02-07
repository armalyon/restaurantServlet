package ua.restaurant.srvlt.controller.command;

import org.apache.log4j.Logger;
import ua.restaurant.srvlt.model.dto.MenuDTO;
import ua.restaurant.srvlt.model.dto.OrdersDTO;
import ua.restaurant.srvlt.model.service.MenuService;
import ua.restaurant.srvlt.model.service.OrdersService;

import javax.servlet.http.HttpServletRequest;

import static ua.restaurant.srvlt.constants.TextConstants.*;

public class ClientPageCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ClientPageCommand.class);


    private MenuService menuService = new MenuService();
    private OrdersService ordersService = new OrdersService();


    @Override
    public String execute(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute(USERNAME_ATTRIBUTE);

        MenuDTO menuDTO = menuService.getMenu();
        request.setAttribute(MENU_DTO_ATTRIBUTE, menuDTO);

        OrdersDTO ordersDTO = ordersService.getTodayOrdersByUserame(username);
        request.setAttribute(ORDERS_DTO_ATTRIBUTE, ordersDTO);

        return CLIENT_MAIN_PAGE;
    }
}
