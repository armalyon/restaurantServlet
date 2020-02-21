package ua.restaurant.srvlt.dao;

import ua.restaurant.srvlt.entity.MenuItem;

import java.util.List;

public interface MenuItemDao extends GenericDao<MenuItem> {

    List<MenuItem> findAllByStorageQuantityGreaterThan(long quantity);
}
