package ua.restaurant.srvlt.model.dao.mapper;

import ua.restaurant.srvlt.model.entity.User;
import ua.restaurant.srvlt.model.entity.type.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {

    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        return new User.Builder()
                .id(rs.getLong("id"))
                .username(rs.getString("username"))
                .password(rs.getString("password"))
                .name(rs.getString("name"))
                .surname(rs.getString("surname"))
                .role(Role.valueOf(rs.getString("role")))
                .registrationDate(
                        rs.getTimestamp("registration_date").toLocalDateTime()
                )
                .funds(rs.getLong("funds"))
                .build();
    }


    @Override
    public User makeUnique(Map<Long, User> cache, User user) {
        cache.putIfAbsent(user.getId(), user);
        return cache.get(user.getId());
    }
}
