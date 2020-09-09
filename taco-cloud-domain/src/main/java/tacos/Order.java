package tacos;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName Order
 * @Description 订单领域类
 * @Author hwd
 * @Date 2020/7/8 3:14 PM
 * @Version 1.0
 */
@Data
@Entity
//指定实体应该持久化的表名字(Taco_Order)
//可以在任何实体类上使用这个注解
//如果不使用,则JPA将默认将实体持久化到一个名为Order的表中
@Table(name = "Taco_Order")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date placedAt;
    @NotBlank(message = "Name is required")
    private String deliveryName;
    @NotBlank(message = "Street is required")
    private String deliveryStreet;
    @NotBlank(message = "City is required")
    private String deliveryCity;
    @NotBlank(message = "State is required")
    private String deliveryState;
    @NotBlank(message = "Zip code is required")
    private String deliveryZip;
    //合法的信用卡号,能通过Luhn算法检查
//    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;
//    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY")
    private String ccExpiration;
//    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;
    @ManyToMany(targetEntity = Taco.class)
    private List<Taco> tacos = new ArrayList<>();
    @ManyToOne
    private User user;

    public void addDesign(Taco design) {
        this.tacos.add(design);
    }

    @PrePersist
    void placeAt() {
        this.placedAt = new Date();
    }
}
