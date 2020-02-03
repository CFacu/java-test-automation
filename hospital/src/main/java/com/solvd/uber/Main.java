package com.solvd.uber;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.uber.daos.UserDAO;
import com.solvd.uber.models.User;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        UserDAO user = new UserDAO();
        User asd = user.get(123L);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File("target/user.json"), asd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
