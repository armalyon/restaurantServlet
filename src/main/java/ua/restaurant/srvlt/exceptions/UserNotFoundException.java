package ua.restaurant.srvlt.exceptions;


public class UserNotFoundException extends Exception {
    private final static String ERROR = " user not found by username ";
    private String tag;
    private String username;
    private String message;

    public UserNotFoundException(String tag, String username) {
        this.tag = tag;
        this.username = username;
        this.message = tag + ERROR + username;
    }

    @Override
    public String getMessage() {
        return tag;
    }

    public String getUsername() {
        return username;
    }
}
