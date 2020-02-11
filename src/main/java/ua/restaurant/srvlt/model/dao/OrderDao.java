package ua.restaurant.srvlt.model.dao;

import ua.restaurant.srvlt.model.entity.Order;
import ua.restaurant.srvlt.model.entity.type.OrderStatement;

import java.time.LocalDate;
import java.util.List;

public interface OrderDao extends GenericDao<Order> {
    int countOrdersByUsername(String username);

    int countOrdersByStatement(OrderStatement statement);

    List<Order> findAllByUsernameAndDate(String username, LocalDate date);

    List<Order> findAllByUsernamePageable(String username, int offset, int pageSize);

    List<Order> findAllByOrderStatementPageable(OrderStatement statement, int offset, int pageSize);

    void updateOrderStatementById(OrderStatement statement, long orderId);

    void updateOrderStatementByIdDecreaseStorageQuantity(OrderStatement statementToSet,
                                                         long menuItemId,
                                                         long orderId,
                                                         long requestedQuantity);
}
