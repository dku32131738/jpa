package com.leejb.jpa.workspace.service;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.leejb.jpa.workspace.entity.Jdbc;

@SpringBootTest
@Rollback(false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JdbcServiceTest {
	
	@Autowired
	JdbcService jdbcService;
	
	@Test
	@Order(1)
	public void jdbcSave() {
		Jdbc jdbc = jdbcService.save("oracle", "oracle", "192.168.0.159", "xe", 1521, "system", "ictway001");
		Assertions.assertEquals(jdbc.getName(), "oracle");
		Assertions.assertEquals(jdbc.getDbType(), "oracle");
		Assertions.assertEquals(jdbc.getUser(), "system");
		Assertions.assertEquals(jdbc.getPassword(), "ictway001");
	}

	@Test
	@Order(2)
	public void jdbcFind() {
		Jdbc jdbc = jdbcService.find("oracle");
		Assertions.assertEquals(jdbc.getName(), "oracle");
		Assertions.assertEquals(jdbc.getDbType(), "oracle");
		Assertions.assertEquals(jdbc.getUser(), "system");
		Assertions.assertEquals(jdbc.getPassword(), "ictway001");
	}
	
	@Test
	@Order(3)
	public void jdbcTest() {
		String result = jdbcService.test("oracle");
		Assertions.assertEquals(result, "success");
	}
	
	@Test
	@Order(4)
	public void jdbcDelete() {
		Jdbc jdbc = jdbcService.delete("oracle");
		Assertions.assertEquals(jdbc.getName(), "oracle");
		Assertions.assertEquals(jdbc.getDbType(), "oracle");
		Assertions.assertEquals(jdbc.getUser(), "system");
		Assertions.assertEquals(jdbc.getPassword(), "ictway001");
	}
}
