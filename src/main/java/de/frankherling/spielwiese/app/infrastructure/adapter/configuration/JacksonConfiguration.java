package de.frankherling.spielwiese.app.infrastructure.adapter.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.Nullable;
import org.springframework.context.annotation.Configuration;

import java.text.DateFormat;
import java.util.TimeZone;

@Configuration
public class JacksonConfiguration {

    public static DateFormat createDefaultDateFormat() {
        DateFormat dateFormat = new RFC3339DateFormat();
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat;
    }

    public static ObjectMapper createDefaultObjectMapper(@Nullable DateFormat dateFormat) {
        if (null == dateFormat) {
            dateFormat = createDefaultDateFormat();
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(dateFormat);
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }
}
