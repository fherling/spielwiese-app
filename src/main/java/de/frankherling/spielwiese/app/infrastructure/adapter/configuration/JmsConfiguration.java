package de.frankherling.spielwiese.app.infrastructure.adapter.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.frankherling.spielwiese.app.infrastructure.adapter.jms.order.OrderConsumerErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
@EnableJms
public class JmsConfiguration {
    @Bean
    public MessageConverter jacksonJmsMessageConverter(ObjectMapper objectMapper) {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        converter.setObjectMapper(objectMapper);
        return converter;
    }

    @Bean("connectionFactory")
    public JmsListenerContainerFactory<?> jmsListenerContainerFactory(OrderConsumerErrorHandler errorHandler, MessageConverter jacksonJmsMessageConverter) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setErrorHandler(errorHandler);
        factory.setReceiveTimeout(60000L);
        factory.setMessageConverter(jacksonJmsMessageConverter);
        return factory;
    }

}
