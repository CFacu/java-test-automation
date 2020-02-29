package com.solvd.uber.daos.mysql;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.uber.ConnectionPool;
import com.solvd.uber.daos.interfaces.IUserDAO;
import com.solvd.uber.models.User;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class UserDAO implements IUserDAO {

    private final Logger LOGGER = Logger.getLogger(UserDAO.class);

    private static final String INSERT_USER =
            "INSERT INTO users " +
                    "(name, age, rate, password, phone_number, location) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String GET_USER_BY_ID =
            "SELECT * " +
                    "FROM users WHERE id = ?";

    private static final String GET_USERS =
            "SELECT * " +
                    "FROM users";

    private static final String UPDATE_USER =
            "UPDATE users " +
                    "SET name, age, rate, password, phone_number, location " +
                    "WHERE id = ?";

    private final static String DELETE_USER =
            "DELETE FROM users " +
                    "WHERE id = ?";

    @Override
    public User get(Long id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                return extractUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        return null;
    }

    public User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setAge(resultSet.getInt("age"));
        user.setPassword(resultSet.getString("password"));
        user.setPhoneNumber(resultSet.getInt("phone_number"));
        user.setRate(resultSet.getInt("rate"));
        user.setLocation(resultSet.getString("location"));

        return user;
    }

    @Override
    public Set<User> getAll() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            Set<User> users = new HashSet<User>();

            while (resultSet.next()) {
                User user = extractUserFromResultSet(resultSet);
                users.add(user);
            }
            return users;
        }catch (SQLException e) {
            LOGGER.error(e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        return null;
    }

    @Override
    public void insert(User user) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.setInt(3, user.getRate());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.getPhoneNumber());
            preparedStatement.setString(6, user.getLocation());
            preparedStatement.executeUpdate();
            connection.commit();
        }catch (SQLException e) {
            LOGGER.error(e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
    }

    @Override
    public void update(User user) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.setInt(3, user.getRate());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.getPhoneNumber());
            preparedStatement.setString(6, user.getLocation());
            preparedStatement.executeUpdate();
            connection.commit();
        }catch (SQLException e) {
            LOGGER.error(e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
    }

    @Override
    public void delete(Long id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER);
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();

        } catch (SQLException e) {
            LOGGER.error(e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
    }

    public void mapperToJson(User user) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File("target/user.json"), user);
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    public User mapperFromJson(String path) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            User user = objectMapper.readValue(new File(path), User.class);
            return user;
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return null;
    }
}
