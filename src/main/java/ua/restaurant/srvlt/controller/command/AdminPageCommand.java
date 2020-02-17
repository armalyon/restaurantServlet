package ua.restaurant.srvlt.controller.command;

import org.apache.log4j.Logger;
import ua.restaurant.srvlt.model.dto.MenuDTO;
import ua.restaurant.srvlt.model.service.MenuService;

import javax.servlet.http.HttpServletRequest;

import static ua.restaurant.srvlt.constants.StringConstants.ADMIN_MAIN_PAGE;
import static ua.restaurant.srvlt.constants.StringConstants.MENU_DTO_ATTRIBUTE;

public class AdminPageCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(AdminPageCommand.class);

    private MenuService menuService = new MenuService();


    @Override
    public String execute(HttpServletRequest request) {
        MenuDTO menuDTO = menuService.getMenu();
       request.setAttribute(MENU_DTO_ATTRIBUTE, menuDTO);
        return ADMIN_MAIN_PAGE;
    }
}
