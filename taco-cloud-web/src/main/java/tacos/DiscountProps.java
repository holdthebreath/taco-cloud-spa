package tacos.web;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName DiscountProps
 * @Description 折扣属性类
 * @Author hwd
 * @Date 2020/7/16 8:56 PM
 * @Version 1.0
 */
@Component
@ConfigurationProperties(prefix = "taco.discount")
@Data
public class DiscountProps {
    private Map<String, Integer> codes = new HashMap<>();
}
