package ua.restaurant.srvlt.model.dao.impl;

import org.apache.log4j.Logger;
import ua.restaurant.srvlt.model.dao.OrderDao;
import ua.restaurant.srvlt.model.dao.mapper.OrderMapper;
import ua.restaurant.srvlt.model.entity.Order;
import ua.restaurant.srvlt.model.entity.type.OrderStatement;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    public int countOrdersByUsername(String username) {
        int count = 0;
        try (Connection connection = ConnectionPoolHolder.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(bundle.getString(COUNT_ORDERS_BY_USERNAME))) {
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if (rs.first()) {
                count = rs.getInt("total");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return count;
    }

    @Override
    public int countOrdersByStatement(OrderStatement statement) {
        int count = 0;
        try (Connection connection = ConnectionPoolHolder.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(bundle.getString(COUNT_ORDERS_BY_STATEMENT))) {
            preparedStatement.setString(1, statement.name());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.first()) {
                count = rs.getInt("total");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return count;
    }

    @Override
    public List<Order> findAllByUsernameAndDate(String username, LocalDate date) {
        List<Order> orders = new ArrayList<>();
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
                orders.add(order);
            }
        } catch (SQLException e) {
            //TODO handling
            LOGGER.warn(e.getMessage());
        }
        return orders;
    }

    public List<Order> findAllByUsernamePageable(String username, int offset, int pageSize) {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionPoolHolder.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     bundle.getString(FIND_ORDERS_BY_USERNAME_PAGEABLE))
        ) {
            st.setString(1, username);
            st.setInt(2, offset);
            st.setInt(3, pageSize);
            ResultSet rs = st.executeQuery();
            OrderMapper mapper = new OrderMapper();
            while (rs.next()) {
                Order order = mapper.extractFromResultSet(rs);
                orders.add(order);
            }
        } catch (
                SQLException e) {
            //TODO handling
            LOGGER.warn(e.getMessage());
        }
        return orders;
    }

    @Override
    public List<Order> findAllByOrderStatementPageable(OrderStatement statement,
                                                       int offset,
                                                       int pageSize) {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionPoolHolder.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     bundle.getString(FIND_ORDERS_BY_STATEMENT_PAGEABLE))
        ) {
            st.setString(1, statement.name());
            st.setInt(2, offset);
            st.setInt(3, pageSize);
            ResultSet rs = st.executeQuery();
            OrderMapper mapper = new OrderMapper();
            while (rs.next()) {
                Order order = mapper.extractFromResultSet(rs);
                orders.add(order);
            }
        } catch (
                SQLException e) {
            //TODO handling
            LOGGER.warn(e.getMessage());
        }
        return orders;
    }

    @Override
    public void updateOrderStatementById(OrderStatement statement, long orderId) {
        try (PreparedStatement st =
                     ConnectionPoolHolder.getConnection()
                             .prepareStatement(bundle.getString(UPDATE_ORDER_STATEMENT_BY_ID))) {
            st.setString(1, statement.name());
            st.setLong(2, orderId);
            st.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }

    }

    @Override
    public void updateOrderStatementByIdDecreaseStorageQuantity(OrderStatement statementToSet,
                                                                long menuItemId,
                                                                long orderId,
                                                                long requestedQuantity) {
        try (Connection connection = ConnectionPoolHolder.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement decreaseItemsStatement =
                         connection.prepareStatement(bundle.getString(DECREASE_ITEM_STORAGE_QUANTITY_BY_VALUE_ADN_ID));
                 PreparedStatement updateOrderStatement =
                         connection.prepareStatement(bundle.getString(UPDATE_ORDER_STATEMENT_BY_ID))) {

                decreaseItemsStatement.setLong(1, requestedQuantity);
                decreaseItemsStatement.setLong(2, menuItemId);

                updateOrderStatement.setString(1, statementToSet.name());
                updateOrderStatement.setLong(2, orderId);

                decreaseItemsStatement.executeUpdate();
                updateOrderStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);

            } catch (SQLException e) {
                connection.rollback();
                connection.setAutoCommit(true);
                LOGGER.error(e.getMessage());
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }


    @Override
    public Order findById(long id) {
        Order order = null;
        try (Connection connection = ConnectionPoolHolder.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     bundle.getString(FIND_ORDER_BY_ID))
        ) {
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.first()) {
                order = new OrderMapper().extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return order;
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
