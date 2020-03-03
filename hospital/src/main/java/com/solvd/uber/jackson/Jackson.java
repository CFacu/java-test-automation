package com.solvd.uber.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Jackson {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File ("src\\main\\resources\\drivers.json");
        CollectionType listDrivers = mapper.getTypeFactory().constructCollectionType(List.class, DriverJson.class);
        List<DriverJson> drivers = mapper.readValue(file, listDrivers);
        mapper.writerWithDefaultPrettyPrinter().writeValues(new File("src\\main\\resources\\jackson.json")).write(drivers);
    }
}