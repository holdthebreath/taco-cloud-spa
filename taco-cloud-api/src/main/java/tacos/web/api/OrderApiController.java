package tacos.web.api;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tacos.Order;
import tacos.data.OrderRepository;

/**
 * @ClassName OrderApiController
 * @Description 订单API控制器类
 * @Author hwd
 * @Date 2020/7/19 4:50 PM
 * @Version 1.0
 */
@RestController
@RequestMapping(path = "/orders", produces = "application/json")
@CrossOrigin("*")
public class OrderApiController {
    private OrderRepository orderRepository;

    public OrderApiController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    //处理PUT请求,语义其实是在GET的对立面,GET用来从服务端往客户端传输数据,PUT请求则是从客户端往服务端发送数据
    //PUT真正目的是执行大规模的替换操作,而不是更新操作(HTTP PATCH请求的目的是对资源数据打补丁或局部更新)
    //PUT意味着"将数据放到这个URL上",本质上就是替换已有对数据
    @PutMapping("/{order}")
    public Order putOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    //处理PATCH请求
    @PatchMapping(path = "/{orderId}", consumes = "application/json")
    public Order patchOrder(@PathVariable("orderId") Long orderId, @RequestBody Order patchOrder) {
        Order order = orderRepository.findById(orderId).get();
        if (patchOrder.getDeliveryName() != null) {
            order.setDeliveryName(patchOrder.getDeliveryName());
        }
        if (patchOrder.getDeliveryStreet() != null) {
            order.setDeliveryStreet(patchOrder.getDeliveryStreet());
        }
        if (patchOrder.getDeliveryCity() != null) {
            order.setDeliveryCity(patchOrder.getDeliveryCity());
        }
        if (patchOrder.getDeliveryState() != null) {
            order.setDeliveryState(patchOrder.getDeliveryState());
        }
        if (patchOrder.getDeliveryZip() != null) {
            order.setDeliveryZip(patchOrder.getDeliveryZip());
        }
        if (patchOrder.getCcNumber() != null) {
            order.setCcNumber(patchOrder.getCcNumber());
        }
        if (patchOrder.getCcExpiration() != null) {
            order.setCcExpiration(patchOrder.getCcExpiration());
        }
        if (patchOrder.getCcCVV() != null) {
            order.setCcCVV(patchOrder.getCcCVV());
        }
        return orderRepository.save(order);
    }

    @DeleteMapping("/{orderId}")
    //确保响应的HTTP状态码是204(NO CONTENT)
    //对于已经不存在的资源,没有必要返回任何资源数据给客户端,因此DELETE请求通常没有响应体,以HTTP状态码的形式让客户端知道不要期望得到任何内容
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            orderRepository.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {
            //为什么捕获异常但什么都不做:
            //作者思路:删除一个不存在的资源的结果和删除之前存在的资源是一样的,所有删除之前资源是否存在不重要
        }
    }
}
