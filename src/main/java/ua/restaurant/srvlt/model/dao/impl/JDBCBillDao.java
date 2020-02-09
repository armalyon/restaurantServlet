package ua.restaurant.srvlt.model.dao.impl;

import org.apache.log4j.Logger;
import ua.restaurant.srvlt.model.dao.BillDao;
import ua.restaurant.srvlt.model.dao.mapper.BillMapper;
import ua.restaurant.srvlt.model.entity.Bill;
import ua.restaurant.srvlt.model.entity.type.BillStatement;
import ua.restaurant.srvlt.model.entity.type.OrderStatement;
import ua.restaurant.srvlt.model.pagination.Page;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Bill findById(long id) {
        return null;
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
    public Page<Bill> getBillsByUserNameNewestFirst(String username, int currentPage, int pageSize) {
        int billsByUser = 0;
        int offset = pageSize * currentPage;
        Map<Long, Bill> bills = new HashMap<>();

        try (Connection connection = ConnectionPoolHolder.getConnection();
             PreparedStatement countStatement = connection.prepareStatement(
                     bundle.getString(COUNT_BILLS_BY_USERNAME));
             PreparedStatement findStatement = connection.prepareStatement(
                     bundle.getString(FIND_BILLS_BY_USERNAME))
        ) {
            countStatement.setString(1, username);
            ResultSet rs = countStatement.executeQuery();
            if (rs.first()) {
                billsByUser = rs.getInt("total");
            }

            findStatement.setString(1, username);
            findStatement.setInt(2, offset);
            findStatement.setInt(3, pageSize);

            rs = findStatement.executeQuery();
            BillMapper mapper = new BillMapper();
            while (rs.next()) {
                Bill bill = mapper.extractFromResultSet(rs);
                bill = mapper.makeUnique(bills, bill);
            }
            List<Bill> ordersList = new ArrayList<>(bills.values());
            return new Page<Bill>(billsByUser, currentPage, pageSize, ordersList, pageSize);
        } catch (
                SQLException e) {
            //TODO handling
            LOGGER.warn(e.getMessage());
            return null;
        }
    }
}
