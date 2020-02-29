package com.solvd.uber.daos.mysql;

import com.solvd.uber.ConnectionPool;
import com.solvd.uber.daos.interfaces.ICarDAO;
import com.solvd.uber.models.Car;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class CarDAO implements ICarDAO {

    private final Logger LOGGER = Logger.getLogger(CarDAO.class);

    private static final String INSERT_CAR =
            "INSERT INTO cars " +
                    "(model, color, make, model_year, drivers_id) " +
                    "VALUES (?, ?, ?, ?, ?)";

    private static final String GET_CAR_BY_ID =
            "SELECT * " +
                    "FROM cars WHERE id = ?";

    private static final String GET_CAR =
            "SELECT * " +
                    "FROM cars";

    private static final String UPDATE_CAR =
            "UPDATE cars " +
                    "SET model, color, make, model_year, drivers_id " +
                    "WHERE id = ?";

    private final static String DELETE_CAR =
            "DELETE FROM cars " +
                    "WHERE id = ?";

    @Override
    public Car get(Long id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CAR_BY_ID);
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

    public Car extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        Car car = new Car();

        car.setId(resultSet.getLong("id"));
        car.setColor(resultSet.getString("color"));
        car.setMake(resultSet.getString("make"));
        car.setModel(resultSet.getString("model"));
        car.setModelYear(resultSet.getInt("model_year"));
        car.setDriverId(resultSet.getLong("drivers_id"));

        return car;
    }

    @Override
    public Set<Car> getAll() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CAR);
            ResultSet resultSet = preparedStatement.executeQuery();
            Set<Car> cars = new HashSet<Car>();

            while (resultSet.next()) {
                Car car = extractUserFromResultSet(resultSet);
                cars.add(car);
            }
            return cars;
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
    public void insert(Car car) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CAR);
            preparedStatement.setString(1, car.getModel());
            preparedStatement.setString(2, car.getColor());
            preparedStatement.setString(3, car.getMake());
            preparedStatement.setInt(4, car.getModelYear());
            preparedStatement.setLong(5, car.getDriverId());
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
    public void update(Car car) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CAR);
            preparedStatement.setString(1, car.getModel());
            preparedStatement.setString(2, car.getMake());
            preparedStatement.setInt(3, car.getModelYear());
            preparedStatement.setString(4, car.getColor());
            preparedStatement.setLong(5, car.getDriverId());
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
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CAR);
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
