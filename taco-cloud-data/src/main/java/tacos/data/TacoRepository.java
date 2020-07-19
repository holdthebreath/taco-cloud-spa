package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.Taco;

/**
 * @ClassName TacoRepository
 * @Description Taco类数据库接口类
 * @Author hwd
 * @Date 2020/7/11 11:21 AM
 * @Version 1.0
 */
public interface TacoRepository extends CrudRepository<Taco, Long> {

}
