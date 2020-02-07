package com.solvd.uber.daos;

import com.solvd.uber.connection.ConnectionPool;
import com.solvd.uber.models.RideRequest;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class RideRequestDAO implements IDAO<RideRequest> {

    private final Logger LOGGER = Logger.getLogger(RideRequestDAO.class);

    @Override
    public RideRequest get(Long id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Rides_Requests WHERE id=" + id);

            while (resultSet.next()) {

                return extractUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
    }

    public RideRequest extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        RideRequest rideRequest = new RideRequest();

        rideRequest.setId(resultSet.getLong("id"));
        rideRequest.setRequestTime(resultSet.getDate("date_time"));
        rideRequest.setLocationStart(resultSet.getString("location_start"));
        rideRequest.setLocationEnd(resultSet.getString("location_end"));
        rideRequest.setRideId(resultSet.getLong("rides_id"));
        rideRequest.setUserId(resultSet.getLong("users_id"));

        return rideRequest;
    }

    @Override
    public Set<RideRequest> getAll() {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Rides_Requests");
            Set<RideRequest> rideRequests = new HashSet<RideRequest>();

            while (resultSet.next()) {
                RideRequest rideRequest = extractUserFromResultSet(resultSet);
                rideRequests.add(rideRequest);
            }
            return rideRequests;
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
    }

    @Override
    public void insert(RideRequest rideRequest) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Rides_Requests VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setLong(1, rideRequest.getId());
            preparedStatement.setDate(2, rideRequest.getRequestTime());
            preparedStatement.setString(3, rideRequest.getLocationStart());
            preparedStatement.setString(4, rideRequest.getLocationEnd());
            preparedStatement.setLong(5, rideRequest.getRideId());
            preparedStatement.setLong(6, rideRequest.getUserId());

            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void update(RideRequest rideRequest) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Rides_Requests SET date_time=?, location_start=?, location_end=?, rides_id=?, users_id=?");
            preparedStatement.setDate(1, rideRequest.getRequestTime());
            preparedStatement.setString(2, rideRequest.getLocationStart());
            preparedStatement.setString(3, rideRequest.getLocationEnd());
            preparedStatement.setLong(4, rideRequest.getRideId());
            preparedStatement.setLong(5, rideRequest.getUserId());

            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){

            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM Rides_Requests WHERE id=" + id);

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
