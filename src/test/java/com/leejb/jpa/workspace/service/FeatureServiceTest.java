package com.leejb.jpa.workspace.service;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.leejb.jpa.workspace.entity.Feature;

@SpringBootTest
@Transactional
@Rollback(false)
public class FeatureServiceTest {
	
	@Autowired
	FeatureService featureService;

	@Test
	public void featureSave() {
		
		Feature feature = featureService.save("polygon", 5186, "polygon", null, 
				"    SELECT GID_0, NAME_0,sdo_util.to_wkbgeometry(geometry) AS geom\r\n"
						+ "      FROM korea_4326\r\n"
						+ "     WHERE SDO_RELATE(GEOMETRY,SDO_GEOMETRY(?, 4326),'mask=anyinteract querytype=WINDOW') = 'TRUE'", 
				"SELECT 'POLYGON ((122.6953125 32.2313896627376, 132.36328125 32.2313896627376, 132.36328125 43.02071359427862, 122.6953125 43.02071359427862, 122.6953125 32.2313896627376))' as bbox from dual");
		Assertions.assertEquals(feature.getName(),"polygon");
		Assertions.assertEquals(feature.getBboxStatement(),"polygon");
	}
	
}
