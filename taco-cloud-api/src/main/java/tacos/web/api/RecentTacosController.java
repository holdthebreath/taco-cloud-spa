package tacos.web.api;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import tacos.Taco;
import tacos.data.TacoRepository;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @ClassName RecentTacosController
 * @Description 最近taco控制器类
 * @Author hwd
 * @Date 2020/8/5 10:46 PM
 * @Version 1.0
 */
//其映射应该采用与为Spring Data REST端点配置的基本路径相同的基本路径
//@RepositoryRestController注解的控制器中的所有映射的路径都将以spring.data.rest.base-path的值为前缀(/api)
@RepositoryRestController
public class RecentTacosController {
    private TacoRepository tacoRepository;

    public RecentTacosController(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    //@RepositoryRestController不确保从处理程序方法返回的值被自动写入响应体,需要使用 @ResponseBody 对方法进行注解,或者返回一个包装响应数据的ResponseEntity。
    @GetMapping(path = "/tacos/recent", produces = "application/hal+json")
    public ResponseEntity<CollectionModel<TacoResource>> recentTacos() {
        PageRequest pageRequest = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        List<Taco> tacoList = tacoRepository.findAll(pageRequest).getContent();
        CollectionModel<TacoResource> tacoResources = new TacoResourceAssembler(DesignTacoController.class, TacoResource.class).toCollectionModel(tacoList);
        tacoResources.add(linkTo(methodOn(RecentTacosController.class).recentTacos()).withRel("recents"));
        return new ResponseEntity<>(tacoResources, HttpStatus.OK);
    }
}
