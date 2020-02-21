package ua.restaurant.srvlt.dao.mapper;

import ua.restaurant.srvlt.entity.MenuItem;
import ua.restaurant.srvlt.entity.Order;
import ua.restaurant.srvlt.entity.User;
import ua.restaurant.srvlt.entity.type.OrderStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;

public class OrderMapper implements ObjectMapper<Order> {
    private static final String ORDERS_ORDER_STATEMENT = "orders.order_statement";
    private static final String ORDERS_QUANTITY = "orders.quantity";
    private static final String ORDERS_TIME = "orders.time";
    private static final String ORDERS_DATE = "orders.date";
    private static final String ORDERS_ID = "orders.id";
    private static final String ORDERS_TOTAL_PRICE = "orders.total_price";
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
                .orderStatement(OrderStatement.valueOf(rs.getString(ORDERS_ORDER_STATEMENT)))
                .quantity(rs.getLong(ORDERS_QUANTITY))
                .time(LocalTime.parse(rs.getString(ORDERS_TIME)))
                .date(rs.getDate(ORDERS_DATE).toLocalDate())
                .id(rs.getLong(ORDERS_ID))
                .totalPrice(rs.getLong(ORDERS_TOTAL_PRICE))
                .build();
    }

}
