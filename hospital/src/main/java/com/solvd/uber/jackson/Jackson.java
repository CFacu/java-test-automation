package com.solvd.uber.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.uber.daos.mysql.DriverDAO;
import com.solvd.uber.models.Driver;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Jackson {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Driver driver = mapper.readValue(new File("src\\main\\resources\\jacksonIn.json"), Driver.class);
        DriverDAO driverDAO = new DriverDAO();
        //driverDAO.insert(driver);
        List<Driver> drivers = driverDAO.getAll();
        mapper.writerWithDefaultPrettyPrinter().writeValues(new File("src\\main\\resources\\jacksonOut.json")).write(drivers);
    }
}