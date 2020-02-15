package com.seari.es_report.service.impl;

import com.seari.es_report.mapper.ReportMapper;
import com.seari.es_report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public List<Map<String, Object>> getUserList() {
        return reportMapper.getUserList();
    }
}
