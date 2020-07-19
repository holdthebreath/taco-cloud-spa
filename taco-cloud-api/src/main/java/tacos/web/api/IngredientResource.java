package tacos.web.api;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import tacos.Ingredient;

/**
 * @ClassName IngredientResource
 * @Description Ingredient类的展示模型类
 * @Author hwd
 * @Date 2020/7/19 10:32 PM
 * @Version 1.0
 */
public class IngredientResource extends RepresentationModel<IngredientResource> {
    @Getter
    private String name;
    @Getter
    private Ingredient.Type type;

    public IngredientResource(Ingredient ingredient) {
        this.name = ingredient.getName();
        this.type = ingredient.getType();
    }
}
