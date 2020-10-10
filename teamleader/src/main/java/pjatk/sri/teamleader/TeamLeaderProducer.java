package pjatk.sri.director;

import org.apache.activemq.command.ActiveMQMessage;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
class TeamLeaderProducer
{
    private final JmsTemplate jmsTemplate;

    TeamLeaderProducer(JmsTemplate jmsTemplate)
	{
        this.jmsTemplate = jmsTemplate;
    }

    public void sendReply(ActiveMQMessage reply)
	{
       jmsTemplate.convertAndSend(reply.getJMSReplyTo(), reply);
    }
	
	public void notifyDriver()
	{
        jmsTemplate.convertAndSend("pitstop.pullover.needed", new Object());
    }
}
