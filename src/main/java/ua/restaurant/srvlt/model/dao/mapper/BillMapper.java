package ua.restaurant.srvlt.model.dao.mapper;

import ua.restaurant.srvlt.model.entity.Bill;
import ua.restaurant.srvlt.model.entity.Order;
import ua.restaurant.srvlt.model.entity.type.BillStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

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
                .invoiceDateTime(LocalDateTime.ofInstant(
                        Instant.ofEpochMilli(
                                rs.getTimestamp("invoice_date_time").getTime()), ZoneId.systemDefault())
                )
                .paymentDateTime(LocalDateTime.ofInstant(
                        Instant.ofEpochMilli(
                                rs.getTimestamp("payment_date_time").getTime()), ZoneId.systemDefault())
                )
                .build();
    }

    @Override
    public Bill makeUnique(Map<Long, Bill> cache, Bill bill) {
        cache.putIfAbsent(bill.getId(), bill);
        return cache.get(bill.getId());
    }
}
