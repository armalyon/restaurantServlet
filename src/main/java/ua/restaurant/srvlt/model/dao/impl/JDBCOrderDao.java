package ua.restaurant.srvlt.model.dao.impl;

import org.apache.log4j.Logger;
import ua.restaurant.srvlt.model.dao.OrderDao;
import ua.restaurant.srvlt.model.dao.mapper.OrderMapper;
import ua.restaurant.srvlt.model.entity.Order;
import ua.restaurant.srvlt.model.entity.types.OrderStatement;
import ua.restaurant.srvlt.model.pagination.Page;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.restaurant.srvlt.constants.DBConstants.*;

public class JDBCOrderDao implements OrderDao {
    private static final Logger LOGGER = Logger.getLogger(JDBCOrderDao.class);

    @Override
    public void create(Order order) {

        try {
            Connection connection = ConnectionPoolHolder.getConnection();
            PreparedStatement st =
                    connection.prepareStatement(bundle.getString(SAVE_ORDER_INTO_TABLE));
            st.setLong(1, order.getMenuItem().getId());
            st.setLong(2, order.getQuantity());
            st.setLong(3, order.getTotalPrice());
            st.setDate(4, Date.valueOf(order.getDate()));
            st.setTime(5, Time.valueOf(order.getTime()));
            st.setString(6, order.getOrderStatement().name());
            st.setLong(7, order.getUser().getId());
            st.execute();
            connection.close();
            st.close();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }

    }

    @Override
    public List<Order> findAllByUsernameAndDate(String username, LocalDate date) {
        Map<Long, Order> orderDTOs = new HashMap<>();
        try (Connection connection = ConnectionPoolHolder.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     bundle.getString(FIND_ORDERS_BY_USERNAME_AND_DATE)
             )) {
            st.setString(1, username);
            st.setDate(2, Date.valueOf(date));
            ResultSet rs = st.executeQuery();
            OrderMapper mapper = new OrderMapper();
            while (rs.next()) {
                Order order = mapper.extractFromResultSet(rs);
                order = mapper.makeUnique(orderDTOs, order);
            }
            return new ArrayList<>(orderDTOs.values());
        } catch (SQLException e) {
            //TODO handling
            LOGGER.warn(e.getMessage());
            return null;
        }

    }

    public Page<Order> findAllByUsernamePagable(String username, int currentPage, int pageSize) {

        int ordersByUser = 0;

        int offset = pageSize * currentPage;
        Map<Long, Order> orders = new HashMap<>();
        try (Connection connection = ConnectionPoolHolder.getConnection();
             PreparedStatement st1 = connection.prepareStatement(
                     bundle.getString(COUNT_ORDERS_BY_USERNAME));
             PreparedStatement st2 = connection.prepareStatement(
                     bundle.getString(FIND_ORDERS_BY_USERNAME_PAGEABLE))
        ) {
            st1.setString(1, username);
            ResultSet rs = st1.executeQuery();
            if (rs.first()) {
                ordersByUser = rs.getInt("total");
            }

            st2.setString(1, username);
            st2.setInt(2, offset);
            st2.setInt(3, pageSize);
            rs = st2.executeQuery();
            OrderMapper mapper = new OrderMapper();
            while (rs.next()) {
                Order order = mapper.extractFromResultSet(rs);
                order = mapper.makeUnique(orders, order);
            }
            List<Order> ordersList = new ArrayList<>(orders.values());
            return new Page<Order>(ordersByUser, currentPage, pageSize, ordersList, pageSize);
        } catch (
                SQLException e) {
            //TODO handling
            LOGGER.warn(e.getMessage());
            return null;
        }
    }

    @Override
    public Page<Order> findAllByOrderStatementOrderByDate(OrderStatement statement,
                                                          int currentPage,
                                                          int pageSize) {
        int ordersByUser = 0;
        int offset = pageSize * currentPage;
        Map<Long, Order> orders = new HashMap<>();

        try (Connection connection = ConnectionPoolHolder.getConnection();
             PreparedStatement st1 = connection.prepareStatement(
                     bundle.getString(COUNT_ORDERS_BY_STATEMENT));
             PreparedStatement st2 = connection.prepareStatement(
                     bundle.getString(FIND_ORDERS_BY_STATEMENT_PAGEABLE))
        ) {
            st1.setString(1, statement.name());
            ResultSet rs = st1.executeQuery();
            if (rs.first()) {
                ordersByUser = rs.getInt("total");
            }

            st2.setString(1, statement.name());
            st2.setInt(2, offset);
            st2.setInt(3, pageSize);
            rs = st2.executeQuery();
            OrderMapper mapper = new OrderMapper();
            while (rs.next()) {
                Order order = mapper.extractFromResultSet(rs);
                order = mapper.makeUnique(orders, order);
            }
            List<Order> ordersList = new ArrayList<>(orders.values());
            return new Page<Order>(ordersByUser, currentPage, pageSize, ordersList, pageSize);
        } catch (
                SQLException e) {
            //TODO handling
            LOGGER.warn(e.getMessage());
            return null;
        }
    }

    @Override
    public void updateOrderStatementById(OrderStatement statement, long orderId) {
        try(PreparedStatement st =
                    ConnectionPoolHolder.getConnection()
                            .prepareStatement(bundle.getString(UPDATE_ORDER_STATEMENT_BY_ID))) {
            st.setString(1, statement.name());
            st.setLong(2, orderId);
            st.executeUpdate();
        } catch (SQLException e){
            LOGGER.warn(e.getMessage());
        }

    }


    @Override
    public Order findById(long id) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public void update(Order entity) {

    }

    @Override
    public void delete(int id) {

    }


}
