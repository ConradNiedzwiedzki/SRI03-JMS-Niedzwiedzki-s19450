package pjatk.sri.mechanicsteam;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

@EnableJms
@Configuration
class JMSConfig
{
    private String BROKER_URL = "tcp://localhost:61616";
    private String BROKER_USERNAME = "admin";
    private String BROKER_PASSWORD = "admin";

    @Bean
    ActiveMQConnectionFactory connectionFactory()
	{
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		
        connectionFactory.setBrokerURL(BROKER_URL);
        connectionFactory.setPassword(BROKER_USERNAME);
        connectionFactory.setUserName(BROKER_PASSWORD);
		
        return connectionFactory;
    }

    @Bean
    public JmsListenerContainerFactory<?> topicListenerFactory(DefaultJmsListenerContainerFactoryConfigurer configurer)
	{
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		
        factory.setPubSubDomain(true);
        factory.setConnectionFactory(connectionFactory());
		
        return factory;
    }
}

