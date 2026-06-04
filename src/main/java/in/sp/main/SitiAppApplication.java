package in.sp.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableScheduling
@SpringBootApplication
public class SitiAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SitiAppApplication.class, args);
	}

}
