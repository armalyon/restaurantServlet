package ua.restaurant.srvlt.model.exceptions;


public class UserExistsException extends Exception {
    private String message;
    private String login;

    public UserExistsException(String message, String login) {
        this.message = message;
        this.login = login;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getLogin() {
        return login;
    }
}
