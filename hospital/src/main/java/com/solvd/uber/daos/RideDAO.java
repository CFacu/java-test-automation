package com.solvd.uber.daos;

import com.solvd.uber.connection.ConnectionPool;
import com.solvd.uber.models.Ride;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class RideDAO implements IDAO<Ride> {

    private final Logger LOGGER = Logger.getLogger(RideDAO.class);

    @Override
    public Ride get(Long id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Rides WHERE id=" + id);

            while (resultSet.next()) {

                return extractUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
    }

    public Ride extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        Ride ride = new Ride();

        ride.setId(resultSet.getLong("id"));
        ride.setDistance(resultSet.getInt("distance"));
        ride.setPickUpTime(resultSet.getDate("pick_up_time"));
        ride.setDropOffTime(resultSet.getDate("drop_off_time"));
        ride.setCost(resultSet.getDouble("cost"));
        ride.setDriversId(resultSet.getLong("drivers_id"));

        return ride;
    }

    @Override
    public Set<Ride> getAll() {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Rides");
            Set<Ride> rides = new HashSet<Ride>();

            while (resultSet.next()) {
                Ride ride = extractUserFromResultSet(resultSet);
                rides.add(ride);
            }
            return rides;
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
    }

    @Override
    public void insert(Ride ride) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Rides VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setLong(1, ride.getId());
            preparedStatement.setInt(2, ride.getDistance());
            preparedStatement.setDate(3, ride.getPickUpTime());
            preparedStatement.setDate(4, ride.getDropOffTime());
            preparedStatement.setDouble(5, ride.getCost());
            preparedStatement.setLong(6, ride.getDriversId());

            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void update(Ride ride) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Rides SET distance=?, pick_up_time=?, drop_off_time=?, cost=?, drivers_id=?");
            preparedStatement.setInt(1, ride.getDistance());
            preparedStatement.setDate(2, ride.getPickUpTime());
            preparedStatement.setDate(3, ride.getDropOffTime());
            preparedStatement.setDouble(4, ride.getCost());
            preparedStatement.setLong(5, ride.getDriversId());

            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){

            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM Rides WHERE id=" + id);

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
