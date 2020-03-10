package com.solvd.uber.daos.mysql;

import com.solvd.uber.ConnectionPool;
import com.solvd.uber.daos.interfaces.IRideDAO;
import com.solvd.uber.models.Ride;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RideDAO implements IRideDAO {

    private final Logger LOGGER = Logger.getLogger(RideDAO.class);

    private static final String INSERT_RIDE =
            "INSERT INTO rides " +
                    "(distance, pick_up_time, drop_off_time, cost, drivers_id) " +
                    "VALUES (?, ?, ?, ?, ?)";

    private static final String GET_RIDE_BY_ID =
            "SELECT * " +
                    "FROM rides WHERE id = ?";

    private static final String GET_RIDE =
            "SELECT * " +
                    "FROM rides";

    private static final String UPDATE_RIDE =
            "UPDATE rides " +
                    "SET distance, pick_up_time, drop_off_time, cost, drivers_id " +
                    "WHERE id = ?";

    private final static String DELETE_RIDE =
            "DELETE FROM rides " +
                    "WHERE id = ?";

    @Override
    public Ride get(Long id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(GET_RIDE_BY_ID);
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
    public List<Ride> getAll() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_RIDE);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Ride> rides = new ArrayList<>();

            while (resultSet.next()) {
                Ride ride = extractUserFromResultSet(resultSet);
                rides.add(ride);
            }
            return rides;
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
    public void insert(Ride ride) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RIDE);
            preparedStatement.setInt(1, ride.getDistance());
            preparedStatement.setDate(2, ride.getPickUpTime());
            preparedStatement.setDate(3, ride.getDropOffTime());
            preparedStatement.setDouble(4, ride.getCost());
            preparedStatement.setLong(5, ride.getDriversId());
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
    public void update(Ride ride) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RIDE);
            preparedStatement.setInt(1, ride.getDistance());
            preparedStatement.setDate(2, ride.getPickUpTime());
            preparedStatement.setDate(3, ride.getDropOffTime());
            preparedStatement.setDouble(4, ride.getCost());
            preparedStatement.setLong(5, ride.getDriversId());
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
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RIDE);
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
