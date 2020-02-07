package com.solvd.uber;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.uber.daos.UserDAO;
import com.solvd.uber.models.User;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        User userToJson = userDAO.get(1L);

        userDAO.mapperToJson(userToJson);

        User userFromJson = userDAO.mapperFromJson("target/json_user.json");


    }
}
