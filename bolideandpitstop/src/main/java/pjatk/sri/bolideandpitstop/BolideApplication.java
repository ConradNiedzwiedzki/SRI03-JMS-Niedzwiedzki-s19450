package pjatk.sri.bolideandpitstop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("pjatk.sri.bolideandpitstop")
public class BolideApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(BolideApplication.class, args);
	}
}
