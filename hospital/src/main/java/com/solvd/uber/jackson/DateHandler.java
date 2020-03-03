package com.solvd.uber.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHandler extends StdSerializer<Date> {
    private final Logger LOGGER = Logger.getLogger(DateHandler.class);

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public DateHandler() {
        this(null);
    }

    public DateHandler(Class<Date> clazz) {
        super(clazz);
    }

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(formatter.format(date));

    }

}
