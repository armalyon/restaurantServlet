package ua.restaurant.srvlt.dao.impl;

import org.apache.log4j.Logger;
import ua.restaurant.srvlt.dao.MenuItemDao;
import ua.restaurant.srvlt.dao.mapper.MenuItemMapper;
import ua.restaurant.srvlt.entity.MenuItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.restaurant.srvlt.constant.DBConstants.FIND_MENU_ITEMS_BY_STORAGE_QUANTITY_GREATER_THAN;
import static ua.restaurant.srvlt.constant.DBConstants.FIND_MENU_ITEM_BY_ID;

public class JDBCMenuItemDao implements MenuItemDao {
    private static final Logger LOGGER = Logger.getLogger(JDBCMenuItemDao.class);


    @Override
    public Optional<MenuItem> findById(long id) {
        MenuItem item = null;
        try (Connection connection = ConnectionPoolHolder.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     bundle.getString(FIND_MENU_ITEM_BY_ID))
        ) {
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.first()) {
                item = new MenuItemMapper().extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        return Optional.ofNullable(item);
    }


    @Override
    public List<MenuItem> findAllByStorageQuantityGreaterThan(long quantity) {
        List<MenuItem> items = new ArrayList<>();
        try (Connection connection = ConnectionPoolHolder.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     bundle.getString(FIND_MENU_ITEMS_BY_STORAGE_QUANTITY_GREATER_THAN)
             )) {
            st.setLong(1, quantity);
            ResultSet rs = st.executeQuery();
            MenuItemMapper mapper = new MenuItemMapper();
            while (rs.next()) {
                MenuItem item = mapper.extractFromResultSet(rs);
                items.add(item);
            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
        return items;
    }

    @Override
    public List<MenuItem> findAll() {
          /*
        Method is not used. Should be implemented
     */

        return null;
    }

    @Override
    public void update(MenuItem entity) {
     /*
        Method is not used. Should be implemented
     */
    }

    @Override
    public void delete(int id) {
     /*
        Method is not used. Should be implemented
     */
    }

    @Override
    public void create(MenuItem entity) {
    /*
        Method is not used. Should be implemented
     */
    }


}
