package com.solvd.uber.stax;

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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Stax {
    public static void main(String[] args) throws FileNotFoundException, XMLStreamException, ParseException {
        final Logger LOGGER = Logger.getLogger(Stax.class);
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream("src\\main\\resources\\drivers.xml"));

        List<StaxDriver> drivers = new ArrayList<>();
        StaxDriver driver = null;
        StaxLicense license = null;
        while (reader.hasNext()) {
            XMLEvent nextEvent = reader.nextEvent();
            if (nextEvent.isStartElement()) {
                StartElement startElement = nextEvent.asStartElement();
                switch (startElement.getName().getLocalPart()) {
                    case "driver":
                        driver = new StaxDriver();
                        Attribute id = startElement.getAttributeByName(new QName("id"));
                        driver.setId(Long.parseLong(id.getValue()));
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
                        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String tempDate = nextEvent.toString();
                        Date date = formatter.parse(tempDate);
                        driver.setBirthDate(date);
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
                        license = new StaxLicense();
                        Attribute licenseId = startElement.getAttributeByName(new QName("id"));
                        license.setId(Long.parseLong(licenseId.getValue()));
                        break;
                    case "number":
                        nextEvent = reader.nextEvent();
                        license.setNumber(Integer.parseInt(nextEvent.asCharacters().getData()));
                        break;
                    case "expDate":
                        nextEvent = reader.nextEvent();
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        String temp = nextEvent.toString();
                        Date expDate = format.parse(temp);
                        license.setExpDate(expDate);
                        break;
                }
            }
            if (nextEvent.isEndElement()) {
                EndElement endElement = nextEvent.asEndElement();
                switch (endElement.getName().getLocalPart()) {
                    case "driver":
                        drivers.add(driver);
                    case "license":
                        driver.setLicense(license);
                }
            }
        }
        for (StaxDriver uberDriver : drivers) {
            LOGGER.info("id: " + uberDriver.getId());
            LOGGER.info("name: " + uberDriver.getName());
            LOGGER.info("password: " + uberDriver.getPassword());
            LOGGER.info("birthDate: " + uberDriver.getBirthDate());
            LOGGER.info("phoneNumber: " + uberDriver.getBirthDate());
            LOGGER.info("rate: " + uberDriver.getRate());
            LOGGER.info("licenseId: " + uberDriver.getLicense().getId());
            LOGGER.info("licenseNumber: " + uberDriver.getLicense().getNumber());
            LOGGER.info("licenseExpDate: " + uberDriver.getLicense().getExpDate());
        }

        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(new FileOutputStream("src/main/resources/staxOutput.xml"));
        xmlStreamWriter.writeStartDocument();
        xmlStreamWriter.writeCharacters("\n");
        xmlStreamWriter.writeStartElement("drivers");
        xmlStreamWriter.writeCharacters("\n");
        for (StaxDriver uberDriver : drivers) {
            xmlStreamWriter.writeStartElement("driver");
            xmlStreamWriter.writeAttribute("id", String.valueOf(uberDriver.getId()));
            xmlStreamWriter.writeCharacters("\n");
            simpleElement(xmlStreamWriter, "name", uberDriver.getName());
            simpleElement(xmlStreamWriter, "password", uberDriver.getPassword());
            simpleElement(xmlStreamWriter, "birthDate", uberDriver.getBirthDate().toString());
            simpleElement(xmlStreamWriter, "phoneNumber", uberDriver.getPhoneNumber().toString());
            simpleElement(xmlStreamWriter, "rate", uberDriver.getRate().toString());

            xmlStreamWriter.writeStartElement("license");
            xmlStreamWriter.writeAttribute("id", uberDriver.getLicense().getId().toString());
            xmlStreamWriter.writeCharacters("\n");
            simpleElement(xmlStreamWriter, "number", uberDriver.getLicense().getNumber().toString());
            simpleElement(xmlStreamWriter, "expDate", uberDriver.getLicense().getExpDate().toString());
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeCharacters("\n");
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeCharacters("\n");
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeCharacters("\n");

        }
    }
    public static void simpleElement(XMLStreamWriter xml, String element, String chars) throws XMLStreamException {
        xml.writeStartElement(element);
        xml.writeCharacters(chars);
        xml.writeEndElement();
        xml.writeCharacters("\n");
    }
}
