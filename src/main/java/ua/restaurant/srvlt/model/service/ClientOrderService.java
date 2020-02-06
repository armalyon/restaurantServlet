package ua.restaurant.srvlt.model.service;

import org.apache.log4j.Logger;
import ua.restaurant.srvlt.model.dao.DaoFactory;
import ua.restaurant.srvlt.model.dao.MenuItemDao;
import ua.restaurant.srvlt.model.dao.OrderDao;
import ua.restaurant.srvlt.model.dao.UserDao;
import ua.restaurant.srvlt.model.entity.MenuItem;
import ua.restaurant.srvlt.model.entity.Order;
import ua.restaurant.srvlt.model.entity.types.OrderStatement;
import ua.restaurant.srvlt.exceptions.ItemNotFoundException;
import ua.restaurant.srvlt.exceptions.NotEnoughItemsException;

import java.time.LocalDate;
import java.time.LocalTime;

public class ClientOrderService {

    private static final Logger LOGGER = Logger.getLogger(ClientOrderService.class);

    private OrderDao orderDao = DaoFactory.getInstance().createOrderDao();
    private MenuItemDao menuItemDao = DaoFactory.getInstance().createMenuItemDao();
    private UserDao userDao = DaoFactory.getInstance().createUserDao();

    public void saveNewOrder(String username, long menuItemId, long quantity) throws NotEnoughItemsException {
        MenuItem item = menuItemDao.findById(menuItemId);
        LOGGER.debug(item);
        if (!isItemsEnough(quantity, item.getStorageQuantity())) {
            throw new NotEnoughItemsException("Not enough items");
        }
        long userId = userDao.findUserByUsername(username).getId();
        long totalPrice = getTotalPrice(quantity, item.getPrice());
        Order order = createOrder(menuItemId, quantity, userId, totalPrice);
        orderDao.create(order);
    }

    private Order createOrder(long menuItemId, long quantity, long userId, long totalPrice) {
        return new Order.Builder()
                .menuItem(new MenuItem.Builder().id(menuItemId).build())
                .quantity(quantity)
                .totalPrice(totalPrice)
                .date(LocalDate.now())
                .time(LocalTime.now())
                .userId(userId)
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
