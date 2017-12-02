package hulva.luva.learn.springcloudmicroservicestudy.hystrixconsumermovie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import hulva.luva.learn.springcloudmicroservicestudy.hystrixconsumermovie.model.User;
import hulva.luva.learn.springcloudmicroservicestudy.hystrixconsumermovie.service.RibbonHystrixService;

/**
 * @author Hulva Luva.H
 *
 */
@RestController
public class RibbonController {
	@Autowired
	private RibbonHystrixService ribbonService;

	@GetMapping("/ribbon-with-hystrix/{id}")
	public User findById(@PathVariable Long id) {
		return this.ribbonService.findById(id);
	}
}
