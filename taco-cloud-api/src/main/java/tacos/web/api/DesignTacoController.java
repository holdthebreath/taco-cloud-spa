package tacos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tacos.Taco;
import tacos.data.TacoRepository;

import java.util.Optional;

/**
 * @ClassName DesignTacoController
 * @Description 设计taco控制器类
 * @Author hwd
 * @Date 2020/7/8 1:30 PM
 * @Version 1.0
 */
//构造型注解
//告诉Spring,控制器中的所有处理方法的返回值都要直接写入响应体中,而不是将值放到模型中传递给一个视图以便渲染
// = @Controller + @ResponseBody
@RestController
//指定控制器类处理等请求路径(处理路径以"/design"开头等请求)
//produces指定请求输出:指明控制器中所有处理器方法只会处理Accept头信息包含"application/json"的请求,同样会限制API只产生JSON结果(可以允许其他控制器处理相同路径请求)
@RequestMapping(path = "/design", produces = "application/json")
//允许跨域请求(来自任何域)
@CrossOrigin(origins = "*")
public class DesignTacoController {
    private TacoRepository tacoRepository;

    @Autowired
    EntityLinks entityLinks;

    public DesignTacoController(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @GetMapping("/recent")
    public Iterable<Taco> recentTacos() {
        //第一页(序号为0)的12条结果,并按照创建时间降序排序
        PageRequest pageRequest = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        return tacoRepository.findAll(pageRequest).getContent();
    }

    //针对/design/{id}的GET请求,{id}是占位符,请求中的实际值会传递给id参数(通过@PathVariable注解匹配)
    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> optionalTaco = tacoRepository.findById(id);
        // =
//        if (optionalTaco.isPresent()) {
//            return new ResponseEntity<>(optionalTaco.get(), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return optionalTaco.map(taco -> new ResponseEntity<>(taco, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    //consumes指定请求输入:该方法只会处理Content-type = application/json想匹配的请求
    @PostMapping(consumes = "application/json")
    //响应成功返回HTTP 200(OK),但这里需要更精确描述创建资源成功,因此设置返回201(CREATED,告诉客户端不仅成功了而且创建了一个资源
    @ResponseStatus(HttpStatus.CREATED)
    //该请求应该被转换为一个Taco对象并绑定到参数taco上
    //如果没有SpringMVC会认为我们希望将请求参数绑定到taco上,该注解确保请求体中的JSON会被绑定到taco上
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepository.save(taco);
    }
}
