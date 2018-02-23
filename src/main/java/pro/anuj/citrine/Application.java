package pro.anuj.citrine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("pro.anuj.citrine")
@SpringBootApplication
public class Application {
    public static final String DEFAULT_VALUE = "--DEFAULT--";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
