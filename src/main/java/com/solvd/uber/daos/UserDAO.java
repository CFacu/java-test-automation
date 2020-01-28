package com.solvd.uber.daos;

import com.solvd.uber.ConnectionFactory;
import com.solvd.uber.models.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UserDAO implements Dao<User> {

    private static final Logger LOGGER = Logger.getLogger(UserDAO.class);

    @Override
    public Optional<User> get(Long id) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM User WHERE id=" + id);

            if (resultSet.next()) {
                connection.close();
                return extractUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return Optional.empty();
    }

    private Optional<User> extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setAge(resultSet.getInt("age"));
        user.setLocation(resultSet.getString("location"));
        user.setPassword(resultSet.getString("password"));
        user.setPhoneNumber(resultSet.getInt("phone_number"));
        user.setRate(resultSet.getInt("rate"));

        return Optional.of(user);
    }

    @Override
    public Set<User> getAll() {
        try {
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM User");
            Set users = new HashSet();

            while (resultSet.next()) {
                Optional<User> user = extractUserFromResultSet(resultSet);
                users.add(user);
            }
            connection.close();
            return users;
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
    }

    @Override
    public void insert(User user) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO User VALUES (NULL, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.setInt(3, user.getRate());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.getPhoneNumber());
            preparedStatement.setString(6, user.getLocation());

            preparedStatement.executeUpdate();
            connection.close();
        }catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void update(User user) {

        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE User SET name=?, age=?, rate=?, password=?, phone_number=?, location=?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.setInt(3, user.getRate());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.getPhoneNumber());
            preparedStatement.setString(6, user.getLocation());

            preparedStatement.executeUpdate();
            connection.close();
        }catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Connection connection = ConnectionFactory.getConnection();

            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM User WHERE id=" + id);

            connection.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
