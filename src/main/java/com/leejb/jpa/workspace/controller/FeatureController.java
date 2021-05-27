package com.leejb.jpa.workspace.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leejb.jpa.workspace.entity.Feature;
import com.leejb.jpa.workspace.entity.Jdbc;
import com.leejb.jpa.workspace.service.FeatureService;
import com.leejb.jpa.workspace.service.JdbcService;

@RestController
@RequestMapping("workspace/feature")
public class FeatureController {

	@Autowired
	FeatureService featureService;
	
	@Autowired
	JdbcService jdbcService;
	
	@PostMapping("save")
	public Feature save(@RequestParam("name") String name,@RequestParam("srs") int srs,
			@RequestParam("type") String type,@RequestParam("jdbc") String jdbc,@RequestParam("query") String query,
			@RequestParam("bboxQuery") String bboxQuery) {
		return featureService.save(name, srs, type, jdbcService.find(jdbc), query, bboxQuery);
	}
	
}
