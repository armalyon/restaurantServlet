package ua.restaurant.srvlt.model.exceptions;


public class UserNotFoundException extends Exception {
    private String message;
    private String username;

    public UserNotFoundException(String message, String username) {
        this.message = message;
        this.username = username;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }
}
