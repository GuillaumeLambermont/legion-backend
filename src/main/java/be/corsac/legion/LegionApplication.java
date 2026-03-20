package be.corsac.legion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class LegionApplication {

	static void main(String[] args) {
		SpringApplication.run(LegionApplication.class, args);
	}

}
