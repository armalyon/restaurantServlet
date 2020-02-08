package ua.restaurant.srvlt.model.dao.mapper;

import ua.restaurant.srvlt.model.entity.MenuItem;
import ua.restaurant.srvlt.model.entity.Order;
import ua.restaurant.srvlt.model.entity.User;
import ua.restaurant.srvlt.model.entity.type.OrderStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Map;

public class OrderMapper implements ObjectMapper<Order> {
    private MenuItemMapper menuItemMapper;
    private UserMapper userMapper;

    public OrderMapper() {
        menuItemMapper = new MenuItemMapper();
        userMapper = new UserMapper();
    }

    @Override
    public Order extractFromResultSet(ResultSet rs) throws SQLException {
        User user = userMapper.extractFromResultSet(rs);
        MenuItem menuItem = menuItemMapper.extractFromResultSet(rs);
         return new Order.Builder()
                .menuItem(menuItem)
                .user(user)
                .orderStatement(OrderStatement.valueOf(rs.getString("orders.order_statement")))
                .quantity(rs.getLong("orders.quantity"))
                .time(LocalTime.parse(rs.getString("orders.time")))
                .date(rs.getDate("orders.date").toLocalDate())
                .id(rs.getLong("orders.id"))
                .totalPrice(rs.getLong("orders.total_price"))
                .build();
    }

    @Override
    public Order makeUnique(Map<Long, Order> cache, Order object) {
        cache.putIfAbsent(object.getId(), object);
        return cache.get(object.getId());
    }
}
