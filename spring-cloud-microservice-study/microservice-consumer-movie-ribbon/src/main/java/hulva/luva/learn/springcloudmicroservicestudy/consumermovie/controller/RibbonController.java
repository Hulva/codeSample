package hulva.luva.learn.springcloudmicroservicestudy.consumermovie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import hulva.luva.learn.springcloudmicroservicestudy.consumermovie.model.User;
import hulva.luva.learn.springcloudmicroservicestudy.consumermovie.service.RibbonService;

/**
 * @author Hulva Luva.H
 *
 */
@RestController
public class RibbonController {
	@Autowired
	private RibbonService ribbonService;

	@GetMapping("/ribbon/{id}")
	public User findById(@PathVariable Long id) {
		return this.ribbonService.findById(id).getBody();
	}
}
