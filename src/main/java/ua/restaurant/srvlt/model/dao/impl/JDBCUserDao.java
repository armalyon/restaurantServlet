package ua.restaurant.srvlt.model.dao.impl;

import org.apache.log4j.Logger;
import ua.restaurant.srvlt.exceptions.UserExistsException;
import ua.restaurant.srvlt.model.dao.UserDao;
import ua.restaurant.srvlt.model.dao.mapper.UserMapper;
import ua.restaurant.srvlt.model.entity.User;
import ua.restaurant.srvlt.model.entity.type.Role;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ua.restaurant.srvlt.constants.DBConstants.*;

public class JDBCUserDao implements UserDao {

    private static final Logger LOGGER = Logger.getLogger(JDBCUserDao.class);

    public User findUserByUsername(String username) {
        User user = null;
        try (Connection connection = ConnectionPoolHolder.getConnection();
             PreparedStatement st =
                     connection.prepareStatement(
                             bundle.getString(FIND_USER_BY_USERNAME)
                     )) {
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.first()) {
                UserMapper mapper = new UserMapper();
                user = mapper.extractFromResultSet(rs);
                            }
        } catch (SQLException e) {
            //TODO handling
            LOGGER.warn(e.getMessage());
        }
        return user;
    }

    @Override
    public void createNewUser(User user) throws UserExistsException {
        try (Connection connection = ConnectionPoolHolder.getConnection();
             PreparedStatement st =
                     connection.prepareStatement(bundle.getString(SAVE_USER_INTO_TABLE))
        ) {
            st.setString(1, user.getUsername());
            st.setString(2, user.getPassword());
            st.setString(3, user.getName());
            st.setString(4, user.getSurname());
            st.setString(5, user.getRole().name());
            st.setTimestamp(6, Timestamp.valueOf(user.getRegistrationDate()));
            st.setLong(7, user.getFunds());
            st.execute();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new UserExistsException("User already exists ", user.getUsername());
        } catch (SQLException e) {
            LOGGER.debug(e.getMessage());
            e.printStackTrace();
        }
    }


    @Override
    public void create(User user) {

    }

    @Override
    public User findById(long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<User> findAllPageByRole(Role role, int offset, int pageSize) {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPoolHolder.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(bundle.getString(FIND__ALL_USERS_BY_ROLE_PAGABLE))
        ) {
            statement.setString(1, role.name());
            statement.setInt(2, offset);
            statement.setInt(3, pageSize);
            ResultSet rs = statement.executeQuery();
            UserMapper mapper = new UserMapper();
            while (rs.next()) {
                User user = mapper.extractFromResultSet(rs);
                    users.add(user);
            }
            return users;
        } catch (SQLException e) {
            //TODO handling
            LOGGER.warn(e.getMessage());
            return null;
        }
    }

    @Override
    public int countUsersByRole(Role role) {
        int count = 0;
        try(Connection connection = ConnectionPoolHolder.getConnection();
        PreparedStatement statement =
                connection.prepareStatement(bundle.getString(COUNT_USERS_BY_ROLE))){
            statement.setString(1, role.name());
            ResultSet rs = statement.executeQuery();
            if (rs.first()){
                count = rs.getInt("total");
            }
        }catch (SQLException e){
            LOGGER.error(e.getMessage());
        }
        return count;
    }


    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(int id) {
    }

}
