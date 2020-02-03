package com.solvd.uber.daos;

import com.solvd.uber.connection.ConnectionPool;
import com.solvd.uber.models.Address;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class AddressDAO implements IDAO<Address> {

    private final Logger LOGGER = Logger.getLogger(AddressDAO.class);

    @Override
    public Address get(Long id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Addresses WHERE id=" + id);

            while (resultSet.next()) {

                return extractUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
    }

    public Address extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        Address address = new Address();

        address.setId(resultSet.getLong("id"));
        address.setStreet(resultSet.getString("street"));
        address.setCity(resultSet.getString("city"));
        address.setHouseNumber(resultSet.getInt("house_number"));
        address.setApartmentNumber(resultSet.getInt("apartment_number"));

        return address;
    }

    @Override
    public Set<Address> getAll() {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Addresses");
            Set<Address> addresses = new HashSet<Address>();

            while (resultSet.next()) {
                Address address = extractUserFromResultSet(resultSet);
                addresses.add(address);
            }
            return addresses;
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
    }

    @Override
    public void insert(Address address) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Addresses VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setLong(1, address.getId());
            preparedStatement.setString(2, address.getStreet());
            preparedStatement.setString(3, address.getCity());
            preparedStatement.setInt(4, address.getHouseNumber());
            preparedStatement.setInt(5, address.getApartmentNumber());

            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void update(Address address) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Addresses SET street=?, city=?, house_number=?, apartment_number=?");
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.setInt(3, address.getHouseNumber());
            preparedStatement.setInt(4, address.getApartmentNumber());

            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){

            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM Addresses WHERE id=" + id);

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
