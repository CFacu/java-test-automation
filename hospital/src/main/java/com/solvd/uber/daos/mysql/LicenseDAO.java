package com.solvd.uber.daos.mysql;

import com.solvd.uber.ConnectionPool;
import com.solvd.uber.daos.interfaces.ILicenseDAO;
import com.solvd.uber.models.License;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class LicenseDAO implements ILicenseDAO {

    private final Logger LOGGER = Logger.getLogger(LicenseDAO.class);

    private static final String INSERT_LICENSE =
            "INSERT INTO licenses " +
                    "(number, exp_date) " +
                    "VALUES (?, ?)";

    private static final String GET_LICENSE_BY_ID =
            "SELECT * " +
                    "FROM licenses WHERE id = ?";

    private static final String GET_LICENSE =
            "SELECT * " +
                    "FROM licenses";

    private static final String UPDATE_LICENSE =
            "UPDATE licenses " +
                    "SET number, exp_date" +
                    "WHERE id = ?";

    private final static String DELETE_LICENSE =
            "DELETE FROM licenses " +
                    "WHERE id = ?";

    @Override
    public License get(Long id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(GET_LICENSE_BY_ID);
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

    public License extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        License license = new License();

        license.setId(resultSet.getLong("id"));
        license.setExpDate(resultSet.getDate("exp_date"));
        license.setNumber(resultSet.getInt("number"));

        return license;
    }

    @Override
    public Set<License> getAll() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_LICENSE);
            ResultSet resultSet = preparedStatement.executeQuery();
            Set<License> licenses = new HashSet<License>();

            while (resultSet.next()) {
                License license = extractUserFromResultSet(resultSet);
                licenses.add(license);
            }
            return licenses;
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
    public void insert(License license) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LICENSE);
            preparedStatement.setInt(1, license.getNumber());
            preparedStatement.setDate(2, license.getExpDate());
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
    public void update(License license) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LICENSE);
            preparedStatement.setInt(1, license.getNumber());
            preparedStatement.setDate(2, license.getExpDate());
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
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_LICENSE);
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
