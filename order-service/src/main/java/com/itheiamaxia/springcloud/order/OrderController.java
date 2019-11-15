package com.itheiamaxia.springcloud.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getOrder")
    public String getOrder(){
        return "获取到了订单";
    }

    //此处调用用户接口，获取用户数据
    @GetMapping("/getUserOrder")
    public String getUser() throws URISyntaxException {
        String forObject =
                restTemplate.getForObject(new URI("http://user-service/user/getUser"), String.class);
        return forObject + " 订单处理";
    }

}
