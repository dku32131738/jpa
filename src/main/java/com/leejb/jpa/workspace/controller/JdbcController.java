package com.leejb.jpa.workspace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.leejb.jpa.workspace.entity.Jdbc;
import com.leejb.jpa.workspace.service.JdbcService;

@RestController
@RequestMapping("workspace/jdbc")
public class JdbcController {
	
	@Autowired
	JdbcService jdbcService;
	
	@PostMapping("/save")
	@ResponseBody
	public Jdbc save(@RequestParam("name") String name, 
			@RequestParam("dbType") String dbType, @RequestParam("host") String host, @RequestParam("dbName") String dbName, @RequestParam("port") int port,
			@RequestParam("username") String username, @RequestParam("password") String password) {
		return jdbcService.save(name, dbType, host, dbName, port, username, password);
	}
	
	@GetMapping("/find")
	@ResponseBody
	public Jdbc find(@RequestParam("name") String name) {
		return jdbcService.find(name);
	}
	
	@GetMapping("/delete")
	@ResponseBody
	public Jdbc delete(@RequestParam("name") String name) {
		return jdbcService.delete(name);
	}

	@GetMapping("/test")
	@ResponseBody
	public String test(@RequestParam("name") String name) {
		return jdbcService.test(name);
	}
	
	@GetMapping("/page")
	@ResponseBody
	public Page<Jdbc> page(@RequestParam("offset") int offset, @RequestParam("size") int size) {
		return jdbcService.page(offset, size);
	}

}

