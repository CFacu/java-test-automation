package com.solvd.uber.daos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.uber.connection.ConnectionPool;
import com.solvd.uber.models.User;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class UserDAO implements IDAO<User> {

    private final Logger LOGGER = Logger.getLogger(UserDAO.class);

    @Override
    public User get(Long id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users WHERE id=" + id);

            while (resultSet.next()) {

                return extractUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
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

        return user;
    }

    @Override
    public Set<User> getAll() {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");
            Set<User> users = new HashSet<User>();

            while (resultSet.next()) {
                User user = extractUserFromResultSet(resultSet);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
    }

    @Override
    public void insert(User user) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Users VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setInt(4, user.getRate());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setInt(6, user.getPhoneNumber());

            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void update(User user) {

        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Users SET name=?, age=?, rate=?, password=?, phone_number=?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.setInt(3, user.getRate());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.getPhoneNumber());

            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){

            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM Users WHERE id=" + id);

        } catch (SQLException e) {
            LOGGER.error(e);
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
