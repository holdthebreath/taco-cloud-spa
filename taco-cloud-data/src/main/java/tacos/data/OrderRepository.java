package tacos.data;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tacos.Order;
import tacos.User;

import java.util.List;

/**
 * @ClassName OrderRepository
 * @Description 订单数据库接口类
 * @Author hwd
 * @Date 2020/7/11 11:15 PM
 * @Version 1.0
 */
public interface OrderRepository extends CrudRepository<Order, Long> {
    //当创建repository实现时,Spring Data会检查repository接口的所有方法,解析方法名称,并基于被持久化对象试图推测方法的目的
    //本质上,Spring Data 定义了一种小型的领域特定语言(DSL)
    //持久化的细节都是通过repository方法的签名来描述
    //repository方法由:动词 + 可选主题(subject) + 关键词by + 断言
    //简单的查询直接用方法名称约定
    List<Order> findByDeliveryZip(String deliveryZip);

    //用@Query显式地指定调用方法时要执行的查询
    //nativeQuery = true开启原生sql语句查询
    @Query(value = "select '*' from Order where deliveryCity = 'LosAngeles'")
    List<Order> readOrdersDeliveredInLosAngeles();

    List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
