package tacos.web.api;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import tacos.Ingredient;

/**
 * @ClassName IngredientResourceAssembler
 * @Description Ingredient展示模型类的装配器类
 * @Author hwd
 * @Date 2020/7/19 10:34 PM
 * @Version 1.0
 */
public class IngredientResourceAssembler extends RepresentationModelAssemblerSupport<Ingredient, IngredientResource> {
    public IngredientResourceAssembler(Class<?> controllerClass, Class<IngredientResource> resourceClass) {
        super(controllerClass, resourceClass);
    }

    @Override
    public IngredientResource instantiateModel(Ingredient ingredient) {
        return new IngredientResource(ingredient);
    }

    @Override
    public IngredientResource toModel(Ingredient entity) {
        return createModelWithId(entity.getId(), entity);
    }
}
