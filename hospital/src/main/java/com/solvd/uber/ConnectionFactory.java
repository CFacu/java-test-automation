package com.solvd.uber;
import com.mysql.cj.jdbc.Driver;
import com.solvd.uber.daos.UserDAO;
import com.solvd.uber.models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/uber", "root", "asd123");
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to database", ex);
        }
    }

    public static void main(String[] args) {

        User user = new User();
        user.setId(123l);
        user.setName("John");
        user.setAge(35);
        user.setLocation("Hawaii");
        user.setRate(4);
        user.setPhoneNumber(123456789);
        user.setPassword("asd123");

        UserDAO userDAO = new UserDAO();
        User userGet = userDAO.get(1l);
        System.out.println(userGet.getName());

        userDAO.insert(user);
        User userInserted = userDAO.get(123l);
        System.out.println(userInserted.getName());
    }
}
