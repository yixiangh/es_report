package com.seari.es_report;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.seari.es_report.mapper")
public class EsReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsReportApplication.class, args);
	}

}
