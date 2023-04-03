package com.zyq.domain.vo;

import lombok.Data;
import lombok.ToString;

import java.sql.Date;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className AssignOrderProductUnit
 * @date 2023-03-12 20:39
 */
@Data
@ToString
public class AssignOrderProductUnit {
    private Integer assignid;//
    private Integer orderid;//
    private Integer unit;//
    private Boolean assignStart;//
    private Boolean assignMateComplete;
    private Boolean assignProComplete;
    private Boolean assignComplete;
    private Date date;
    //以下是order和product
    private Integer productId;//
    private String productName;//
    private String productScale;//
    private Integer number;//
    private Boolean orderArrange;//
    private Boolean orderComplete;//
    //以下是midmaterial或product
    private Integer midmaterialId;
    private String midmaterialName;
    private String midmaterialScale;
    //以下是unitprocess
    private Integer liushui;
    private Boolean unitProcessStart;
    private Boolean unitProcessComplete;
}
