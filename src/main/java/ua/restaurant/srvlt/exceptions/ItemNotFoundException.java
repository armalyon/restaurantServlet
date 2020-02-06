package ua.restaurant.srvlt.exceptions;


public class ItemNotFoundException extends Exception {
    private String message;

    public ItemNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
