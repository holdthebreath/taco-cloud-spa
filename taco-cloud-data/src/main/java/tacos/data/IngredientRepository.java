package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.Ingredient;

/**
 * @ClassName IngredientRepository
 * @Description JDBC repository
 * @Author hwd
 * @Date 2020/7/8 9:40 PM
 * @Version 1.0
 */
//CrudRepository定义了很多Crud操作的方法,第一个参数是要持久化的实体类,第二个参数是实体id属性类型
//Spring Data JPA会在运行期间自动生成实现类
public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
