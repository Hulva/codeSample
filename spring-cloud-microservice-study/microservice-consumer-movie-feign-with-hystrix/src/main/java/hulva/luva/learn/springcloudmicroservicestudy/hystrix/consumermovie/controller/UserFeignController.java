package hulva.luva.learn.springcloudmicroservicestudy.hystrix.consumermovie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import hulva.luva.learn.springcloudmicroservicestudy.hystrix.consumermovie.feignclients.UserFeignHystrixClient;
import hulva.luva.learn.springcloudmicroservicestudy.hystrix.consumermovie.model.User;

/**
 * @author Hulva Luva.H
 *
 */
@RestController
public class UserFeignController {
	@Autowired
	private UserFeignHystrixClient userFeignClient;

	@GetMapping("feign-with-hystrix/{id}")
	public User findByIdFeign(@PathVariable Long id) {
		return this.userFeignClient.findByIdFeign(id);
	}
}
