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
 * @className MaterialgetMaterial
 * @date 2023-03-23 15:09
 */
@Data
@ToString
public class MaterialgetMaterial {
    private Integer id;
    private Integer materialid;
    private Date date;
    private Integer unit;
    private Integer assignid;
    private Float number;
    private String materialname;
    private String materialtype;
    private String materialscale;
}
