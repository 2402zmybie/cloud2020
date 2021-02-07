package com.hr.springcloud.controller;

import com.hr.commonutils.Payment;
import com.hr.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
//订单的客户端, 调用支付的服务
public class OrderController {

//    public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    //RestTemplate提供了多种便捷访问远程Http服务的方法
    //是一种简单便捷的访问restful服务模板类, 是Spring提供的用于访问Rest服务的客户端模板工具类
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public R create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment,R.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public R getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, R.class);
    }


}
