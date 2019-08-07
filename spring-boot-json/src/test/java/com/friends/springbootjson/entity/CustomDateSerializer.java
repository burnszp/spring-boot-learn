package com.friends.springbootjson.entity;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateSerializer  extends StdSerializer<Date>
{

    private static final long serialVersionUID = -5451717385630622729L;
    public CustomDateSerializer() {
        this(Date.class);
    }

    public CustomDateSerializer(final Class<Date> t) {
        super(t);
    }

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(value));
    }
}
