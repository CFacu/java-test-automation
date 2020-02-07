package com.solvd.uber.daos;

import com.solvd.uber.connection.ConnectionPool;
import com.solvd.uber.models.Car;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class CarDAO implements IDAO<Car> {

    private final Logger LOGGER = Logger.getLogger(CarDAO.class);

    @Override
    public Car get(Long id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Cars WHERE id=" + id);

            while (resultSet.next()) {

                return extractUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
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
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Cars");
            Set<Car> cars = new HashSet<Car>();

            while (resultSet.next()) {
                Car car = extractUserFromResultSet(resultSet);
                cars.add(car);
            }
            return cars;
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
    }

    @Override
    public void insert(Car car) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Cars VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setLong(1, car.getId());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setString(3, car.getColor());
            preparedStatement.setString(4, car.getMake());
            preparedStatement.setInt(5, car.getModelYear());
            preparedStatement.setLong(6, car.getDriverId());

            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void update(Car car) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Cars SET model=?, make=?, model_year=?, color=?, drivers_id=?");
            preparedStatement.setString(1, car.getModel());
            preparedStatement.setString(2, car.getMake());
            preparedStatement.setInt(3, car.getModelYear());
            preparedStatement.setString(4, car.getColor());
            preparedStatement.setLong(5, car.getDriverId());

            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){

            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM Cars WHERE id=" + id);

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
