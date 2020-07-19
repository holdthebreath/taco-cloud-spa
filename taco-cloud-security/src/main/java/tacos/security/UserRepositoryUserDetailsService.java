package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import tacos.User;
import tacos.data.UserRepository;

/**
 * @ClassName UserRepositoryUserDetailsService
 * @Description 用户详细信息服务类
 * @Author hwd
 * @Date 2020/7/15 2:30 PM
 * @Version 1.0
 */
//构造型注解,将该类标记为包含在Spring组件扫描中
@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    private UserRepositoryUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws AuthenticationException {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return user;
        }
        //登陆失败信息
        //AbstractUserDetailsAuthenticationProvider的authenticate()有hideUserNotFoundExceptions属性,默认值为true
        //意味直接抛出UsernameNotFoundException它也会转为BadCredentialsException,导致UsernameNotFoundException的信息会没有
        //所以直接抛出BadCredentialsException异常
        //最终但异常信息存在session属性中,key值为SPRING_SECURITY_LAST_EXCEPTION
        throw new BadCredentialsException("Unable to login. Check your username and password.");
    }
}
