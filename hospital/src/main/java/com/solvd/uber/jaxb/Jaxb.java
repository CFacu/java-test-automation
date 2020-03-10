package com.solvd.uber.jaxb;

import com.solvd.uber.daos.mysql.DriverDAO;
import com.solvd.uber.models.Driver;
import org.apache.log4j.Logger;

import javax.xml.bind.*;
import java.io.File;

public class Jaxb {

    private static final Logger LOGGER = Logger.getLogger(Jaxb.class);

    public static void main(String[] args) throws JAXBException {

        DriverDAO driverDAO = new DriverDAO();
        Driver driverOut = driverDAO.get(1L);
        try {
            JAXBContext context = JAXBContext.newInstance(Driver.class);

            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            File file = new File("src\\main\\resources\\jaxbOut.xml");

            marshaller.marshal(driverOut, file);
        } catch (JAXBException e) {
            LOGGER.error(e);
        }

        File fileIn = new File("src\\main\\resources\\jaxbIn.xml");

        try {
            JAXBContext context = JAXBContext.newInstance(Driver.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Driver driverIn = (Driver) unmarshaller.unmarshal(fileIn);
            LOGGER.info(driverIn.getName());
            LOGGER.info(driverIn.getBirthDate());
        } catch (JAXBException e) {
            LOGGER.error(e);
        }
    }
}
