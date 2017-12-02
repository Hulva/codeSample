package hulva.luva.learn.springcloudmicroservicestudy.consumermovie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import hulva.luva.learn.springcloudmicroservicestudy.consumermovie.feignclients.UserFeignClient;
import hulva.luva.learn.springcloudmicroservicestudy.consumermovie.model.User;

/**
 * @author Hulva Luva.H
 *
 */
@RestController
public class UserFeignController {
	@Autowired
	private UserFeignClient userFeignClient;

	@GetMapping("feign/{id}")
	public User findByIdFeign(@PathVariable Long id) {
		return this.userFeignClient.findByIdFeign(id);
	}
}
