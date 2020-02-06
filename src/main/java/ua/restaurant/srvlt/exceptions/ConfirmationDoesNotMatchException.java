package ua.restaurant.srvlt.exceptions;

public class ConfirmationDoesNotMatchException extends Exception {
    private String message;

    public ConfirmationDoesNotMatchException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}