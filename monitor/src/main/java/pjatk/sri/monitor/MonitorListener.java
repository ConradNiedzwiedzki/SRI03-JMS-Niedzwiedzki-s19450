package pjatk.sri.monitor;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MonitorListener
{
    private final SeverityPicker severityPicker;

    MonitorListener(SeverityPicker severityPicker)
	{
        this.severityPicker = severityPicker;
    }

    @JmsListener(containerFactory = "topicListenerFactory", destination = "bolide.information")
    public void onMessage(Message<Map<String,Object>> message)
	{
        severityPicker.pickSeverity(BolideInformationDTO.creafeFromMap(message.getPayload()));
    }
}
