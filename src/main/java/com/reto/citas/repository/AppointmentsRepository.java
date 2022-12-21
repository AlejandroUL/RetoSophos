package com.reto.citas.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reto.citas.entities.Affiliates;
import com.reto.citas.entities.Appointment;

@Repository
public interface AppointmentsRepository extends JpaRepository <Appointment, Long> {

	List<Appointment> findByDateAppointmentOrderByAffiliateId(LocalDate dateAppointment);

	List<Appointment> findByAffiliateIdOrderByDateAppointment(Affiliates affiliatesId);

}


