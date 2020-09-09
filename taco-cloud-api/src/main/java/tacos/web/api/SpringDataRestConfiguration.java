package tacos.web.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import tacos.Ingredient;

/**
 * @ClassName SpringDataRestController
 * @Description Spring data初始化类
 * @Author hwd
 * @Date 2020/8/15 10:52 PM
 * @Version 1.0
 */
@Configuration
public class SpringDataRestConfiguration implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Ingredient.class);
    }

}