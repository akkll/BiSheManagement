package com.zyq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zyq.dao.manager.ManagerMapper;
import com.zyq.domain.manager.Manager;
import com.zyq.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className ManagerServiceImpl
 * @date 2023-02-25 16:59
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public Manager getManagerByName(String name, String password, int iden) {
        LambdaQueryWrapper<Manager> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(Manager::getName, name)
                .eq(Manager::getPassword, password);
        System.out.println(iden);
        if(iden == 1){
            queryWrapper.eq(Manager::getIden, 0);
        } else {
            queryWrapper.in(Manager::getIden, new String[]{"1", "2", "3", "4"});
        }
        List<Manager> managers = managerMapper.selectList(queryWrapper);
        if (managers.size()==1)
            return managers.get(0);
        else
            return null;
    }
}
