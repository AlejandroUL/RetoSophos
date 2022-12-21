package com.reto.citas.servers.impl;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto.citas.entities.Affiliates;
import com.reto.citas.entities.Appointment;
import com.reto.citas.repository.AppointmentsRepository;
import com.reto.citas.servers.AffiliatesServices;
import com.reto.citas.servers.AppointmentsServices;
import com.reto.citas.servers.TestServices;

@Service
public class AppointmentsServicesimpl implements AppointmentsServices {

	
	@Autowired
	private AppointmentsRepository appointmentsRepository;
	
	@Autowired
	private AffiliatesServices affiliatesServicesimpl;
	
	@Autowired
	private TestServices testServiceImpl;
	
	@Override
	public List<Appointment> consultarAppointmets() {
		List<Appointment> listaAppointments = this.appointmentsRepository.findAll();
		return listaAppointments;
	}

	@Override
	public Appointment guardarAppointment(Appointment appointment) {
		
		appointment.setAffiliateId(affiliatesServicesimpl.getByIdAffiliates(appointment.getAffiliateId().getAffiliateId()));
		appointment.setTestId(testServiceImpl.getById(appointment.getTestId().getTestId()));
		return appointmentsRepository.save(appointment);
	}
	
	@Override
	public Appointment actualizatAppointment(Appointment appointment) {
		
		appointment.setTestId(testServiceImpl.getById(appointment.getTestId().getTestId()));
		appointment.setAffiliateId(affiliatesServicesimpl.getByIdAffiliates(appointment.getAffiliateId().getAffiliateId()));
		
		return appointmentsRepository.save(appointment);
	}

	@Override
	public void eliminarAppointment(Long id) {
		this.appointmentsRepository.deleteById(id);
		
	}

	

	@Override
	public Appointment getByIdAppointment(Long id) {
		Appointment appointment = this.appointmentsRepository.findById(id).get();
		return appointment;
	}
	@Override
	public List<Appointment> getByDate (LocalDate dateAppointment) {
		List<Appointment> appointment = this.appointmentsRepository.findByDateAppointmentOrderByAffiliateId(dateAppointment);
		return appointment;
	}
	@Override
	public List<Appointment> getByIdaffiliate (Affiliates affiliatesId){
		List<Appointment> affiliateAppointments = this.appointmentsRepository.findByAffiliateIdOrderByDateAppointment(affiliatesId);
		return affiliateAppointments;
	}

}
