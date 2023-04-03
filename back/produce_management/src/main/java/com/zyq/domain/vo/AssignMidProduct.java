package com.zyq.domain.vo;

import lombok.Data;
import lombok.ToString;

import java.sql.Date;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className AssignMidProduct
 * @date 2023-03-01 16:02
 */
@Data
@ToString
public class AssignMidProduct {
    private Integer id;
    private Integer materialID;
    private Integer prev;
    private Date accdate;
    private Date date;
    private Boolean complete;
    private float number;
    private Boolean start;
    private Integer orderid;
    private Integer midid;
    private String name;
    private String scale;
    private Integer materialid;
    private Float matenum;
    private Integer unit;
    private Boolean matecomplete;
    private Boolean procomplete;
}
