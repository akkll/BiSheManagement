package com.zyq.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

import java.sql.Date;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className UnitProcessEmployeeMaterial
 * @date 2023-03-03 15:11
 */
@Data
@ToString
public class UnitProcessEmployeeMaterial {
    private Integer id;
    private Integer unit;
    private Integer employeeid;
    private Integer assignid;
    private Date accdate;
    private Date date;
    private Boolean complete;
    private Boolean start;
    private String name;
    private String employeename;
}
