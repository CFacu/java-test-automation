package com.solvd.uber.daos;

import com.solvd.uber.connection.ConnectionPool;
import com.solvd.uber.models.Order;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class OrderDAO implements IDAO<Order> {

    private final Logger LOGGER = Logger.getLogger(OrderDAO.class);

    @Override
    public Order get(Long id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Orders WHERE id=" + id);

            while (resultSet.next()) {

                return extractUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
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
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Orders");
            Set<Order> orders = new HashSet<Order>();

            while (resultSet.next()) {
                Order order = extractUserFromResultSet(resultSet);
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
    }

    @Override
    public void insert(Order order) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Orders VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setLong(1, order.getId());
            preparedStatement.setDate(2, order.getDate());
            preparedStatement.setDouble(3, order.getCost());
            preparedStatement.setLong(4, order.getServiceId());
            preparedStatement.setLong(5, order.getCarId());

            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void update(Order order) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Orders SET date=?, cost=?, services_id=?, cars_id=?");
            preparedStatement.setDate(1, order.getDate());
            preparedStatement.setDouble(2, order.getCost());
            preparedStatement.setLong(3, order.getServiceId());
            preparedStatement.setLong(4, order.getCarId());

            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){

            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM Orders WHERE id=" + id);

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
