package com.solvd.uber.daos.mysql;

import com.solvd.uber.ConnectionPool;
import com.solvd.uber.daos.interfaces.IDriverDAO;
import com.solvd.uber.models.Driver;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DriverDAO implements IDriverDAO {

    private final Logger LOGGER = Logger.getLogger(DriverDAO.class);

    private static final String INSERT_DRIVER =
            "INSERT INTO drivers " +
                    "(name, password, birth_date, phone_number, rate) " +
                    "VALUES (?, ?, ?, ?, ?)";

    private static final String GET_DRIVER_BY_ID =
            "SELECT * " +
                    "FROM drivers WHERE id = ?";

    private static final String GET_DRIVER =
            "SELECT * " +
                    "FROM drivers";

    private static final String UPDATE_DRIVER =
            "UPDATE drivers " +
                    "SET name, password, birth_date, phone_number, rate " +
                    "WHERE id = ?";

    private final static String DELETE_DRIVER =
            "DELETE FROM drivers " +
                    "WHERE id = ?";

    @Override
    public Driver get(Long id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(GET_DRIVER_BY_ID);
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
    public List<Driver> getAll() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_DRIVER);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Driver> drivers = new ArrayList<>();

            while (resultSet.next()) {
                Driver driver = extractUserFromResultSet(resultSet);
                drivers.add(driver);
            }
            return drivers;
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
    public void insert(Driver driver) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DRIVER);
            preparedStatement.setString(1, driver.getName());
            preparedStatement.setString(2, driver.getPassword());
            preparedStatement.setDate(3, driver.getBirthDate());
            preparedStatement.setInt(4, driver.getPhoneNumber());
            preparedStatement.setInt(5, driver.getRate());
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
    public void update(Driver driver) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DRIVER);
            preparedStatement.setString(1, driver.getName());
            preparedStatement.setDate(2, driver.getBirthDate());
            preparedStatement.setInt(3, driver.getRate());
            preparedStatement.setString(4, driver.getPassword());
            preparedStatement.setInt(5, driver.getPhoneNumber());
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
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DRIVER);
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
}
