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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuDTO)) return false;

        MenuDTO menuDTO = (MenuDTO) o;

        return getMenu().equals(menuDTO.getMenu());
    }

    @Override
    public int hashCode() {
        return getMenu().hashCode();
    }
}
