package com.zyq.domain.vo;

import lombok.Data;
import lombok.ToString;

import java.sql.Date;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className KaoqinEmployee
 * @date 2023-03-06 20:52
 */
@Data
@ToString
public class KaoqinEmployee {
    private Integer id;
    private Date shangban;
    private Double longitude;
    private Double latitude;
    private String name;
}
