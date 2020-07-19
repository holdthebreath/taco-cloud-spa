package tacos.web.api;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import tacos.Ingredient;
import tacos.Taco;

import java.util.Date;
import java.util.List;

/**
 * @ClassName TacoResource
 * @Description Taco对象扩展工具类(通过继承RepresentationModel继承一个Link对象的列表和管理链接列表的方法)
 * @Author hwd
 * @Date 2020/7/19 9:53 PM
 * @Version 1.0
 */
public class TacoResource extends RepresentationModel<TacoResource> {
    @Getter
    private final String name;
    @Getter
    private final Date createdAt;
    @Getter
    private final List<Ingredient> ingredients;

    public TacoResource(Taco taco) {
        this.name = taco.getName();
        this.createdAt = taco.getCreatedAt();
        this.ingredients = taco.getIngredients();
    }
}
