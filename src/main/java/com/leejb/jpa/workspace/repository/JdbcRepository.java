package com.leejb.jpa.workspace.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.leejb.jpa.workspace.entity.Jdbc;

public interface JdbcRepository extends JpaRepository<Jdbc, Integer>{
	Optional<Jdbc> findOneByName(@Param("name") String name);
	Page<Jdbc> findAll(Pageable pageable);
}