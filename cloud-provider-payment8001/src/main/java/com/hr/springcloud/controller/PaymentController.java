package com.hr.springcloud.controller;

import com.hr.commonutils.Payment;
import com.hr.commonutils.R;
import com.hr.springcloud.service.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Api("支付管理")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;


    @ApiOperation("生成支付")
    @PostMapping("/payment/create")
    private R create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        return result > 0? R.ok().data("serverPort", serverPort) : R.error();
    }

    @ApiOperation("获取支付")
    @GetMapping("/payment/get/{id}")
    private R getPaymentByid(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        return payment !=null ? R.ok().data("payment", payment).data("serverPort", serverPort): R.error();
    }

    @GetMapping("/payment/discovery")
    public Object discovery() {
        //所有的微服务列表
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("element:" + element);
        }
        //一个微服务下面所有全部的具体实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return this.discoveryClient;
    }
}
