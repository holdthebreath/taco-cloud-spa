package tacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//引导类

//组合了3个其他注解的组合注解
//1.@SpringBootConfiguration:将该类声明为配置类,该注解其实是@Configuration注解的特殊形式
//2.@EnableAutoFConfiguration:启用Spring Boot的自动配置
//3.@ComponentScan:启用组件扫描
@SpringBootApplication
public class TacoCloudApplication {

    public static void main(String[] args) {
//传递参数为1:配置类 2.命令行参数
        SpringApplication.run(TacoCloudApplication.class, args);
    }
}
