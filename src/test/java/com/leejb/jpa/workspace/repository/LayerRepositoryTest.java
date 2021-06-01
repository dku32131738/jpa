package com.leejb.jpa.workspace.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.leejb.jpa.workspace.entity.Feature;
import com.leejb.jpa.workspace.entity.Layer;

@SpringBootTest
@Transactional
@Rollback(false)
public class LayerRepositoryTest {
	
	@PersistenceContext
	EntityManager em;
	
	@Test
	public void save() {
		Feature featureA = new Feature("featureA",0,null,null,null,null);
		Feature featureB = new Feature("featureB",0,null,null,null,null);
		em.persist(featureA);
		em.persist(featureB);
		
		Layer layer1 = new Layer("layer1",featureA,"test");
		Layer layer2 = new Layer("layer2",featureA,"test");
		Layer layer3 = new Layer("layer3",featureB,"test");
		Layer layer4 = new Layer("layer4",featureB,"test");
		
		em.persist(layer1);
		em.persist(layer2);
		em.persist(layer3);
		em.persist(layer4);
		
		em.flush();
		em.clear();
		
		List<Layer> layers = em.createQuery("select m from Layer m",Layer.class).getResultList();
		
		for (Layer layer : layers) {
			 System.out.println("layer=" + layer);
			 System.out.println("-> layer.feature=" + layer.getFeature());
		}
	}

}
