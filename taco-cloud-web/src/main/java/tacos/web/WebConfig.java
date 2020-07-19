package tacos.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName WebMvcConfigurer
 * @Description 视图控制器(不需要填充模型或处理输入)
 * @Author hwd
 * @Date 2020/7/8 9:14 PM
 * @Version 1.0
 */
//只将请求转发到视图到控制器
@Configuration
public class WebConfig implements WebMvcConfigurer {
    //接收一个ViewControllerRegistry对象,使用它注册一个或多个视图控制器
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //addViewController():针对传递进去的路径执行GET请求,方法返回一个ViewControllerRegistration对象
        //ViewControllerRegistration调用setViewName()方法,转发到"home"视图上
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login");
    }
}
