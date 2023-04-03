package com.zyq.service;

import com.zyq.domain.process.Assign;
import com.zyq.domain.process.UnitProcess;
import com.zyq.domain.vo.AssignOrderProductUnit;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className UnitProcessService
 * @date 2023-02-27 23:04
 */
//车间内的派工单
@Service
public interface UnitProcessService {
    List<UnitProcess> getUnitProcess();
    UnitProcess geUnitProcessById(int id);
    int insertUnitProcess(UnitProcess unitProcess);
    List<UnitProcess> getUnitProcessByUnit(int unit);
    int addEmployeeUnitProcess(int employeeid, int id);
    int completeUnitProcess(int id, int assignid);
    List<AssignOrderProductUnit> getAssignOrderProductUnit123(int unit);
}
