package com.reto.citas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reto.citas.entities.Affiliates;
@Repository
public interface AffiliatesRepository extends JpaRepository <Affiliates,Long> {

}
