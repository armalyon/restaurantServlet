package ua.restaurant.srvlt.model.dao;

import java.util.List;
import java.util.ResourceBundle;

public interface GenericDao<T> extends AutoCloseable {
    ResourceBundle bundle = ResourceBundle.getBundle("queries");
    void create(T entity);
    T findById(long id);
    List<T> findAll();
    void update(T entity);
    void delete(int id);
    void close();
}
