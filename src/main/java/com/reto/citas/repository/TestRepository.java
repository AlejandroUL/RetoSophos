package com.reto.citas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reto.citas.entities.Tests;
@Repository
public interface TestRepository extends JpaRepository <Tests,Long> {
	

}
