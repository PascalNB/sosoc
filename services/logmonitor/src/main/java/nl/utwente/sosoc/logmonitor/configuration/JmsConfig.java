package nl.utwente.sosoc.logmonitor.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import nl.utwente.sosoc.logmonitor.model.Alarm;
import nl.utwente.sosoc.logmonitor.util.RFC3339DateFormat;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import java.util.Map;

@Configuration
@EnableJms
public class JmsConfig {

    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;

    @Value("${spring.activemq.user}")
    private String brokerUsername;

    @Value("${spring.activemq.password}")
    private String brokerPassword;

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(brokerUrl);

        if(brokerUsername != null && !brokerUsername.isEmpty() && brokerPassword != null && !brokerPassword.isEmpty()) {
            activeMQConnectionFactory.setUserName(brokerUsername);
            activeMQConnectionFactory.setPassword(brokerPassword);
        }

        return activeMQConnectionFactory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jacksonMessageConverter());  // Set the custom message converter
        return factory;
    }

    @Bean
    public MessageConverter jacksonMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("JMSType");
        converter.setTypeIdMappings(Map.of(
            "alarm", Alarm.class
        ));
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(RFC3339DateFormat.getInstance());
        objectMapper.registerModule(new JavaTimeModule());
        converter.setObjectMapper(objectMapper);
        return converter;
    }
}
