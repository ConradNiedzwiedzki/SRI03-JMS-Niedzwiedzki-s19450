package pjatk.sri.monitor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class SeverityPicker
{
    private final JmsTemplate queueTemplate;
    private final JmsTemplate topicTemplate;

    public SeverityPicker(@Qualifier("queueTemplate") JmsTemplate queueTemplate, @Qualifier("topicTemplate") JmsTemplate topicTemplate)
	{
        this.queueTemplate = queueTemplate;
        this.topicTemplate = topicTemplate;
    }

    public void pickSeverity(BolideInformationDTO bolideInformationDto)
	{
        Map<String, Object> bolideInformationDto = info.convertToHashMap();
		
        if (info.getEngineTemperature() > 100)
		{
            bolideInformationDto.put("message", "Critical damage detected, pit stop pullover is reuired");
            topicTemplate.convertAndSend("monitor.critical.damage", bolideInformationDto);
        }
		else if (info.getEngineTemperature() > 50)
		{
            bolideInformationDto.put("message", "There is a small problem with bolide.");
            queueTemplate.convertAndSend("monitor.small.damage", bolideInformationDto);
        }
    }
}
