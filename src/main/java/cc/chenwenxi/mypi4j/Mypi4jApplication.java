package cc.chenwenxi.mypi4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class Mypi4jApplication {

	public static void main(String[] args) {
		SpringApplication.run(Mypi4jApplication.class, args);
	}

}
