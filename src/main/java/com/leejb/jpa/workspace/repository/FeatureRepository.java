package com.leejb.jpa.workspace.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.leejb.jpa.workspace.entity.Feature;

public interface FeatureRepository extends JpaRepository<Feature, Integer>{
	Optional<Feature> findOneByName(@Param("name") String name);
	Page<Feature> findAll(Pageable pageable);
}
