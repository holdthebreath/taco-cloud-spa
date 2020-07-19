package tacos;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @ClassName Taco
 * @Description Taco领域对象
 * @Author hwd
 * @Date 2020/7/8 1:53 PM
 * @Version 1.0
 */
@Data
@Entity
public class Taco {
    @Id
    //因为依赖于数据库自动生成id值,所以还使用@GeneratedValue注解id属性,指定自动策略
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date createdAt;
    @NotBlank
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;
    //多对多关系
    //@ManyToMany注解ingredient属性,一个Taco可以有很多Ingredient,一个Ingredient可以是很多Taco的一部分
    @ManyToMany(targetEntity = Ingredient.class)
    @NotEmpty
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;
    //声明在实体持久化保存之前触发的回调方法
    @PrePersist
    void createdAt(){
        this.createdAt = new Date();
    }
}
