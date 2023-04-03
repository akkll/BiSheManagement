package com.zyq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.zyq.dao.process.AssignMapper;
import com.zyq.dao.process.UnitProcessMapper;
import com.zyq.dao.vo.AssignOrderProductUnitMapper;
import com.zyq.domain.process.Assign;
import com.zyq.domain.process.UnitProcess;
import com.zyq.domain.vo.AssignOrderProductUnit;
import com.zyq.service.UnitProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className UnitProcessServiceImpl
 * @date 2023-02-27 23:04
 */
@Service
@Repository
public class UnitProcessServiceImpl implements UnitProcessService {

    @Autowired
    private UnitProcessMapper unitProcessMapper;
    @Autowired
    private AssignMapper assignMapper;

    @Autowired
    private AssignOrderProductUnitMapper assignOrderProductUnitMapper;

    @Override
    public List<UnitProcess> getUnitProcess() {
        List<UnitProcess> unitProcesses = unitProcessMapper.selectList(null);
        return unitProcesses;
    }

    @Override
    public UnitProcess geUnitProcessById(int id) {
        LambdaQueryWrapper<UnitProcess> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UnitProcess::getId, id);
        UnitProcess unitProcess = unitProcessMapper.selectOne(queryWrapper);
        return unitProcess;
    }

    @Override
    public int insertUnitProcess(UnitProcess unitProcess) {
        int insert = unitProcessMapper.insert(unitProcess);
        return insert;
    }

    @Override
    public List<UnitProcess> getUnitProcessByUnit(int unit) {
        LambdaQueryWrapper<UnitProcess> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UnitProcess::getUnit, unit);
        List<UnitProcess> unitProcesses = unitProcessMapper.selectList(queryWrapper);
        return unitProcesses;
    }

    @Override
    public int addEmployeeUnitProcess(int employeeid, int id) {
        LambdaUpdateWrapper<UnitProcess> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(UnitProcess::getEmployeeid, employeeid)
                .set(UnitProcess::getStart, true)
                .eq(UnitProcess::getId, id);
        int update = unitProcessMapper.update(null, updateWrapper);
        return update;
    }

    @Override
    public int completeUnitProcess(int id, int assignid) {
        LambdaUpdateWrapper<UnitProcess> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .eq(UnitProcess::getId, id)
                .set(UnitProcess::getComplete, true);
        int update = unitProcessMapper.update(null, updateWrapper);
        LambdaUpdateWrapper<Assign> wrapper = new LambdaUpdateWrapper<>();
        wrapper
                .eq(Assign::getId, assignid)
                .set(Assign::getProcomplete, true);
        int update1 = assignMapper.update(null, wrapper);
        return update;
    }

    @Override
    public List<AssignOrderProductUnit> getAssignOrderProductUnit123(int assignid) {
        List<AssignOrderProductUnit> list = assignOrderProductUnitMapper.getAssignOrderProductUnit123(assignid);
        return list;
    }


}
