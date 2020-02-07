package com.solvd.uber.daos;

import com.solvd.uber.connection.ConnectionPool;
import com.solvd.uber.models.Service;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ServiceDAO implements IDAO<Service> {

    private final Logger LOGGER = Logger.getLogger(ServiceDAO.class);

    @Override
    public Service get(Long id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Services WHERE id=" + id);

            while (resultSet.next()) {

                return extractUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
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
    public Set<Service> getAll() {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Services");
            Set<Service> services = new HashSet<Service>();

            while (resultSet.next()) {
                Service service = extractUserFromResultSet(resultSet);
                services.add(service);
            }
            return services;
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
    }

    @Override
    public void insert(Service service) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Services VALUES (?, ?, ?)");
            preparedStatement.setLong(1, service.getId());
            preparedStatement.setString(2, service.getName());
            preparedStatement.setInt(3, service.getSerialNumber());

            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void update(Service service) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Services SET name=?, serial_number=?");
            preparedStatement.setString(1, service.getName());
            preparedStatement.setInt(2, service.getSerialNumber());

            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){

            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM Services WHERE id=" + id);

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
