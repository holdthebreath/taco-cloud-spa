package tacos.web.api;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import tacos.Taco;

/**
 * @ClassName TacoResourcesProcessor
 * @Description Taco列表添加自定义超链接
 * @Author hwd
 * @Date 2020/9/4 2:17 PM
 * @Version 1.0
 */
@Component
public class TacoResourcesProcessor implements RepresentationModelProcessor<PagedModel<EntityModel<Taco>>> {
    private final EntityLinks entityLinks;

    public TacoResourcesProcessor(EntityLinks entityLinks) {
        this.entityLinks = entityLinks;
    }

    @Override
    public PagedModel<EntityModel<Taco>> process(PagedModel<EntityModel<Taco>> model) {
        model.add(entityLinks.linkFor(Taco.class).slash("recent").withRel("recents"));
        return model;
    }
}
