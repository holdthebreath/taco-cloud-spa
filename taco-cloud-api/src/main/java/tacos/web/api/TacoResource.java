package tacos.web.api;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import tacos.Taco;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName TacoResource
 * @Description Taco对象扩展工具类(通过继承RepresentationModel继承一个Link对象的列表和管理链接列表的方法)
 * @Author hwd
 * @Date 2020/7/19 9:53 PM
 * @Version 1.0
 */
//指定HATEOAS如何命名结果JSON中的字段名
@Relation(value = "taco", collectionRelation = "tacos")
public class TacoResource extends RepresentationModel<TacoResource> {
    private static final IngredientResourceAssembler INGREDIENT_RESOURCE_ASSEMBLER = new IngredientResourceAssembler(IngredientController.class, IngredientResource.class);

    @Getter
    private final String name;
    @Getter
    private final Date createdAt;
    @Getter
    private final List<IngredientResource> ingredients;

    public TacoResource(Taco taco) {
        this.name = taco.getName();
        this.createdAt = taco.getCreatedAt();
        this.ingredients = new ArrayList<>(INGREDIENT_RESOURCE_ASSEMBLER.toCollectionModel(taco.getIngredients()).getContent());
    }
}
