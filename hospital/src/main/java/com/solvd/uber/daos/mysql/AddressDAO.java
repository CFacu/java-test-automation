package com.solvd.uber.daos.mysql;

import com.solvd.uber.ConnectionPool;
import com.solvd.uber.daos.interfaces.IAddressDAO;
import com.solvd.uber.models.Address;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddressDAO implements IAddressDAO {

    private final Logger LOGGER = Logger.getLogger(AddressDAO.class);

    private static final String INSERT_ADDRESS =
            "INSERT INTO addresses " +
                    "(street, city, house_number, apartment_number) " +
                    "VALUES (?, ?, ?, ?)";

    private static final String GET_ADDRESS_BY_ID =
            "SELECT * " +
                    "FROM addresses WHERE id = ?";

    private static final String GET_ADDRESSES =
            "SELECT * " +
                    "FROM addresses";

    private static final String UPDATE_ADDRESS =
            "UPDATE addresses " +
                    "SET street, city, house_number, apartment_number " +
                    "WHERE id = ?";

    private final static String DELETE_ADDRESS =
            "DELETE FROM addresses " +
                    "WHERE id = ?";
    @Override
    public Address get(Long id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ADDRESS_BY_ID);
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
    public List<Address> getAll() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ADDRESSES);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Address> addresses = new ArrayList<>();

            while (resultSet.next()) {
                Address address = extractUserFromResultSet(resultSet);
                addresses.add(address);
            }
            return addresses;
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
    public void insert(Address address) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ADDRESS);
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.setInt(3, address.getHouseNumber());
            preparedStatement.setInt(4, address.getApartmentNumber());
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
    public void update(Address address) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ADDRESS);
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.setInt(3, address.getHouseNumber());
            preparedStatement.setInt(4, address.getApartmentNumber());
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
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ADDRESS);
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
