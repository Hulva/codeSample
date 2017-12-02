package hulva.luva.learn.springcloudmicroservicestudy.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableTurbine
public class MicroserviceTurbineApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceTurbineApplication.class, args);
	}
}
