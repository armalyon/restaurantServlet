package ua.restaurant.srvlt.model.dao.impl;

import org.apache.log4j.Logger;
import ua.restaurant.srvlt.model.dao.BillDao;
import ua.restaurant.srvlt.model.entity.Bill;
import ua.restaurant.srvlt.model.entity.type.BillStatement;
import ua.restaurant.srvlt.model.entity.type.OrderStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static ua.restaurant.srvlt.constants.DBConstants.SAVE_NEW_BILL_INTO_TABLE;
import static ua.restaurant.srvlt.constants.DBConstants.UPDATE_ORDER_STATEMENT_BY_ID;

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
}
