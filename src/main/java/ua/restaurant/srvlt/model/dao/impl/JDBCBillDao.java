package ua.restaurant.srvlt.model.dao.impl;

import org.apache.log4j.Logger;
import ua.restaurant.srvlt.model.dao.BillDao;
import ua.restaurant.srvlt.model.dao.mapper.BillMapper;
import ua.restaurant.srvlt.model.entity.Bill;
import ua.restaurant.srvlt.model.entity.type.BillStatement;
import ua.restaurant.srvlt.model.entity.type.OrderStatement;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

import static ua.restaurant.srvlt.constants.DBConstants.*;

public class JDBCBillDao implements BillDao {
    private static final Logger LOGGER = Logger.getLogger(JDBCBillDao.class);

    @Override
    public void create(Bill bill) {
        try (Connection connection = ConnectionPoolHolder.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement saveBillStatement =
                         connection.prepareStatement(bundle.getString(SAVE_NEW_BILL_INTO_TABLE));
                 PreparedStatement updateOrderStatement =
                         connection.prepareStatement(bundle.getString(UPDATE_ORDER_STATEMENT_BY_ID))) {

                updateOrderStatement.setString(1, OrderStatement.INVOICED.name());
                updateOrderStatement.setLong(2, bill.getOrder().getId());

                saveBillStatement.setLong(1, bill.getOrder().getId());
                saveBillStatement.setString(2, BillStatement.INVOICE.name());
                saveBillStatement.setTimestamp(3, Timestamp.valueOf(bill.getInvoiceDateTime()));

                saveBillStatement.executeUpdate();
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
    public Optional<Bill> findById(long id) {
        Bill bill = null;
        try (Connection connection = ConnectionPoolHolder.getConnection();
             PreparedStatement statement = connection.prepareStatement(bundle.getString(FIND_BILL_BY_ID))) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.first()) {
                bill = new BillMapper().extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            LOGGER.error("BILL NOT FOUND BY ID=" + id);
        }
        return Optional.ofNullable(bill);
    }

    @Override
    public List<Bill> findAll() {
        return null;
    }

    @Override
    public void update(Bill entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Bill> getBillsByUserNameNewestFirst(String username, int currentPage, int pageSize, int offset) {
        List<Bill> bills = new ArrayList<>();
        try (Connection connection = ConnectionPoolHolder.getConnection();
             PreparedStatement findStatement = connection.prepareStatement(
                     bundle.getString(FIND_BILLS_BY_USERNAME))
        ) {
            findStatement.setString(1, username);
            findStatement.setInt(2, offset);
            findStatement.setInt(3, pageSize);

            ResultSet rs = findStatement.executeQuery();
            BillMapper mapper = new BillMapper();
            while (rs.next()) {
                Bill bill = mapper.extractFromResultSet(rs);
                bills.add(bill);
            }
        } catch (
                SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        return bills;
    }

    @Override
    public int countBillsByUsername(String username) {
        int billsByUser = 0;
        try (Connection connection = ConnectionPoolHolder.getConnection();
             PreparedStatement countStatement = connection.prepareStatement(
                     bundle.getString(COUNT_BILLS_BY_USERNAME))) {
            countStatement.setString(1, username);
            ResultSet rs = countStatement.executeQuery();
            if (rs.first()) {
                billsByUser = rs.getInt("total");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return billsByUser;
    }

    @Override
    public void updatePaymentDateStatementById(BillStatement biillStatement, LocalDateTime localDateTime, long id) {
        try (Connection connection = ConnectionPoolHolder.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(bundle.getString(UPDATE_BILL_PAYMENT_DATE_STATEMENT_BY_ID))) {
            statement.setTimestamp(1, Timestamp.valueOf(localDateTime));
            statement.setString(2, biillStatement.name());
            statement.setLong(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

}
