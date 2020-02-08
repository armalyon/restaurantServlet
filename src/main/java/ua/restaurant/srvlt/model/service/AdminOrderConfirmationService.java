package ua.restaurant.srvlt.model.service;

import org.apache.log4j.Logger;
import ua.restaurant.srvlt.exceptions.IdNotFoundExeption;
import ua.restaurant.srvlt.exceptions.NotEnoughItemsException;
import ua.restaurant.srvlt.model.dao.DaoFactory;
import ua.restaurant.srvlt.model.dao.MenuItemDao;
import ua.restaurant.srvlt.model.dao.OrderDao;
import ua.restaurant.srvlt.model.entity.Order;

import static ua.restaurant.srvlt.model.entity.types.OrderStatement.CONFIRMED;

public class AdminOrderConfirmationService {
    private static final Logger LOGGER = Logger.getLogger(AdminOrderConfirmationService.class);

    private OrderDao orderDao = DaoFactory.getInstance().createOrderDao();


    public void confirmOrder(Long orderId, Long requestedQuantity) throws IdNotFoundExeption {
        long menuItemId = orderDao.findById(orderId).getMenuItem().getId();
        orderDao.updateOrderStatementByIdDecreaseStorageQuantity(CONFIRMED, menuItemId, orderId, requestedQuantity);

    }

    public boolean isCanBeConfirmed(Long orderId) throws IdNotFoundExeption, NotEnoughItemsException {
        Order order = getOrederById(orderId);
        if (order.getMenuItem()
                .getStorageQuantity()
                <= order.getQuantity()) throw new NotEnoughItemsException("Not enough goods");

        return !order.getOrderStatement().equals(CONFIRMED);
    }

    private Order getOrederById(Long id) throws IdNotFoundExeption {
        return orderDao
                .findById(id);
    }

}
