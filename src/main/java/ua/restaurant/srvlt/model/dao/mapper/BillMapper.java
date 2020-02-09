package ua.restaurant.srvlt.model.dao.mapper;

import ua.restaurant.srvlt.model.entity.Bill;
import ua.restaurant.srvlt.model.entity.Order;
import ua.restaurant.srvlt.model.entity.type.BillStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

import static java.time.ZoneId.systemDefault;

public class BillMapper implements ObjectMapper <Bill> {
    private OrderMapper orderMapper;

    public BillMapper() {
        orderMapper = new OrderMapper();
    }

    @Override
    public Bill extractFromResultSet(ResultSet rs) throws SQLException {
        Order order = orderMapper.extractFromResultSet(rs);
        return new Bill.Builder()
                .order(order)
                .statement(BillStatement.valueOf(rs.getString("statement")))
                .invoiceDateTime(rs.getTimestamp("invoice_date_time").toLocalDateTime())
                .paymentDateTime(getPaymentDateTimeFromResultSet(rs))
                .build();
    }

    private LocalDateTime getPaymentDateTimeFromResultSet(ResultSet rs) throws SQLException {
       Timestamp ts = rs.getTimestamp("payment_date_time");
       if (ts != null) {
           return ts.toLocalDateTime();
       }
       else return null;
    }

    @Override
    public Bill makeUnique(Map<Long, Bill> cache, Bill bill) {
        cache.putIfAbsent(bill.getId(), bill);
        return cache.get(bill.getId());
    }
}
