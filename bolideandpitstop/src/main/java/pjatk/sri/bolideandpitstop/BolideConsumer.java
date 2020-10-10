package pjatk.sri.bolideandpitstop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BolideConsumer
{
	@JmsListener(destination = "monitor.small.damage", containerFactory = "queueListenerFactory")
    void smallDamage()
	{
        log.warn("There is a small damage.");
    }

    @JmsListener(destination = "monitor.critical.damage", containerFactory = "topicListenerFactory")
    void criticalDamage()
	{
        log.error("Attention! There is a critical damage!!");
    }
	
	@JmsListener(destination = "pitstop.pullover.needed", containerFactory = "queueListenerFactory")
    void onMessage()
	{
        log.warn("Pit-stop pullover is needed!");
    }
}
