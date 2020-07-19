package tacos.web;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @ClassName OrderProps
 * @Description 订单页面数量实体类
 * @Author hwd
 * @Date 2020/7/16 4:21 PM
 * @Version 1.0
 */
@Component
@Data
//通过属性文件里taco.orders.pageSize设置值
@ConfigurationProperties(prefix = "taco.orders")
@Validated
public class OrderProps {
    @Min(value = 5, message = "must be between 5 and 25!")
    @Max(value = 25, message = "must be between 5 and 25!")
    private int pageSize = 20;
}
