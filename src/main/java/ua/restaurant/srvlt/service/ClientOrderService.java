package ua.restaurant.srvlt.service;

import ua.restaurant.srvlt.dao.DaoFactory;
import ua.restaurant.srvlt.dao.MenuItemDao;
import ua.restaurant.srvlt.dao.OrderDao;
import ua.restaurant.srvlt.dao.UserDao;
import ua.restaurant.srvlt.entity.MenuItem;
import ua.restaurant.srvlt.entity.Order;
import ua.restaurant.srvlt.entity.User;
import ua.restaurant.srvlt.entity.type.OrderStatement;
import ua.restaurant.srvlt.exception.IdNotFoundExeption;
import ua.restaurant.srvlt.exception.NotEnoughItemsException;
import ua.restaurant.srvlt.exception.UserNotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;

public class ClientOrderService {

    private OrderDao orderDao = DaoFactory.getInstance().createOrderDao();
    private MenuItemDao menuItemDao = DaoFactory.getInstance().createMenuItemDao();
    private UserDao userDao = DaoFactory.getInstance().createUserDao();

    public void saveNewOrder(String username, long menuItemId, long quantity)
            throws NotEnoughItemsException, UserNotFoundException, IdNotFoundExeption {
        Order order = createOrderEntity(menuItemId, quantity, username);
        orderDao.create(order);
    }

    private Order createOrderEntity(long menuItemId, long quantity, String username)
            throws NotEnoughItemsException, UserNotFoundException, IdNotFoundExeption {
        MenuItem item = menuItemDao
                .findById(menuItemId).orElseThrow(
                        () -> new IdNotFoundExeption(ClientOrderService.class.getName(), menuItemId));
        if (!isItemsEnough(quantity, item.getStorageQuantity())) {
            throw new NotEnoughItemsException("Not enough items");
        }
        User user = userDao
                .findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("ORDERING ERROR, USER NOT FOUND ", username));
        long totalPrice = getTotalPrice(quantity, item.getPrice());
        return buildOrder(menuItemId, quantity, user, totalPrice);
    }

    private Order buildOrder(long menuItemId, long quantity, User user, long totalPrice) {
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
