package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.User;

/**
 * @ClassName UserRepository
 * @Description 用户数据库接口类
 * @Author hwd
 * @Date 2020/7/15 2:22 PM
 * @Version 1.0
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
