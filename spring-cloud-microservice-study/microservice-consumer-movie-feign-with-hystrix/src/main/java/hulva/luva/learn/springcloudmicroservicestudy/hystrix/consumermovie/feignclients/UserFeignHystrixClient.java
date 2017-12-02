package hulva.luva.learn.springcloudmicroservicestudy.hystrix.consumermovie.feignclients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.hystrix.FallbackFactory;
import hulva.luva.learn.springcloudmicroservicestudy.hystrix.consumermovie.model.User;

/**
 * @author Hulva Luva.H
 *
 */
// @FeignClient(name = "microservice-provider-user")
@FeignClient(name = "microservice-provider-user", fallback = UserFeignHystrixClient.HystrixClientFallbackFactory.class)
public interface UserFeignHystrixClient {
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User findByIdFeign(@PathVariable("id") Long id);

	@Component
	public static class HystrixClientFallbackFactory implements FallbackFactory<UserFeignHystrixClient> {

		private static final Logger LOGGER = LoggerFactory.getLogger(HystrixClientFallbackFactory.class);

		@Override
		public UserFeignHystrixClient create(Throwable cause) {
			HystrixClientFallbackFactory.LOGGER.info("异常发生，进入fallback方法，cause: {}", cause);
			return new UserFeignHystrixClient() {

				/**
				 * hystrix fallback方法
				 * 
				 * @param id
				 *            id
				 * @return 默认的用户
				 */
				@Override
				public User findByIdFeign(@PathVariable("id") Long id) {
					HystrixClientFallbackFactory.LOGGER.info("异常发生，进入fallback方法，接收的参数：id = {}", id);
					User user = new User();
					user.setId(-1L);
					user.setUsername("default username");
					user.setAge(0);
					return user;
				}
			};
		}

	}
}
