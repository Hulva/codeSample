package hulva.luva.learn.springcloudmicroservicestudy.consumermovie.feignclients;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hulva.luva.learn.springcloudmicroservicestudy.consumermovie.model.User;

/**
 * @author Hulva Luva.H
 *
 */
@FeignClient(name = "microservice-provider-user")
public interface UserFeignClient {
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User findByIdFeign(@PathVariable("id") Long id);
}