package com.test.controller;

import com.test.entity.Payment;
import com.test.entity.R;
import com.test.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ：Breeze
 * @date ：Created in 2020/3/12 23:34
 * @description：
 */

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/savePayment")
    public R savePayment(@RequestBody Payment payment){
        int result = paymentService.savePayment(payment);
        //System.out.println("插入成功...");
        if(result>0) {
            return new R(200,"插入成功, port"+ serverPort,result);
        }else{
            return new R(444,"插入失败",result);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public R getPayment(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        System.out.println("插入成功...");
        if(payment != null) {
            return new R(200,"查询成功port"+ serverPort,payment);
        }else{
            return new R(444,"查询失败",null);
        }
    }

    @GetMapping(value = "/payment/info")
    public Object serverInfo(){
        List<String> services = discoveryClient.getServices();
        for (String server: services) {
            log.info("***** server: " +server);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance element : instances){
            log.info("***** server: " +element.getServiceId()+ "\t"+ element.getHost() +"\t" + element.getUri());
        }

        return discoveryClient;
    }

    @GetMapping("/payment/port")
    public R getPort(){
        return new R(200,"ok",serverPort);
    }

}
