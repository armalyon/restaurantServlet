package ua.restaurant.srvlt.model.dao.mapper;

import ua.restaurant.srvlt.model.entity.Bill;
import ua.restaurant.srvlt.model.entity.Order;
import ua.restaurant.srvlt.model.entity.type.BillStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class BillMapper implements ObjectMapper <Bill> {
    private static final String ID = "id";
    private static final String STATEMENT = "statement";
    private static final String INVOICE_DATE_TIME = "invoice_date_time";
    private static final String PAYMENT_DATE_TIME = "payment_date_time";
    private OrderMapper orderMapper;

    public BillMapper() {
        orderMapper = new OrderMapper();
    }

    @Override
    public Bill extractFromResultSet(ResultSet rs) throws SQLException {
        Order order = orderMapper.extractFromResultSet(rs);
        return new Bill.Builder()
                .id(rs.getLong(ID))
                .order(order)
                .statement(BillStatement.valueOf(rs.getString(STATEMENT)))
                .invoiceDateTime(rs.getTimestamp(INVOICE_DATE_TIME).toLocalDateTime())
                .paymentDateTime(getPaymentDateTimeFromResultSet(rs))
                .build();
    }

    private LocalDateTime getPaymentDateTimeFromResultSet(ResultSet rs) throws SQLException {
       Timestamp ts = rs.getTimestamp(PAYMENT_DATE_TIME);
       if (ts != null) {
           return ts.toLocalDateTime();
       }
       else return null;
    }

}
