package ua.restaurant.srvlt.model.dao;

import ua.restaurant.srvlt.model.entity.MenuItem;

import java.util.List;

public interface MenuItemDao extends GenericDao <MenuItem> {

    List<MenuItem> findAllByStorageQuantityGreaterThan(long quantity);
}
