package ua.restaurant.srvlt.exceptions;


public class IdNotFoundExeption extends Exception {
    private String message;
    private long id;

    public IdNotFoundExeption(String message, long id) {
        this.message = message;
        this.id = id;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public long getId() {
        return id;
    }
}
