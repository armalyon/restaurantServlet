package ua.restaurant.srvlt.entity;

import ua.restaurant.srvlt.entity.type.BillStatement;

import java.time.LocalDateTime;


public class Bill {
    private long id;
    private Order order;
    private BillStatement statement;
    private LocalDateTime invoiceDateTime;
    private LocalDateTime paymentDateTime;

    public long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public BillStatement getStatement() {
        return statement;
    }

    public void setStatement(BillStatement statement) {
        this.statement = statement;
    }

    public LocalDateTime getInvoiceDateTime() {
        return invoiceDateTime;
    }

    public void setInvoiceDateTime(LocalDateTime invoiceDateTime) {
        this.invoiceDateTime = invoiceDateTime;
    }

    public LocalDateTime getPaymentDateTime() {
        return paymentDateTime;
    }

    public void setPaymentDateTime(LocalDateTime paymentDateTime) {
        this.paymentDateTime = paymentDateTime;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", order=" + order +
                ", statement=" + statement +
                ", invoiceDateTime=" + invoiceDateTime +
                ", paymentDateTime=" + paymentDateTime +
                '}';
    }

    public static class Builder {
        private Bill newBill;

        public Builder() {
            newBill = new Bill();
        }

        public Builder id(long id) {
            newBill.id = id;
            return this;
        }

        public Builder order(Order order) {
            newBill.order = order;
            return this;
        }

        public Builder statement(BillStatement statement) {
            newBill.statement = statement;
            return this;
        }

        public Builder invoiceDateTime(LocalDateTime invoiceDateTime) {
            newBill.invoiceDateTime = invoiceDateTime;
            return this;
        }

        public Builder paymentDateTime(LocalDateTime paymantDateTime) {
            newBill.paymentDateTime = paymantDateTime;
            return this;
        }

        public Bill build() {
            return newBill;
        }


    }

}
