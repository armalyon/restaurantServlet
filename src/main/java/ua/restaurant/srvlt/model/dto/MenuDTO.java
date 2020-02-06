package ua.restaurant.srvlt.model.dto;

import ua.restaurant.srvlt.model.entity.MenuItem;

import java.util.List;


public class MenuDTO {
    private List<MenuItem> menu;

    public MenuDTO(List<MenuItem> menu) {
        this.menu = menu;
    }

    public List<MenuItem> getMenu() {
        return menu;
    }

    public void setMenu(List<MenuItem> menu) {
        this.menu = menu;
    }
}
