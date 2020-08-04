package tacos;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import tacos.data.IngredientRepository;
import tacos.data.OrderRepository;
import tacos.data.TacoRepository;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @ClassName HomeControllerTest
 * @Description 主页面控制器测试类
 * @Author hwd
 * @Date 2020/8/4 8:30 PM
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@WebMvcTest()
@Ignore("Unsure how to test with @DataJpaTest")
public class HomeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IngredientRepository ingredientRepository;

    @MockBean
    private TacoRepository designRepository;

    @MockBean
    private OrderRepository orderRepository;
    @Test
    public void testHomePage() throws Exception{
        //对"/"路径发起HTTP GET请求
        mockMvc.perform(get("/")).
            //响应状态为HTTP200(OK)
                andExpect(status().isOk()).
            //视图对逻辑名称应该是"home"
                andExpect(view().name("home")).
            //渲染后对视图应该包含文本"I’ll be a Laker for the rest of my life"
                andExpect(content().string(containsString("I’ll be a Laker for the rest of my life")));
//        期望全部满足后表明测试通过
    }
}
