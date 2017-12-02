package hulva.luva.learn.springcloudmicroservicestudy.hystrix.consumermovie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableCircuitBreaker
public class MicroserviceConsumerMovieFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceConsumerMovieFeignApplication.class, args);
	}
}
