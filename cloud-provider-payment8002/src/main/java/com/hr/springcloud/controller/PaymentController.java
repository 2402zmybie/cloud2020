package com.hr.springcloud.controller;

import com.hr.commonutils.Payment;
import com.hr.commonutils.R;
import com.hr.springcloud.service.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Api("支付管理")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

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
}
