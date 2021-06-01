package com.leejb.jpa.workspace.service;

import static org.slf4j.LoggerFactory.getLogger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leejb.jpa.workspace.entity.Feature;
import com.leejb.jpa.workspace.entity.Jdbc;
import com.leejb.jpa.workspace.repository.FeatureRepository;

@Service
public class FeatureService {

	@Autowired
	FeatureRepository featureRepository;
	
	private static final Logger LOG = getLogger(FeatureService.class);
	
	public Feature save(String name, int srs, String type, Jdbc jdbc, String query, String bboxQuery) {
		Feature feature = new Feature(name,srs,type,jdbc,query,bboxQuery);
		featureRepository.save(feature);
		return feature;
	}
	
	public String getTypeInOracle(String name) throws Exception {
		Feature feature = featureRepository.findOneByName(name).orElse(null);
		Jdbc jdbc = feature.getJdbc();
		switch(jdbc.getDbType()) {
		case "oracle": 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(jdbc.getUrl(),jdbc.getUser(),jdbc.getPassword());
			PreparedStatement preparedStatement = conn.prepareStatement(feature.getSqlStatement());
			preparedStatement.executeQuery(feature.getSqlStatement());
			conn.close();
			break;
		}
		return null;
	}
}
