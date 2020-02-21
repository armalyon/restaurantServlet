package ua.restaurant.srvlt.dao.mapper;

import ua.restaurant.srvlt.entity.User;
import ua.restaurant.srvlt.entity.type.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ObjectMapper<User> {

    private static final String ID = "id";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String ROLE = "role";
    private static final String REGISTRATION_DATE = "registration_date";
    private static final String FUNDS = "funds";

    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        return new User.Builder()
                .id(rs.getLong(ID))
                .username(rs.getString(USERNAME))
                .password(rs.getString(PASSWORD))
                .name(rs.getString(NAME))
                .surname(rs.getString(SURNAME))
                .role(Role.valueOf(rs.getString(ROLE)))
                .registrationDate(
                        rs.getTimestamp(REGISTRATION_DATE).toLocalDateTime()
                )
                .funds(rs.getLong(FUNDS))
                .build();
    }

}
