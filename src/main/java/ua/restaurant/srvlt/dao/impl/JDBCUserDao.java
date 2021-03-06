package ua.restaurant.srvlt.dao.impl;

import org.apache.log4j.Logger;
import ua.restaurant.srvlt.dao.UserDao;
import ua.restaurant.srvlt.dao.mapper.UserMapper;
import ua.restaurant.srvlt.entity.User;
import ua.restaurant.srvlt.entity.type.Role;
import ua.restaurant.srvlt.exception.TransactionException;
import ua.restaurant.srvlt.exception.UserExistsException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.restaurant.srvlt.constant.DBConstants.*;

public class JDBCUserDao implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(JDBCUserDao.class);

    public Optional<User> findUserByUsername(String username) {
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
            LOGGER.warn(e.getMessage());
        }
        return Optional.ofNullable(user);
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
        }
    }

    @Override
    public void transferFunds(String payerUsername, String recieverUsername, long valueToTransfer) throws TransactionException {
        try (Connection connection = ConnectionPoolHolder.getConnection();
        ) {
            connection.setAutoCommit(false);
            try (PreparedStatement addStatement =
                         connection.prepareStatement(bundle.getString(ADD_FUNDS_BY_USERNAME));
                 PreparedStatement decreaseStatement =
                         connection.prepareStatement(bundle.getString(DECREASE_FUNDS_BY_USERNAME))) {
                decreaseStatement.setLong(1, valueToTransfer);
                decreaseStatement.setString(2, payerUsername);
                addStatement.setLong(1, valueToTransfer);
                addStatement.setString(2, recieverUsername);
                decreaseStatement.executeUpdate();
                addStatement.executeUpdate();
            } catch (SQLException e) {
                connection.rollback();
                LOGGER.error(e.getMessage());
                throw new TransactionException();
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new TransactionException();

        }

    }


    @Override
    public void create(User user) {

    }

    @Override
    public Optional<User> findById(long id) {
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
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
            return null;
        }
        return users;
    }

    @Override
    public int countUsersByRole(Role role) {
        int count = 0;
        try (Connection connection = ConnectionPoolHolder.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(bundle.getString(COUNT_USERS_BY_ROLE))) {
            statement.setString(1, role.name());
            ResultSet rs = statement.executeQuery();
            if (rs.first()) {
                count = rs.getInt("total");
            }
        } catch (SQLException e) {
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
