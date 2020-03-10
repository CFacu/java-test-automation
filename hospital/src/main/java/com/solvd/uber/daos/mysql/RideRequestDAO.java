package com.solvd.uber.daos.mysql;

import com.solvd.uber.ConnectionPool;
import com.solvd.uber.daos.interfaces.IRideRequestDAO;
import com.solvd.uber.models.RideRequest;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RideRequestDAO implements IRideRequestDAO {

    private final Logger LOGGER = Logger.getLogger(RideRequestDAO.class);

    private static final String INSERT_RIDE_REQUEST =
            "INSERT INTO rides_requests " +
                    "(request_time, location_start, location_end, rides_id, users_id) " +
                    "VALUES (?, ?, ?, ?, ?)";

    private static final String GET_RIDE_REQUEST_BY_ID =
            "SELECT * " +
                    "FROM rides_requests WHERE id = ?";

    private static final String GET_RIDE_REQUEST =
            "SELECT * " +
                    "FROM rides_requests";

    private static final String UPDATE_RIDE_REQUEST =
            "UPDATE rides_requests " +
                    "SET request_time, location_start, location_end, rides_id, users_id " +
                    "WHERE id = ?";

    private final static String DELETE_RIDE_REQUEST =
            "DELETE FROM rides_requests " +
                    "WHERE id = ?";

    @Override
    public RideRequest get(Long id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(GET_RIDE_REQUEST_BY_ID);
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
    public List<RideRequest> getAll() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_RIDE_REQUEST);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<RideRequest> rideRequests = new ArrayList<>();

            while (resultSet.next()) {
                RideRequest rideRequest = extractUserFromResultSet(resultSet);
                rideRequests.add(rideRequest);
            }
            return rideRequests;
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
    public void insert(RideRequest rideRequest) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RIDE_REQUEST);
            preparedStatement.setDate(2, rideRequest.getRequestTime());
            preparedStatement.setString(3, rideRequest.getLocationStart());
            preparedStatement.setString(4, rideRequest.getLocationEnd());
            preparedStatement.setLong(5, rideRequest.getRideId());
            preparedStatement.setLong(6, rideRequest.getUserId());
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
    public void update(RideRequest rideRequest) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RIDE_REQUEST);
            preparedStatement.setDate(1, rideRequest.getRequestTime());
            preparedStatement.setString(2, rideRequest.getLocationStart());
            preparedStatement.setString(3, rideRequest.getLocationEnd());
            preparedStatement.setLong(4, rideRequest.getRideId());
            preparedStatement.setLong(5, rideRequest.getUserId());
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
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RIDE_REQUEST);
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
