package com.reto.citas.servers;

import java.time.LocalDate;
import java.util.List;

import com.reto.citas.entities.Affiliates;
import com.reto.citas.entities.Appointment;

public interface AppointmentsServices {

	List<Appointment> consultarAppointmets();
	Appointment guardarAppointment(Appointment appointment);
	void eliminarAppointment(Long id);
	Appointment actualizatAppointment(Appointment appointment);
	Appointment getByIdAppointment(Long id);
	List<Appointment> getByDate (LocalDate dateAppointment);
	List<Appointment> getByIdaffiliate (Affiliates affiliatesId);
	
}
