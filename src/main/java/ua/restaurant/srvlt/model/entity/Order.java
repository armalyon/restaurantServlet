package ua.restaurant.srvlt.model.entity;

import ua.restaurant.srvlt.model.entity.types.OrderStatement;

import java.time.LocalDate;
import java.time.LocalTime;

public class Order {
    private long id;
    private MenuItem menuItem;
    private long quantity;
    private long totalPrice;
    private LocalDate date;
    private LocalTime time;
    private OrderStatement orderStatement;
    private long userId;


    public long getId() {
        return id;
    }


    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public OrderStatement getOrderStatement() {
        return orderStatement;
    }

    public void setOrderStatement(OrderStatement orderStatement) {
        this.orderStatement = orderStatement;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public static class Builder {
        private Order newOrder;

        public Builder() {
            newOrder = new Order();
        }

        public Builder id(long id) {
            newOrder.id = id;
            return this;
        }

        public Builder menuItem(MenuItem meuItem) {
            newOrder.menuItem = meuItem;
            return this;
        }

        public Builder totalPrice(long totalPrice) {
            newOrder.totalPrice = totalPrice;
            return this;
        }

        public Builder quantity(long quantity) {
            newOrder.quantity = quantity;
            return this;
        }

        public Builder time(LocalTime time) {
            newOrder.time = time;
            return this;
        }

        public Builder date(LocalDate date) {
            newOrder.date = date;
            return this;
        }

        public Builder orderStatement(OrderStatement statement) {
            newOrder.orderStatement = statement;
            return this;
        }

        public Builder userId(long userId) {
            newOrder.userId = userId;
            return this;
        }

        public Order build() {
            return newOrder;
        }

    }
}



