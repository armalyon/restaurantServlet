package ua.restaurant.srvlt.service;

import ua.restaurant.srvlt.dao.DaoFactory;
import ua.restaurant.srvlt.dao.MenuItemDao;
import ua.restaurant.srvlt.dto.MenuDTO;

public class MenuService {
    private MenuItemDao menuItemDao = DaoFactory.getInstance().createMenuItemDao();

    public MenuDTO getMenu() {
        return new MenuDTO(menuItemDao
                .findAllByStorageQuantityGreaterThan(0L));
    }

}
