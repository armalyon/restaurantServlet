package ua.restaurant.srvlt.model.service;

import ua.restaurant.srvlt.model.dao.DaoFactory;
import ua.restaurant.srvlt.model.dao.MenuItemDao;
import ua.restaurant.srvlt.model.dto.MenuDTO;

public class MenuService {
    private MenuItemDao menuItemDao = DaoFactory.getInstance().createMenuItemDao();

    public MenuDTO getMenu() {
        return new MenuDTO(menuItemDao
                .findAllByStorageQuantityGreaterThan(0L));
    }

}
