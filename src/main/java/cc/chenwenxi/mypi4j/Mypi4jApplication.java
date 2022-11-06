package cc.chenwenxi.mypi4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Mypi4jApplication {

	public static void main(String[] args) {
		SpringApplication.run(Mypi4jApplication.class, args);

		System.out.println("启动oled");
		OledKit oled = new OledKit(0, 0);
		oled.start();
		oled.text("ok");
	}

}
