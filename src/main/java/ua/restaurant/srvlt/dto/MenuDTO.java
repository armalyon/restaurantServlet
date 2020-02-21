package ua.restaurant.srvlt.dto;

import ua.restaurant.srvlt.entity.MenuItem;

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
