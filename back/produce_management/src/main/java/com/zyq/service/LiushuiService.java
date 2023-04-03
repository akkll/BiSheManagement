package com.zyq.service;

import com.zyq.domain.liushui.Gongxu;
import com.zyq.domain.liushui.Liushui;

import java.util.List;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className LiushuiService
 * @date 2023-03-11 17:37
 */
public interface LiushuiService {

    List<Gongxu> getGongxuByUnit(int unit);
    Liushui getLiushui(int unit,int id, int gongxu);//根据车间号、流水线号、工序号查找对应的记录
    Boolean updateOrInsertLiushui(int unit,int id, int gongxu, int employeeid);
}
