package ua.restaurant.srvlt.model.service;


import ua.restaurant.srvlt.exceptions.IdNotFoundExeption;
import ua.restaurant.srvlt.exceptions.NotEnoughItemsException;
import ua.restaurant.srvlt.model.dao.DaoFactory;
import ua.restaurant.srvlt.model.dao.OrderDao;
import ua.restaurant.srvlt.model.entity.Order;
import ua.restaurant.srvlt.model.entity.types.OrderStatement;

import static ua.restaurant.srvlt.model.entity.types.OrderStatement.CONFIRMED;

public class OrderStatementService {

    OrderDao orderDao = DaoFactory.getInstance().createOrderDao();





    public boolean updateOrderStatement(OrderStatement statement, Long orderId) {
       orderDao.updateOrderStatementById(statement, orderId);
        return true;
    }

/*

    public void confirmOrder(Long orderId, Long quantity) throws IdNotFoundExeption {
        menuItemRepository.decreaseStorageQuantityById(
                getOrederById(orderId).getMenuItem().getId(), quantity);
        orderRepository.updateOrderStatementById(CONFIRMED, orderId);

    }

    public boolean isCanBeConfirmed(Long orderId) throws IdNotFoundExeption, NotEnoughItemsException {
        Order order = getOrederById(orderId);
        if (order.getMenuItem()
                .getStorageQuantity()
                <= order.getQuantity()) throw new NotEnoughItemsException("Not enough goods");

        return !order.getOrderStatement().equals(CONFIRMED);
    }

    private Order getOrederById(Long id) throws IdNotFoundExeption {
        return orderRepository
                .findById(id)
                .orElseThrow(() -> new IdNotFoundExeption("order not found", id));
    }*/

}
