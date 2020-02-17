package ua.restaurant.srvlt.model.dao.mapper;

import ua.restaurant.srvlt.model.entity.MenuItem;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuItemMapper implements ObjectMapper<MenuItem> {

    private static final String MENU_ITEMS_ID = "menu_items.id";
    private static final String MENU_ITEMS_NAME = "menu_items.name";
    private static final String MENU_ITEMS_NAMEUA = "menu_items.nameua";
    private static final String MENU_ITEMS_WEIGHT = "menu_items.weight";
    private static final String MENU_ITEMS_PRICE = "menu_items.price";
    private static final String STORAGE_QUANTITY = "storage_quantity";

    @Override
    public MenuItem extractFromResultSet(ResultSet rs) throws SQLException {
        return new MenuItem.Builder()
                .id(rs.getLong(MENU_ITEMS_ID))
                .name(rs.getString(MENU_ITEMS_NAME))
                .nameUa(rs.getString(MENU_ITEMS_NAMEUA))
                .weight(rs.getLong(MENU_ITEMS_WEIGHT))
                .price(rs.getLong(MENU_ITEMS_PRICE))
                .storageQuantity(rs.getLong(STORAGE_QUANTITY))
                .build();
    }

}
