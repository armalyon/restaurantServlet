package ua.restaurant.srvlt.exception;


public class IdNotFoundExeption extends Exception {
    private String tag;
    private String message;
    private long id;

    public IdNotFoundExeption(long id) {
        this.id = id;
        this.message = id + " id not found in db";
    }

    public IdNotFoundExeption(String tag, long id) {
        this.id = id;
        this.tag = tag;
        this.message = tag + " id not found in db, id=" + id;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public long getId() {
        return id;
    }
}
