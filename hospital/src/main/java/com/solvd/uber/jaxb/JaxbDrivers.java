package com.solvd.uber.jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "drivers")
public class JaxbDrivers {
    private List<JaxbDriver> drivers;


    public List<JaxbDriver> getDrivers() {
        return drivers;
    }

    @XmlElement(name = "driver")
    public void setDrivers(List<JaxbDriver> drivers) {
        this.drivers = drivers;
    }
}
