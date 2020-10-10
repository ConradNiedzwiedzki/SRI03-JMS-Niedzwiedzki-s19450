package pjatk.sri.bolideandpitstop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;

@RestController
public class PitStopEndpoint
{
    private final PitStopService service;

    public PitStopEndpoint(PitStopService service)
	{
        this.service = service;
    }

    @RequestMapping("/pitstop/{number}")
    public ResponseEntity pitStopPullover(@PathVariable("number") Integer number)
	{
        boolean approve = false;
        try
		{
            approve = service.askForPitStop(number);
        }
		catch (JMSException e)
		{
            e.printStackTrace();
        }

        if (approve)
		{
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
