package com.leejb.jpa.workspace.service;

import static org.slf4j.LoggerFactory.getLogger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.leejb.jpa.workspace.entity.Jdbc;
import com.leejb.jpa.workspace.repository.JdbcRepository;
import com.leejb.jpa.workspace.service.JdbcService;

@Service
public class JdbcService {

	@Autowired
	JdbcRepository jdbcRepository;
	
	private static final Logger LOG = getLogger(JdbcService.class);
	
	public Jdbc save(String name, String dbType, String host, String dbName, int port, String username, String password) {
		Jdbc jdbc =new Jdbc(name,username,password);
		jdbc.setUrl(createUrl(dbType,host,dbName,port));
		jdbc.setDbType(dbType);
		//중복 확인
		if(!jdbcRepository.findOneByName(name).isEmpty()) {
			return null;
		}else {
			LOG.info("test");
			jdbcRepository.save(jdbc);
		}
		return jdbcRepository.findById(jdbc.getId()).orElse(null);
	}
	
	public Jdbc find(String name) {
		Optional<Jdbc> temp = jdbcRepository.findOneByName(name);
		return temp.orElse(null);
	}
	
	public Jdbc delete(String name) {
		Jdbc deletedJdbc = jdbcRepository.findOneByName(name).get();
		jdbcRepository.delete(deletedJdbc);
		return deletedJdbc;
	}
	
	public String test(String name) {
		Jdbc jdbc = jdbcRepository.findOneByName(name).orElse(null);
		return test(jdbc);
	}
	
	public String test(Jdbc jdbc){
		
		String result;
		try {
			switch(jdbc.getDbType()) {
			case "oracle": Class.forName("oracle.jdbc.driver.OracleDriver");
			break;
			default: throw new ClassNotFoundException();
			}
			Connection conn = DriverManager.getConnection(jdbc.getUrl(),jdbc.getUser(),jdbc.getPassword());
			conn.close();
			result = "success";
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			LOG.error(se.getMessage());
			result = "fail";
		} catch (NullPointerException ne) {
			LOG.error(ne.getMessage());
			result = "fail";
		} catch (ClassNotFoundException ce) {
			// TODO Auto-generated catch block
			LOG.error(ce.getMessage());
			ce.printStackTrace();
			result = "fail";
		}
		return result;
	}
	
	public Page<Jdbc> page(int offset, int size) {
		PageRequest pageRequest = PageRequest.of(offset, size);
		return jdbcRepository.findAll(pageRequest);
	}
	
	private String createUrl(String dbType, String host, String dbName, int port) {
		String resultUrl;
		switch (dbType) {
		case "oracle": resultUrl = "jdbc:oracle:thin:@//" + host + ":" + port + "/" + dbName;
		break;
		default: resultUrl = null;
		break;
		}
		return resultUrl;
	}
	
}
