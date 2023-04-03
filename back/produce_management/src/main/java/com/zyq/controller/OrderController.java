package com.zyq.controller;

import com.zyq.controller.utils.R;
import com.zyq.domain.process.Order;
import com.zyq.domain.process.Product;
import com.zyq.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className OrderController
 * @date 2023-02-23 16:49
 */
@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderServicel;

    @GetMapping("")
    public R test1(){
        List<Order> orders = orderServicel.getOrders();
        return new R(true, orders);
    }

    @GetMapping("/arrange")
    public R test2(Boolean type){
        List<Order> list = orderServicel.getOrdersByArrange(type);
        return new R(true, list);
    }

    @GetMapping("/products")
    public R test3(){
        List<Product> list = orderServicel.getProduct();
        return new R(true, list);
    }

    @PostMapping("/updateArrange")
    public R test4(@RequestBody List<Integer> list){
        int i = orderServicel.updateArrange(list);
        return new R(true, i);
    }

    @PostMapping("/Unit123GiveMidComplete")
    public R test5(@RequestBody Map<String, Integer> map){
        Integer assignid = map.get("assignid");
        int i = orderServicel.Unit123GiveMidComplete(assignid);
        return new R(true, i);
    }
    @PostMapping("/Unit4GiveProComplete")
    public R test6(@RequestBody Map<String, Integer> map){
        Integer assignid = map.get("assignid");
        int i = orderServicel.Unit4GiveProComplete(assignid);
        return new R(true, i);
    }
}
