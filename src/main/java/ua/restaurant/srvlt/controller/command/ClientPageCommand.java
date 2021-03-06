package ua.restaurant.srvlt.controller.command;

import org.apache.log4j.Logger;
import ua.restaurant.srvlt.dto.MenuDTO;
import ua.restaurant.srvlt.dto.OrdersDTO;
import ua.restaurant.srvlt.service.MenuService;
import ua.restaurant.srvlt.service.OrdersService;

import javax.servlet.http.HttpServletRequest;

import static ua.restaurant.srvlt.constant.StringConstants.*;

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
