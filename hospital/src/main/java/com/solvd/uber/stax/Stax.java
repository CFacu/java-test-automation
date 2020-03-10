package com.solvd.uber.stax;

import com.solvd.uber.daos.mysql.DriverDAO;
import com.solvd.uber.daos.mysql.LicenseDAO;
import com.solvd.uber.models.Driver;
import com.solvd.uber.models.License;
import org.apache.log4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;

public class Stax {
    public static void main(String[] args) throws FileNotFoundException, XMLStreamException, ParseException {
        final Logger LOGGER = Logger.getLogger(Stax.class);

        /*XMLOutputFactory outputFactory = XMLOutputFactory.newFactory();
        DriverDAO driverDAO = new DriverDAO();
        List<Driver> drivers = driverDAO.getAll();

        try(FileOutputStream fos = new FileOutputStream("src\\main\\resources\\staxOut.xml")) {
            XMLStreamWriter writer = outputFactory.createXMLStreamWriter(fos);
            writer.writeStartDocument();
            writer.writeCharacters("\n");
            writer.writeStartElement("drivers");
            writer.writeCharacters("\n");

            for (Driver driver : drivers) {
                writer.writeCharacters("\t");
                writer.writeStartElement("driver");
                writer.writeCharacters("\n\t\t");
                writer.writeStartElement("id");
                writer.writeCharacters(driver.getId().toString());
                writer.writeEndElement();

                writer.writeCharacters("\n\t\t");
                writer.writeStartElement("name");
                writer.writeCharacters(driver.getName());
                writer.writeEndElement();

                writer.writeCharacters("\n\t\t");
                writer.writeStartElement("password");
                writer.writeCharacters(driver.getPassword());
                writer.writeEndElement();

                writer.writeCharacters("\n\t\t");
                writer.writeStartElement("birthDate");
                writer.writeCharacters(driver.getBirthDate().toString());
                writer.writeEndElement();

                writer.writeCharacters("\n\t\t");
                writer.writeStartElement("phoneNumber");
                writer.writeCharacters(driver.getPhoneNumber().toString());
                writer.writeEndElement();

                writer.writeCharacters("\n\t\t");
                writer.writeStartElement("rate");
                writer.writeCharacters(driver.getRate().toString());
                writer.writeEndElement();

                writer.writeCharacters("\n\t\t");
                writer.writeStartElement("licence");

                writer.writeCharacters("\n\t\t\t");
                writer.writeStartElement("id");
                writer.writeCharacters(driver.getLicense().getId().toString());
                writer.writeEndElement();

                writer.writeCharacters("\n\t\t\t");
                writer.writeStartElement("number");
                writer.writeCharacters(driver.getLicense().getNumber().toString());
                writer.writeEndElement();

                writer.writeCharacters("\n\t\t\t");
                writer.writeStartElement("expDate");
                writer.writeCharacters(driver.getLicense().getExpDate().toString());
                writer.writeEndElement();

                writer.writeCharacters("\n\t\t");
                writer.writeEndElement();

                writer.writeCharacters("\n\t");
                writer.writeEndElement();

                writer.writeCharacters("\n");
            }
            writer.writeEndElement();
            writer.writeEndDocument();
            writer.close();
        } catch (IOException | XMLStreamException e) {
            LOGGER.error(e);
        }*/

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream("src\\main\\resources\\staxIn.xml"));

        Driver driver = new Driver();
        License license = new License();
        while (reader.hasNext()) {
            XMLEvent nextEvent = reader.nextEvent();
            if (nextEvent.isStartElement()) {
                StartElement startElement = nextEvent.asStartElement();
                switch (startElement.getName().getLocalPart()) {
                    case "driver":
                        Attribute driverId = startElement.getAttributeByName(new QName("driverId"));
                        if (driverId != null) {
                            driver.setId(Long.parseLong(driverId.getValue()));
                        }
                        break;
                    case "name":
                        nextEvent = reader.nextEvent();
                        driver.setName(nextEvent.asCharacters().getData());
                        break;
                    case "password":
                        nextEvent = reader.nextEvent();
                        driver.setPassword(nextEvent.asCharacters().getData());
                        break;
                    case "birthDate":
                        nextEvent = reader.nextEvent();
                        driver.setBirthDate(Date.valueOf(nextEvent.asCharacters().getData()));
                        break;
                    case "phoneNumber":
                        nextEvent = reader.nextEvent();
                        driver.setPhoneNumber(Integer.parseInt(nextEvent.asCharacters().getData()));
                        break;
                    case "rate":
                        nextEvent = reader.nextEvent();
                        driver.setRate(Integer.parseInt(nextEvent.asCharacters().getData()));
                        break;
                    case "license":
                        Attribute licenseId = startElement.getAttributeByName(new QName("licenseId"));
                        if (licenseId != null) {
                            license.setId(Long.parseLong(licenseId.getValue()));
                            license.setDriverId(driver.getId());
                            driver.setLicense(license);
                        }
                        break;
                    case "number":
                        nextEvent = reader.nextEvent();
                        license.setNumber(Integer.parseInt(nextEvent.asCharacters().getData()));
                        break;
                    case "expDate":
                        nextEvent = reader.nextEvent();
                        license.setExpDate(Date.valueOf(nextEvent.asCharacters().getData()));
                        break;
                }
            }
            if (nextEvent.isEndElement()) {
                EndElement endElement = nextEvent.asEndElement();
                if (endElement.getName().getLocalPart().equals("driver")) {
                    DriverDAO driverDAO = new DriverDAO();
                    LicenseDAO licenseDAO = new LicenseDAO();
                    //driverDAO.insert(driver);
                    //licenseDAO.insert(license);
                }
            }

        }
    }
}
