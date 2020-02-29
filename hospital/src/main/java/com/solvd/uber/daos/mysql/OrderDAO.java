package com.solvd.uber.daos.mysql;

import com.solvd.uber.ConnectionPool;
import com.solvd.uber.daos.interfaces.IOrderDAO;
import com.solvd.uber.models.Order;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class OrderDAO implements IOrderDAO {

    private final Logger LOGGER = Logger.getLogger(OrderDAO.class);

    private static final String INSERT_ORDER =
            "INSERT INTO orders " +
                    "(date, cost, service_id, car_id) " +
                    "VALUES (?, ?, ?, ?)";

    private static final String GET_ORDER_BY_ID =
            "SELECT * " +
                    "FROM orders WHERE id = ?";

    private static final String GET_ORDER =
            "SELECT * " +
                    "FROM orders";

    private static final String UPDATE_ORDER =
            "UPDATE orders " +
                    "SET date, cost, service_id, car_id " +
                    "WHERE id = ?";

    private final static String DELETE_ORDER =
            "DELETE FROM orders " +
                    "WHERE id = ?";

    @Override
    public Order get(Long id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_BY_ID);
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

    public Order extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();

        order.setId(resultSet.getLong("id"));
        order.setDate(resultSet.getDate("date"));
        order.setCost(resultSet.getDouble("cost"));
        order.setServiceId(resultSet.getLong("services_id"));
        order.setCarId(resultSet.getLong("cars_id"));

        return order;
    }

    @Override
    public Set<Order> getAll() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER);
            ResultSet resultSet = preparedStatement.executeQuery();
            Set<Order> orders = new HashSet<Order>();

            while (resultSet.next()) {
                Order order = extractUserFromResultSet(resultSet);
                orders.add(order);
            }
            return orders;
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
    public void insert(Order order) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER);
            preparedStatement.setDate(1, order.getDate());
            preparedStatement.setDouble(2, order.getCost());
            preparedStatement.setLong(3, order.getServiceId());
            preparedStatement.setLong(4, order.getCarId());
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
    public void update(Order order) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER);
            preparedStatement.setDate(1, order.getDate());
            preparedStatement.setDouble(2, order.getCost());
            preparedStatement.setLong(3, order.getServiceId());
            preparedStatement.setLong(4, order.getCarId());
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
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER);
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