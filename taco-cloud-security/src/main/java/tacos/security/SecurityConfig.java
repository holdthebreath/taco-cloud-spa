package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @ClassName SecurityConfig
 * @Description Spring Security基础配置类
 * @Author hwd
 * @Date 2020/7/14 10:13 PM
 * @Version 1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
            //声明顺序是重要的,首先声明的安全规则优先于较低级别声明的安全规则
            //对/design和/orders的请求授予ROLE_USER权限
            .antMatchers("/design", "/orders")
            //access(String):如果SpEL表达式的值为true，则允许访问
            // = .hasRole("ROLE_USER")
            .access("hasRole('ROLE_USER')")
            //所有的请求都被允许给所有的用户
            .antMatchers("/", "/**")
            // = .access("permitAll");
            .permitAll()
            //and()方法表示已经完成授权配置,并准备应用一些额外的HTTP配置
            .and()
            //开始配置自定义登陆表单
            .formLogin()
            //配置自定义登陆页面路径
            .loginPage("/login")
            //登陆失败url
//            .failureUrl("/login?error=true")
            //配置默认登陆成功页面
            .defaultSuccessUrl("/design")
            //登出
            .and()
            .logout()
            .logoutSuccessUrl("/")
            //禁用h2数据库控制台页面的csrf防护
            .and()
            .csrf()
            .ignoringAntMatchers("/h2-console/**")
            //配置Spring Security允许iframe frame加载同源的资源(h2控制台页面需要)
            .and()
            .headers().frameOptions()
            .sameOrigin();
    }
}