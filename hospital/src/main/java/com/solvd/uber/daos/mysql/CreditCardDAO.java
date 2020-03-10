package com.solvd.uber.daos.mysql;

import com.solvd.uber.ConnectionPool;
import com.solvd.uber.daos.interfaces.ICreditCardDAO;
import com.solvd.uber.models.CreditCard;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CreditCardDAO implements ICreditCardDAO {

    private final Logger LOGGER = Logger.getLogger(CreditCardDAO.class);

    private static final String INSERT_CREDIT_CARD =
            "INSERT INTO credit_cards " +
                    "(number, exp_date, users_id) " +
                    "VALUES (?, ?, ?)";

    private static final String GET_CREDIT_CARD_BY_ID =
            "SELECT * " +
                    "FROM credit_cards WHERE id = ?";

    private static final String GET_CREDIT_CARD =
            "SELECT * " +
                    "FROM credit_cards";

    private static final String UPDATE_CREDIT_CARD =
            "UPDATE credit_cards " +
                    "SET number, exp_date, users_id " +
                    "WHERE id = ?";

    private final static String DELETE_CREDIT_CARD =
            "DELETE FROM credit_cards " +
                    "WHERE id = ?";

    @Override
    public CreditCard get(Long id) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CREDIT_CARD_BY_ID);
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

    public CreditCard extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        CreditCard creditCard = new CreditCard();

        creditCard.setId(resultSet.getLong("id"));
        creditCard.setNumber(resultSet.getInt("number"));
        creditCard.setExpDate(resultSet.getDate("exp_date"));
        creditCard.setUserId(resultSet.getLong("users_id"));

        return creditCard;
    }

    @Override
    public List<CreditCard> getAll() {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CREDIT_CARD);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<CreditCard> creditCards = new ArrayList<>();

            while (resultSet.next()) {
                CreditCard creditCard = extractUserFromResultSet(resultSet);
                creditCards.add(creditCard);
            }
            return creditCards;
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
    public void insert(CreditCard creditCard) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CREDIT_CARD);
            preparedStatement.setInt(1, creditCard.getNumber());
            preparedStatement.setDate(2, creditCard.getExpDate());
            preparedStatement.setLong(3, creditCard.getUserId());
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
    public void update(CreditCard creditCard) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CREDIT_CARD);
            preparedStatement.setInt(1, creditCard.getNumber());
            preparedStatement.setDate(2, creditCard.getExpDate());
            preparedStatement.setLong(3, creditCard.getUserId());
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
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CREDIT_CARD);
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
