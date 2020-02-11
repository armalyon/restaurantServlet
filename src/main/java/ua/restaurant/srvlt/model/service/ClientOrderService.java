package ua.restaurant.srvlt.model.service;

import org.apache.log4j.Logger;
import ua.restaurant.srvlt.model.dao.DaoFactory;
import ua.restaurant.srvlt.model.dao.MenuItemDao;
import ua.restaurant.srvlt.model.dao.OrderDao;
import ua.restaurant.srvlt.model.dao.UserDao;
import ua.restaurant.srvlt.model.entity.MenuItem;
import ua.restaurant.srvlt.model.entity.Order;
import ua.restaurant.srvlt.model.entity.User;
import ua.restaurant.srvlt.model.entity.type.OrderStatement;
import ua.restaurant.srvlt.exceptions.NotEnoughItemsException;

import java.time.LocalDate;
import java.time.LocalTime;

public class ClientOrderService {

    private OrderDao orderDao = DaoFactory.getInstance().createOrderDao();
    private MenuItemDao menuItemDao = DaoFactory.getInstance().createMenuItemDao();
    private UserDao userDao = DaoFactory.getInstance().createUserDao();

    public void saveNewOrder(String username, long menuItemId, long quantity) throws NotEnoughItemsException {
        Order order = createOrderEntity(menuItemId, quantity, username);
        orderDao.create(order);
    }

    private Order createOrderEntity(long menuItemId, long quantity, String username) throws NotEnoughItemsException {
        MenuItem item = menuItemDao.findById(menuItemId);
        if (!isItemsEnough(quantity, item.getStorageQuantity())) {
            throw new NotEnoughItemsException("Not enough items");
        }
        User user = userDao.findUserByUsername(username);
        long totalPrice = getTotalPrice(quantity, item.getPrice());

        return new Order.Builder()
                .menuItem(new MenuItem.Builder().id(menuItemId).build())
                .quantity(quantity)
                .totalPrice(totalPrice)
                .date(LocalDate.now())
                .time(LocalTime.now())
                .user(user)
                .orderStatement(OrderStatement.WAITING)
                .build();
    }

    private boolean isItemsEnough(long quantity, long storageQuantity) {
        return quantity <= storageQuantity;
    }

    private long getTotalPrice(long quantity, long pricePerItem) {
        return quantity * pricePerItem;
    }
}
