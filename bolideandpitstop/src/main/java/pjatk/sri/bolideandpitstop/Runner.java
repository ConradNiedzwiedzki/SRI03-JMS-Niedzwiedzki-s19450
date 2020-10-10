package pjatk.sri.bolideandpitstop;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TopicConnection;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class Runner
{
    private final BolideInformationSender bolideInformationSender;
    private final AtomicLong atomicLongGenerator;

    public Runner(BolideInformationSender bolideInformationSender)
	{
        this.bolideInformationSender = bolideInformationSender;
        atomicLongGenerator = new AtomicLong();
        run();
    }

    private void run()
	{
        Runnable runner = () ->
		{
            while (true)
			{
                BolideInformationDTO bolideInformationDto = new BolideInformationDTO(LocalDateTime.now(), atomicLongGenerator.incrementAndGet(), atomicLongGenerator.incrementAndGet());
                bolideInformationSender.sendInfo(bolideInformationDto);
				
                try
				{
                    Thread.sleep(1500);
                }
				catch (InterruptedException exception)
				{
                    exception.printStackTrace();
                }
            }
        };
		
        Thread thread = new Thread(runner);
        thread.start();
    }
}
