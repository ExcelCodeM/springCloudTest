package com.test.controller;

import com.test.entity.R;
import com.test.service.HystrixServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Breeze
 * @date ：Created in 2020/3/22 19:03
 * @description：
 */

@RestController
public class HystrixController {

    @Autowired
    private HystrixServiceImpl hystrixService;

    @GetMapping("/hystrxOK/{id}")
    public R hystrixOK(@PathVariable("id") Long id){
        return new R(200,"成功",hystrixService.hystrixOK(id));
    }

    @GetMapping("/hystrxTimeout/{id}")
    public R hystrixTimeout(@PathVariable("id") Long id){
        return new R(200,"成功",hystrixService.hystrixTimeout(id));
    }

}
