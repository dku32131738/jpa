package com.leejb.jpa.workspace.repository;

import static org.slf4j.LoggerFactory.getLogger;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.leejb.jpa.workspace.entity.Feature;
import com.leejb.jpa.workspace.entity.Jdbc;

@SpringBootTest
@Rollback(false)
public class FeatureRepositoryTest {
	
	private static final Logger LOG = getLogger(FeatureRepositoryTest.class);
	
	private Jdbc findJdbc;

	@Autowired
	FeatureRepository featureRepository;
	
	@Autowired
	JdbcRepository jdbcRepository;
	
	@Test
	public void saveJdbc() {
		Jdbc jdbc = new Jdbc("oracle","system","ictway");
		jdbcRepository.save(jdbc);
		Assertions.assertEquals(jdbcRepository.count(),1);
		Assertions.assertEquals(jdbcRepository.findAll().size(),1);
	}
	
	@Test
	public void findJdbc() {
		findJdbc = jdbcRepository.findOneByName("oracle").orElse(null);
		Assertions.assertNotNull(findJdbc);
		Assertions.assertEquals(findJdbc.getId(),1);
		Assertions.assertEquals(findJdbc.getName(),"oracle");
		Assertions.assertEquals(findJdbc.getUser(),"system");
		Assertions.assertEquals(findJdbc.getPassword(),"ictway");
	}
	
	@Test
	public void saveFeature() {
		Feature feature = new Feature("polygon",5186,"polygon",jdbcRepository.findAll().get(0),
				"    SELECT GID_0, NAME_0,sdo_util.to_wkbgeometry(geometry) AS geom\r\n"
				+ "      FROM korea_4326\r\n"
				+ "     WHERE SDO_RELATE(GEOMETRY,SDO_GEOMETRY(?, 4326),'mask=anyinteract querytype=WINDOW') = 'TRUE'",
				"SELECT 'POLYGON ((122.6953125 32.2313896627376, 132.36328125 32.2313896627376, 132.36328125 43.02071359427862, 122.6953125 43.02071359427862, 122.6953125 32.2313896627376))' as bbox from dual");
		featureRepository.save(feature);
		Assertions.assertEquals(featureRepository.count(),1);
		Assertions.assertEquals(featureRepository.findAll().size(),1);
	}
	
	@Test
	public void findFeature() {
		Feature findFeature = featureRepository.findOneByName("polygon").orElse(null);
		Assertions.assertNotNull(findFeature);
		Assertions.assertEquals(findFeature.getName(),"polygon");
		Assertions.assertEquals(findFeature.getFeatureTypeName(),"polygon");
		Assertions.assertEquals(findFeature.getJdbc().getId(),jdbcRepository.findAll().get(0).getId());
	}
	
}
