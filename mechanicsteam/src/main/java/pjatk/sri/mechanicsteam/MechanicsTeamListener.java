package pjatk.sri.mechanicsteam;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
class MechanicsTeamListener
{
    @JmsListener(destination = "monitor.critical.damage", containerFactory = "topicListenerFactory")
    void onMessage(Message<Map<String, Object>> message)
	{
        log.info("Mechanics team is ready for fixing critical issue.");
    }
}
