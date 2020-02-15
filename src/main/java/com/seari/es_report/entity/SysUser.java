package com.seari.es_report.entity;


import lombok.Data;

import java.util.Date;

@Data
public class SysUser {

    private String userId;
    private String userName;
    private String userRealName;
    private String userSex;
    private String createBy;
    private Date createTime;
}
