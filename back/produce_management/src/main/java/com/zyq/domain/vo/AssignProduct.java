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
 * @className AssignProduct
 * @date 2023-02-28 15:45
 */
@Data
@ToString
public class AssignProduct {
    private Integer id;
    private Integer materialID;
    private Integer prev;
    private Date accdate;
    private Date date;
    private Boolean complete;
    private float number;
    private Boolean start;
    private Integer orderid;
    private Integer productid;
    private String name;
    private String scale;
    private String mid;
    private Boolean matecomplete;
    private Boolean procomplete;
}
