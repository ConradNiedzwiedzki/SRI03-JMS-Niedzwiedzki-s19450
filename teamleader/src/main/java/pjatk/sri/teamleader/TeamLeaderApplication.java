package pjatk.sri.teamleader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class TeamLeaderApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(TeamLeaderApplication.class, args);
	}
}
