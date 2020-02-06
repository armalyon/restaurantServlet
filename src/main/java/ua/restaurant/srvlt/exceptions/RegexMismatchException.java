package ua.restaurant.srvlt.exceptions;


public class RegexMismatchException extends Exception {
    private String field;
    private String message;

    public RegexMismatchException(String message, String field) {
        this.field = field;
        this.message = message + "field: " + field;
    }

    public String getField() {
        return field;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
