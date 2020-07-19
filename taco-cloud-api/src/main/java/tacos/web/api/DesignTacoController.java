package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import tacos.Ingredient;
import tacos.Order;
import tacos.Taco;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName DesignTacoController
 * @Description 设计taco控制器类
 * @Author hwd
 * @Date 2020/7/8 1:30 PM
 * @Version 1.0
 */
//lombok提供注解,在运行时在这个类中自动生成一个SLF4J(Simple Logging Facade for Java) Logger
// = private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DesignTacoController.class);
@Slf4j
@Controller
//指定控制器类处理等请求路径(处理路径以"/design"开头等请求)
@RequestMapping("/design")
//model的名称"order"属性会被存入到HttpSession
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;

    private TacoRepository tacoRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository){
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    //@ModelAttribute注释方法:
    //将数据添加到model对象中 = model.addAttribute("order", order);
    //被注释到方法会在该controller执行每个@ReqiestMapping注解方法执行前被执行
    //返回具体类到方法,若不指定属性名称,则model中到属性名称由隐式指定,为返回类型到小写名称
    //@ModelAttribute注解的返回值会覆盖@RequestMapping注解方法中的同名对象
    @ModelAttribute(name = "order")
    public Order order(){
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    //处理GET请求(Spring 4.3引入) = @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredients::add);
        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
        return "design";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }

    //处理POST请求
    @PostMapping
    //@Valid表示对Taco对象进行校验,校验时机在绑定完表单数据,调用processDesign()之前.如果存在校验错误,则错误的细节将会捕捉到一个Errors对象中并传递给processDesign()
    //@ModelAttribute注释方法参数:参数的值来自model相同名称属性
    public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            return "design";
        }
        Taco saved = tacoRepository.save(design);
        order.addDesign(saved);
        //redirect:前缀:说明这是重定向视图,重定向到相对路径/orders/current
        return "redirect:/orders/current";
    }
}
