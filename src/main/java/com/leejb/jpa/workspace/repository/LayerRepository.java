package com.leejb.jpa.workspace.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.leejb.jpa.workspace.entity.Layer;

@Repository
public class LayerRepository {
	
	@PersistenceContext
	private EntityManager em;

	public Layer save(Layer layer) {
		em.persist(layer);
		return layer;
	}
	
	public Layer find(Long id) {
		return em.find(Layer.class, id);
	}
}
