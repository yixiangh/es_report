package com.seari.es_report.controller;

import com.alibaba.fastjson.JSONObject;
import com.seari.es_report.entity.ResultBean;
import com.seari.es_report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 报表
 */
@RestController
@RequestMapping(value = "report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @RequestMapping(value = "getUserInfo")
    public String getUserInfo()
    {
        List<Map<String,Object>> userList = reportService.getUserList();
        return JSONObject.toJSONString(userList);
    }
}
