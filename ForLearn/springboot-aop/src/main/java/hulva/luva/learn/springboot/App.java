package hulva.luva.learn.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan("hulva.luva.learn.springboot.**")
public class App {
  public static void main(String[] args) throws Exception {
    SpringApplication.run(App.class, args);
  }
}
