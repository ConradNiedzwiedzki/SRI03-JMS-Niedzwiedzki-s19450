package pjatk.sri.monitor;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import java.util.concurrent.TimeoutException;

@EnableJms
@Configuration
class JMSConfig
{
    private String BROKER_URL = "tcp://localhost:61616";
    private String BROKER_USERNAME = "admin";
    private String BROKER_PASSWORD = "admin";

    @Bean
    public ActiveMQConnectionFactory connectionFactory()
	{
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		
        connectionFactory.setBrokerURL(BROKER_URL);
        connectionFactory.setPassword(BROKER_USERNAME);
        connectionFactory.setUserName(BROKER_PASSWORD);
		
        return connectionFactory;
    }
	
	@Bean("queueTemplate")
    public JmsTemplate queueTemplate()
	{
        JmsTemplate template = new JmsTemplate();
		
        template.setConnectionFactory(connectionFactory());
        template.setPubSubDomain(false);
		
        return template;
    }

    @Bean("topicTemplate")
    public JmsTemplate topicTemplate()
	{
        JmsTemplate template = new JmsTemplate();
		
        template.setConnectionFactory(connectionFactory());
        template.setPubSubDomain(true);
		
        return template;
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