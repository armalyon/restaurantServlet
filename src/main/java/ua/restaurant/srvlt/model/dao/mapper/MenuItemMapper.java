package ua.restaurant.srvlt.model.dao.mapper;

import ua.restaurant.srvlt.model.entity.MenuItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class MenuItemMapper implements ObjectMapper<MenuItem> {

    @Override
    public MenuItem extractFromResultSet(ResultSet rs) throws SQLException {
        return new MenuItem.Builder()
                .id(rs.getLong("menu_items.id"))
                .name(rs.getString("menu_items.name"))
                .nameUa(rs.getString("menu_items.nameua"))
                .weight(rs.getLong("menu_items.weight"))
                .price(rs.getLong("menu_items.price"))
                .storageQuantity(rs.getLong("storage_quantity"))
                .build();
    }

    @Override
    public MenuItem makeUnique(Map<Long, MenuItem> cache, MenuItem item) {
        cache.putIfAbsent(item.getId(), item);
        return cache.get(item.getId());
    }
}
