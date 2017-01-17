package link.revie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class RevieApplication {

    @RequestMapping("/")
    public String index() {
        return "Spring Boot!";
    }
	public static void main(String[] args) {
		SpringApplication.run(RevieApplication.class, args);
	}
	
}
