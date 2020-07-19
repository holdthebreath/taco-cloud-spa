package tacos.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @ClassName DiscountController
 * @Description 折扣控制器
 * @Author hwd
 * @Date 2020/7/16 9:03 PM
 * @Version 1.0
 */
@Controller
@RequestMapping("/discounts")
public class DiscountController {
    private DiscountProps discountProps;

    public DiscountController(DiscountProps discountProps) {
        this.discountProps = discountProps;
    }

    @GetMapping
    public String displayDiscountCodes(Model model) {
        Map<String, Integer> codes = discountProps.getCodes();
        model.addAttribute("codes", codes);
        return "discountList";
    }
}
