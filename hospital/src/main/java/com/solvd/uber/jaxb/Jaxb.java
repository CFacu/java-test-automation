package com.solvd.uber.jaxb;

import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;

public class Jaxb {

    private static final Logger LOGGER = Logger.getLogger(Jaxb.class);

    public static void main(String[] args) throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(JaxbDrivers.class);

        JaxbDrivers drivers = (JaxbDrivers) context.createUnmarshaller().unmarshal(new File("src/main/resources/drivers.xml"));

        for (JaxbDriver driver : drivers.getDrivers()) {
            LOGGER.info("id: " + driver.getId());
            LOGGER.info("name: " + driver.getName());
            LOGGER.info("password: " + driver.getPassword());
            LOGGER.info("birthDate: " + driver.getBirthDate());
            LOGGER.info("phoneNumber: " + driver.getPhoneNumber());
            LOGGER.info("rate: " + driver.getRate());
            LOGGER.info("licenseId: " + driver.getLicense().getId());
            LOGGER.info("licenseNumber: " + driver.getLicense().getNumber());
            LOGGER.info("licenseExpDate: " + driver.getLicense().getExpDate());
        }
    }
}
