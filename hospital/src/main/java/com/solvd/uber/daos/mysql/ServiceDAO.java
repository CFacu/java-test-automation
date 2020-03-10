package com.solvd.uber.daos.mysql;

import com.solvd.uber.ConnectionPool;
import com.solvd.uber.daos.interfaces.IServiceDAO;
import com.solvd.uber.models.Service;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ServiceDAO implements IServiceDAO {

    private final Logger LOGGER = Logger.getLogger(ServiceDAO.class);

    private static final String INSERT_SERVICE =
            "INSERT INTO services " +
                    "(name, serial_number) " +
                    "VALUES (?, ?)";

    private static final String GET_SERVICE_BY_ID =
            "SELECT * " +
                    "FROM services WHERE id = ?";

    private static final String GET_SERVICE =
            "SELECT * " +
                    "FROM services";

    private static final String UPDATE_SERVICE =
            "UPDATE services " +
                    "SET name, serial_number " +
                    "WHERE id = ?";

    private final static String DELETE_SERVICE =
            "DELETE FROM services " +
                    "WHERE id = ?";

    @Override
    public Service get(Long id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(GET_SERVICE_BY_ID);
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

    public Service extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        Service service = new Service();

        service.setId(resultSet.getLong("id"));
        service.setName(resultSet.getString("name"));
        service.setSerialNumber(resultSet.getInt("serial_number"));

        return service;
    }

    @Override
    public List<Service> getAll() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_SERVICE);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Service> services = new ArrayList<>();

            while (resultSet.next()) {
                Service service = extractUserFromResultSet(resultSet);
                services.add(service);
            }
            return services;
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
    public void insert(Service service) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SERVICE);
            preparedStatement.setString(1, service.getName());
            preparedStatement.setInt(2, service.getSerialNumber());
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
    public void update(Service service) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SERVICE);
            preparedStatement.setString(1, service.getName());
            preparedStatement.setInt(2, service.getSerialNumber());
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
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SERVICE);
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
