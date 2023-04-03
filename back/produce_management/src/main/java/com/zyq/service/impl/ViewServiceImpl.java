package com.zyq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zyq.dao.process.AssignMapper;
import com.zyq.dao.process.UnitProcessMapper;
import com.zyq.domain.process.Assign;
import com.zyq.domain.process.UnitProcess;
import com.zyq.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className ViewServiceImpl
 * @date 2023-03-08 22:39
 */
@Service
@Repository
public class ViewServiceImpl implements ViewService {
    @Autowired
    private AssignMapper assignMapper;
    @Autowired
    private UnitProcessMapper unitProcessMapper;

    @Override
    public List<Map<String, Object>> getBeiliao(int unit) {
        QueryWrapper<Assign> wrapper = new QueryWrapper<>();
        wrapper
                .select("count(*) as num, assign_matecomplete as complete")
                .groupBy("assign_matecomplete")
                .eq("assign_procomplete", 0)
                .eq("assign_type",unit);
        List<Map<String, Object>> maps1 = assignMapper.selectMaps(wrapper);
        if (maps1.size()==1){
            String complete = maps1.get(0).get("complete").toString();
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("num", 0);
            if(complete=="true"){
                hashMap.put("complete", false);
            } else {
                hashMap.put("complete", true);
            }
            maps1.add(hashMap);
        }else if (maps1.size()==0){
            HashMap<String, Object> hashMap = new HashMap<>();
            HashMap<String, Object> hashMap2 = new HashMap<>();
            hashMap.put("num",0);
            hashMap.put("complete", true);
            hashMap2.put("num",0);
            hashMap2.put("complete", false);
            maps1.add(hashMap);
            maps1.add(hashMap2);
        }
        System.out.println(maps1);
        return maps1;
    }

    @Override
    public ArrayList<HashMap<String, Object>> getProcess(int unit) {
        LambdaQueryWrapper<UnitProcess> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UnitProcess::getUnit, unit).eq(UnitProcess::getStart,1).eq(UnitProcess::getComplete,0);
        Long aLong = unitProcessMapper.selectCount(queryWrapper);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("num",aLong);
        hashMap.put("complete",true);
        LambdaQueryWrapper<Assign> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(Assign::getType, unit).eq(Assign::getProcomplete,1).eq(Assign::getComplete,0);
        Long aLong1 = assignMapper.selectCount(queryWrapper1);
        HashMap<String, Object> hashMap1 = new HashMap<>();
        hashMap1.put("num",aLong1);
        hashMap1.put("complete",false);
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        list.add(hashMap);
        list.add(hashMap1);
        return list;
    }

    @Override
    public List<Map<String, Object>> getMonthAssign(int unit) {
        QueryWrapper<Assign> wrapper = new QueryWrapper<>();
        wrapper
                .select("count(*) as num, DATE_FORMAT(assign_date,'%Y-%m') as date")
                .groupBy("DATE_FORMAT(assign_date,'%Y-%m')")
                .eq("assign_type",unit);
        List<Map<String, Object>> maps1 = assignMapper.selectMaps(wrapper);
        return maps1;
    }
}
