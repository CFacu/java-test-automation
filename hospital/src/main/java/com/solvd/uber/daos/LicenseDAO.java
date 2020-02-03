package com.solvd.uber.daos;

import com.solvd.uber.connection.ConnectionPool;
import com.solvd.uber.models.License;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class LicenseDAO implements IDAO<License> {

    private final Logger LOGGER = Logger.getLogger(LicenseDAO.class);

    @Override
    public License get(Long id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Licenses WHERE id=" + id);

            while (resultSet.next()) {

                return extractUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
    }

    public License extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        License license = new License();

        license.setId(resultSet.getLong("id"));
        license.setExpDate(resultSet.getDate("exp_date"));
        license.setNumber(resultSet.getInt("number"));
        license.setDriversId(resultSet.getLong("drivers_id"));

        return license;
    }

    @Override
    public Set<License> getAll() {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Licences");
            Set<License> licenses = new HashSet<License>();

            while (resultSet.next()) {
                License license = extractUserFromResultSet(resultSet);
                licenses.add(license);
            }
            return licenses;
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
    }

    @Override
    public void insert(License license) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Licenses VALUES (?, ?, ?, ?)");
            preparedStatement.setLong(1, license.getId());
            preparedStatement.setInt(2, license.getNumber());
            preparedStatement.setDate(3, license.getExpDate());
            preparedStatement.setLong(4, license.getDriversId());

            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            LOGGER.error(e);
        }

    }

    @Override
    public void update(License license) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Licenses SET number=?, exp_date=?, drivers_id=?");
            preparedStatement.setInt(1, license.getNumber());
            preparedStatement.setDate(2, license.getExpDate());
            preparedStatement.setLong(3, license.getDriversId());

            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){

            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM Licenses WHERE id=" + id);

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
