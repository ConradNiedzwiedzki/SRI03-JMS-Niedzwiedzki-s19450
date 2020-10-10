package pjatk.sri.teamleader;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
class TeamLeaderListener
{
    private final TeamLeaderProducer teamLeaderProducer;

    TeamLeaderListener(TeamLeaderProducer teamLeaderProducer)
	{
        this.teamLeaderProducer = teamLeaderProducer;
    }

    @JmsListener(destination = "pitstop.request", containerFactory = "queueListenerFactory")
    void onRequestPitStop(Message message) throws JMSException
	{
		Integer parameter = 1;
        ActiveMQMessage msg = (ActiveMQMessage) message.getPayload();
        Destination replyDestu = msg.getJMSReplyTo();

        ActiveMQMessage replyMsg = new ActiveMQMessage();
        replyMsg.setJMSReplyTo(replyChannel);
        replyMsg.setJMSCorrelationID(msg.getJMSCorrelationID());

        try
		{
            parameter = (Integer) msg.getProperties().get("request");
        }
		catch (IOException exception)
		{
            exception.printStackTrace();
        }
		
        if (parameter % 2 == 0)
		{
            replyMsg.setObjectProperty("decision", true);
        }
		else
		{
            replyMsg.setObjectProperty("decision", false);
        }
		
        teamLeaderProducer.sendReply(replyMsg);
    }
	
    @JmsListener(destination = "monitor.critical.damage", containerFactory = "topicListenerFactory")
    void onCriticalDamage(Message<Map<String, Object>> message)
	{
        log.info("There is critical problem of bolide, inform driver that pit-stop is needed.");
        teamLeaderProducer.notifyDriver();
    }
}
