package ua.restaurant.srvlt.model.dao.impl;

import org.apache.log4j.Logger;
import ua.restaurant.srvlt.model.dao.OrderDao;
import ua.restaurant.srvlt.model.dao.mapper.OrderMapper;
import ua.restaurant.srvlt.model.entity.Order;
import ua.restaurant.srvlt.model.entity.types.OrderStatement;
import ua.restaurant.srvlt.model.pagination.Page;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.restaurant.srvlt.constants.DBConstants.*;

public class JDBCOrderDao implements OrderDao {
    private static final Logger LOGGER = Logger.getLogger(JDBCOrderDao.class);
    private Connection connection;

    private long menuItemId;
    private long quantity;
    private long totalPrice;
    private LocalDate date;
    private LocalTime time;
    private OrderStatement orderStatement;
    private long userId;


    public JDBCOrderDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Order order) {
        try (PreparedStatement st =
                     connection.prepareStatement(bundle.getString(SAVE_ORDER_INTO_TABLE))
        ) {
            st.setLong(1, order.getMenuItem().getId());
            st.setLong(2, order.getQuantity());
            st.setLong(3, order.getTotalPrice());
            st.setDate(4, Date.valueOf(order.getDate()));
            st.setTime(5, Time.valueOf(order.getTime()));
            st.setString(6, order.getOrderStatement().name());
            st.setLong(7, order.getUserId());
            st.execute();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> findAllByUsernameAndDate(String username, LocalDate date) {
        Map<Long, Order> orderDTOs = new HashMap<>();
        try (PreparedStatement st = connection.prepareStatement(
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
        try (PreparedStatement st = connection.prepareStatement(
                bundle.getString(COUNT_ORDERS_BY_USERNAME))
        ) {
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.first()) {
                ordersByUser = rs.getInt("total");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        int offset = pageSize * currentPage;
        Map<Long, Order> orders = new HashMap<>();
        try (PreparedStatement st = connection.prepareStatement(
                bundle.getString(FIND_ORDERS_BY_USERNAME_PAGEABLE))
        ) {
            LOGGER.debug("offset=" + offset + " pageSize=" + pageSize);
            st.setString(1, username);
            st.setInt(2, offset);
            st.setInt(3, pageSize);
            ResultSet rs = st.executeQuery();
            OrderMapper mapper = new OrderMapper();
            while (rs.next()) {
                Order order = mapper.extractFromResultSet(rs);
                order= mapper.makeUnique(orders, order);
                LOGGER.debug("orderDTO!!" + order);
            }
            List<Order> ordersList = new ArrayList<>(orders.values());
            return new Page(ordersByUser, currentPage, pageSize, ordersList, pageSize);
        } catch (
                SQLException e) {
            //TODO handling
            LOGGER.warn(e.getMessage());
            return null;
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

    @Override
    public void close() {

    }

}
