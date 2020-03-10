package com.solvd.uber;

import com.solvd.uber.daos.mysql.UserDAO;
import com.solvd.uber.models.User;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        final Logger LOGGER = Logger.getLogger(Main.class);

        UserDAO userDAO = new UserDAO();
        User userToJson = userDAO.get(1L);
        userDAO.mapperToJson(userToJson);
        User userFromJson = userDAO.mapperFromJson("target/json_user.json");

        //Reflection
        try {
            Class myClass = Class.forName("com.solvd.uber.models.User");
            Field fld[] = myClass.getDeclaredFields();
            for (int i = 0; i < fld.length; i++) {
                LOGGER.info("Field: " + fld[i].getName() + ", Type: " + fld[i].getType());
            }
            Method mtd[] = myClass.getDeclaredMethods();
            for (int i = 0; i < fld.length; i++) {
                LOGGER.info("Method: " + mtd[i].getName() + ", Return Type: " + mtd[i].getReturnType());
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error(e);
        }
    }
}
