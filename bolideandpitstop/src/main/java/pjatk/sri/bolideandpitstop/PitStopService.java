package pjatk.sri.bolideandpitstop;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQMessage;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

@Service
class PitStopService
{
    private final ActiveMQConnectionFactory activeMQConnectionFactory;

    PitStopService(ActiveMQConnectionFactory activeMQConnectionFactory)
	{
        this.activeMQConnectionFactory = activeMQConnectionFactory;
    }

    public boolean askForPitStop(Integer number) throws JMSException
	{
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
		
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination requestDestination = new ActiveMQQueue("pitstop.request");
		
        MessageProducer messageProducer = session.createProducer(requestDestination);
        Destination replyDestination = new ActiveMQQueue("pitstop.reply");

        Message message = session.createMessage();
        message.setIntProperty("request", number);
        message.setJMSReplyTo(replyDestination);
        message.setJMSCorrelationID(Long.toHexString(new Random(System.currentTimeMillis()).nextLong()));

        MessageConsumer replyMessageConsumer = session.createConsumer(replyDestination);
        messageProducer.send(message);

        Message replyMessage = replyMessageConsumer.receive();
        Map properties = null;
		
        try
		{
            properties = ((ActiveMQMessage) replyMessage).getProperties();
        }
		catch (IOException e)
		{
            e.printStackTrace();
        }
		
        connection.stop();
        replyMessageConsumer.close();
		
        return (Boolean) properties.get("decision");
    }
}
