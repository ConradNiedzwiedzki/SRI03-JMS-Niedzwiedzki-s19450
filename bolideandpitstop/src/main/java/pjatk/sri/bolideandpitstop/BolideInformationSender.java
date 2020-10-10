package pjatk.sri.bolideandpitstop;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
class BolideInformationSender
{
    private static final String QUEUE_NAME = "bolide.information";
    private final JmsTemplate jmsTemplate;

    BolideInformationSender(JmsTemplate jmsTemplate)
	{
        this.jmsTemplate = jmsTemplate;
    }

    void sendInfo(BolideInformationDTO bolideInformationDto)
	{
        jmsTemplate.convertAndSend(QUEUE_NAME, bolideInformationDto.convertToHashMap());
    }
}
