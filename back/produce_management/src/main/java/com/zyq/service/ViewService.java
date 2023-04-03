package com.zyq.service;

import com.zyq.dao.process.AssignMapper;
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
 * @className ViewService
 * @date 2023-03-08 22:37
 */
@Service
@Repository
public interface ViewService {

    List<Map<String, Object>>  getBeiliao(int unit);
    ArrayList<HashMap<String, Object>> getProcess(int unit);
    List<Map<String, Object>>  getMonthAssign(int unit);
}
