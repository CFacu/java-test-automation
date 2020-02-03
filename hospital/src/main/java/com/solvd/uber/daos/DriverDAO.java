package com.solvd.uber.daos;

import com.solvd.uber.connection.ConnectionPool;
import com.solvd.uber.models.Driver;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class DriverDAO implements IDAO<Driver> {

    private final Logger LOGGER = Logger.getLogger(DriverDAO.class);

    @Override
    public Driver get(Long id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Drivers WHERE id=" + id);

            while (resultSet.next()) {

                return extractUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
    }

    public Driver extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        Driver driver = new Driver();

        driver.setId(resultSet.getLong("id"));
        driver.setName(resultSet.getString("name"));
        driver.setBirthDate(resultSet.getDate("birth_date"));
        driver.setPassword(resultSet.getString("password"));
        driver.setPhoneNumber(resultSet.getInt("phone_number"));
        driver.setRate(resultSet.getInt("rate"));

        return driver;
    }

    @Override
    public Set getAll() {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Drivers");
            Set<Driver> drivers = new HashSet<Driver>();

            while (resultSet.next()) {
                Driver driver = extractUserFromResultSet(resultSet);
                drivers.add(driver);
            }
            return drivers;
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
    }

    @Override
    public void insert(Driver driver) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Drivers VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setLong(1, driver.getId());
            preparedStatement.setString(2, driver.getName());
            preparedStatement.setString(3, driver.getPassword());
            preparedStatement.setDate(4, driver.getBirthDate());
            preparedStatement.setInt(5, driver.getPhoneNumber());
            preparedStatement.setInt(6, driver.getRate());

            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void update(Driver driver) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Drivers SET name=?, birth_date=?, rate=?, password=?, phone_number=?");
            preparedStatement.setString(1, driver.getName());
            preparedStatement.setDate(2, driver.getBirthDate());
            preparedStatement.setInt(3, driver.getRate());
            preparedStatement.setString(4, driver.getPassword());
            preparedStatement.setInt(5, driver.getPhoneNumber());

            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){

            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM Drivers WHERE id=" + id);

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
