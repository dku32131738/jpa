package com.leejb.jpa.workspace.service;

import static org.slf4j.LoggerFactory.getLogger;

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
	
	public String getTypeInOracle(String name) {
		String url = featureRepository.findOneByName(name).orElse(null).getJdbc().getUrl();
		return null;
	}
}
