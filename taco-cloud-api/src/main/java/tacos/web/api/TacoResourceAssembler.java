package tacos.web.api;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import tacos.Taco;

/**
 * @ClassName tacos.web.api.TacoResourceAssembler
 * @Description Taco资源模型装配器类
 * @Author hwd
 * @Date 2020/7/19 9:59 PM
 * @Version 1.0
 */
public class TacoResourceAssembler extends RepresentationModelAssemblerSupport<Taco, TacoResource> {
    public TacoResourceAssembler(Class<?> controllerClass, Class<TacoResource> resourceType) {
        //通过父类构造方法设置控制器类属性(确定所有URL基础路径)
        //通过父类构造方法设置返回的对象类型
        super(controllerClass, resourceType);
    }

    @Override
    public TacoResource instantiateModel(Taco taco) {
        return new TacoResource(taco);
    }

    @Override
    public TacoResource toModel(Taco entity) {
        //通过实体对象(调用instantiateModel方法)创建TacoResource对象并根据实体对象id设置一个self链接
        return createModelWithId(entity.getId(), entity);
    }
}
