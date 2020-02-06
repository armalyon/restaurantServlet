package ua.restaurant.srvlt.model.dao;

import ua.restaurant.srvlt.model.entity.Order;
import ua.restaurant.srvlt.model.pagination.Page;

import java.time.LocalDate;
import java.util.List;

public interface OrderDao extends GenericDao<Order> {
    List<Order> findAllByUsernameAndDate (String username, LocalDate date);
    Page<Order> findAllByUsernamePagable(String username, int currentPage, int pageSize);

}
