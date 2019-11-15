package com.itheiamaxia.springcloud.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getUser")
    public String getUser(){
        return "获取到了用户";
    }

    //此接口测试是否能从user-service使用ribbon调用自身
    //当user-service启动时，向eureka注册的只是应用名（在配置文件中指定）+IP地址+端口
    @GetMapping("/getSelf")
    public String getSelf() throws URISyntaxException {
        String forObject =
                restTemplate.getForObject(new URI("http://user-service/user/getUser"), String.class);
        return "调用自身/user/getUser" + forObject;
    }

    @GetMapping("/getOrder")
    public String getOrder() throws URISyntaxException {
        String forObject =
                restTemplate.getForObject(new URI("http://order-service/order/getOrder"), String.class);
        return forObject;
    }
}
