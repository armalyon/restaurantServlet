package ua.restaurant.srvlt.model.exceptions;


public class NotEnoughFundsException extends Exception {
    private String message;
    private long billId;
    private long foundsDifference;

    public NotEnoughFundsException(String message, long foundsDifference, long billId) {
        this.message = message;
        this.foundsDifference = foundsDifference;
        this.billId = billId;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public long getBillId() {
        return billId;
    }

    public long getFoundsDifference() {
        return foundsDifference;
    }
}
