package ua.restaurant.srvlt.dto;


import ua.restaurant.srvlt.model.entity.Order;

import java.util.List;

public class OrdersDTO {
    private List<Order> orders;

    public OrdersDTO() {
    }

    public OrdersDTO(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "OrdersDTO{" +
                "orders=" + orders +
                '}';
    }
}
