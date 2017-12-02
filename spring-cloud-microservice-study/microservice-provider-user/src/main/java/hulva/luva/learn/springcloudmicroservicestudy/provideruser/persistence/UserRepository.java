package hulva.luva.learn.springcloudmicroservicestudy.provideruser.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hulva.luva.learn.springcloudmicroservicestudy.provideruser.model.User;

/**
 * @author Hulva Luva.H from ECBD
 * @date 2017年12月2日
 * @description 
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
