package ua.restaurant.srvlt.dao;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public interface GenericDao<T> {
    ResourceBundle bundle = ResourceBundle.getBundle("queries");

    void create(T entity);

    Optional<T> findById(long id);

    List<T> findAll();

    void update(T entity);

    void delete(int id);
}
