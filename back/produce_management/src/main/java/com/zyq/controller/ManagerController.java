package com.zyq.controller;

import com.zyq.controller.utils.R;
import com.zyq.dao.manager.ManagerDao;
import com.zyq.dao.manager.ManagerMapper;
import com.zyq.domain.manager.Manager;
import com.zyq.service.ManagerService;
import com.zyq.service.impl.ManagerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className ManagerController
 * @date 2023-02-06 17:17
 */
@RestController
@RequestMapping("manager")
public class ManagerController {
    @Autowired
    private ManagerDao managerDao;

    @Autowired
    private ManagerServiceImpl managerService;

    @GetMapping("/getByName")
    @ResponseBody
    public R test1(String name, String password, int iden){
//        Manager managerByName = managerDao.getManagerByName(name, password, iden);
        Manager managerByName = managerService.getManagerByName(name, password, iden);

        if(managerByName != null){
            managerByName.setPassword("");
            return new R(true, managerByName);
        }
        else
            return new R(false);
    }

}
