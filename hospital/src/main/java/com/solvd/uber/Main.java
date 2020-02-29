package com.solvd.uber;

import com.solvd.uber.daos.mysql.UserDAO;
import com.solvd.uber.models.User;

public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        User userToJson = userDAO.get(1L);

        userDAO.mapperToJson(userToJson);

        User userFromJson = userDAO.mapperFromJson("target/json_user.json");


    }
}
