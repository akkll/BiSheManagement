package com.zyq.controller;

import com.zyq.controller.utils.R;
import com.zyq.service.impl.ViewServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className ViewController
 * @date 2023-03-09 16:21
 */
@RestController
@RequestMapping("view")
public class ViewController {

    @Autowired
    ViewServiceImpl viewService;

    @GetMapping("/getBeiliao")
    public R getBeiliao(int unit){
        List<Map<String, Object>> beiliao = viewService.getBeiliao(unit);
        return new R(true, beiliao);
    }

    @GetMapping("/getProcess")
    public R getProcess(int unit){
        ArrayList<HashMap<String, Object>> process = viewService.getProcess(unit);
        return new R(true, process);
    }

}
