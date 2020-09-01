package tacos.web.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tacos.Ingredient;
import tacos.data.IngredientRepository;

/**
 * @ClassName IngredientController
 * @Description Ingredient控制器类
 * @Author hwd
 * @Date 2020/7/19 10:40 PM
 * @Version 1.0
 */
@RestController
@RequestMapping(path = "/ingredientsx", produces = "application/json")
@CrossOrigin("*")
public class IngredientController {
    private IngredientRepository ingredientRepository;

    public IngredientController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping
    public Iterable<Ingredient> allIngredients() {
        return ingredientRepository.findAll();
    }
}
