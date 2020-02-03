package com.solvd.uber.daos;

import com.solvd.uber.connection.ConnectionPool;
import com.solvd.uber.models.CreditCard;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class CreditCardDAO implements IDAO<CreditCard> {

    private final Logger LOGGER = Logger.getLogger(CreditCardDAO.class);

    @Override
    public CreditCard get(Long id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Credit_Cards WHERE id=" + id);

            while (resultSet.next()) {

                return extractUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
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
    public Set<CreditCard> getAll() {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Credit_Cards");
            Set<CreditCard> creditCards = new HashSet<CreditCard>();

            while (resultSet.next()) {
                CreditCard creditCard = extractUserFromResultSet(resultSet);
                creditCards.add(creditCard);
            }
            return creditCards;
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
    }

    @Override
    public void insert(CreditCard creditCard) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Credit_Cards VALUES (?, ?, ?, ?)");
            preparedStatement.setLong(1, creditCard.getId());
            preparedStatement.setInt(2, creditCard.getNumber());
            preparedStatement.setDate(3, creditCard.getExpDate());
            preparedStatement.setLong(4, creditCard.getUserId());

            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void update(CreditCard creditCard) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Credit_Cards SET number=?, exp_date=?, users_id=?");
            preparedStatement.setInt(1, creditCard.getNumber());
            preparedStatement.setDate(2, creditCard.getExpDate());
            preparedStatement.setLong(3, creditCard.getUserId());

            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()){

            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM Credit_Cards WHERE id=" + id);

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
