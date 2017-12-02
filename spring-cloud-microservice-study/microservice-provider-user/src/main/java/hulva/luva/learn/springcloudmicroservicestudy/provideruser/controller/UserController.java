package hulva.luva.learn.springcloudmicroservicestudy.provideruser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import hulva.luva.learn.springcloudmicroservicestudy.provideruser.model.User;
import hulva.luva.learn.springcloudmicroservicestudy.provideruser.persistence.UserRepository;

/**
 * @author Hulva Luva.H
 *
 */
@RestController
public class UserController {

	@Autowired
	private Registration discoveryClient;
	@Autowired
	private UserRepository userRepository;

	/**
	 * 注：@GetMapping("/{id}")是spring 4.3的新注解等价于：
	 * 
	 * @RequestMapping(value = "/id", method = RequestMethod.GET)
	 *                       类似的注解还有@PostMapping等等
	 * @param id
	 * @return user信息
	 */
	@GetMapping("/{id}")
	public User findById(@PathVariable Long id) {
		User findOne = this.userRepository.findOne(id);
		return findOne;
	}

	/**
	 * 本地服务实例信息
	 * 
	 * @return
	 */
	@GetMapping("/instance-info")
	public ServiceInstance showInfo() {
		return this.discoveryClient;
	}
}
