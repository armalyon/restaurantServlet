package ua.restaurant.srvlt.exception;

public class NotEnoughItemsException extends Exception {
    private String message;

    public NotEnoughItemsException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
