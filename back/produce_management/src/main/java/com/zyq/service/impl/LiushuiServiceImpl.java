package com.zyq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.zyq.dao.liushui.GongxuMapper;
import com.zyq.dao.liushui.LiushuiMapper;
import com.zyq.domain.liushui.Gongxu;
import com.zyq.domain.liushui.Liushui;
import com.zyq.service.LiushuiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className LiushuiServiceImpl
 * @date 2023-03-11 17:37
 */
@Service
@Repository
public class LiushuiServiceImpl implements LiushuiService {
    @Autowired
    private GongxuMapper gongxuMapper;
    @Autowired
    private LiushuiMapper liushuiMapper;
    @Override
    public List<Gongxu> getGongxuByUnit(int unit) {
        LambdaQueryWrapper<Gongxu> queryWrapper = new LambdaQueryWrapper<>();
        if(unit!=-1){
            queryWrapper.eq(Gongxu::getUnit,unit);
        }
        List<Gongxu> gongxus = gongxuMapper.selectList(queryWrapper);
        return gongxus;
    }

    @Override
    public Liushui getLiushui(int unit, int id, int gongxu) {
        LambdaQueryWrapper<Liushui> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Liushui::getUnit, unit).eq(Liushui::getLid, id).eq(Liushui::getGongxu,gongxu);
        Liushui liushui = liushuiMapper.selectOne(queryWrapper);
        return liushui;
    }

    @Override
    public Boolean updateOrInsertLiushui(int unit, int id, int gongxu, int employeeid) {
        Liushui liushui = getLiushui(unit, id, gongxu);
        LambdaUpdateWrapper<Liushui> updateWrapper = new LambdaUpdateWrapper<>();
        if(liushui==null){
            Liushui liushui1 = new Liushui();
            liushui1.setLid(id);
            liushui1.setEmployeeid(employeeid);
            liushui1.setGongxu(gongxu);
            liushui1.setUnit(unit);
            liushuiMapper.insert(liushui1);
            return false;
        }else{
            updateWrapper.eq(Liushui::getLid,id).eq(Liushui::getGongxu,gongxu).eq(Liushui::getUnit,unit).set(Liushui::getEmployeeid,employeeid);
            liushuiMapper.update(null, updateWrapper);
            return true;
        }
    }
}
