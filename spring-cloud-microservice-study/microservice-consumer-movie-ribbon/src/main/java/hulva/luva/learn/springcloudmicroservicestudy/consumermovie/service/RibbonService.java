package hulva.luva.learn.springcloudmicroservicestudy.consumermovie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import hulva.luva.learn.springcloudmicroservicestudy.consumermovie.model.User;

/**
 * @author Hulva Luva.H
 *
 */
@Service
public class RibbonService {
	@Autowired
	private RestTemplate restTemplate;

	public ResponseEntity<User> findById(Long id) {
		// http://服务提供者的serviceId/url
		return this.restTemplate.getForEntity("http://microservice-provider-user/" + id, User.class);
	}
}
