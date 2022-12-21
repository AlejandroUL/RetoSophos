package com.reto.citas.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reto.citas.entities.Affiliates;
import com.reto.citas.entities.Appointment;
import com.reto.citas.servers.AppointmentsServices;

@RestController
@RequestMapping("/appointments")
public class AppointmentsController {
	
	@Autowired
	private AppointmentsServices appointmentsServicesimpl;
	
	@GetMapping()
	
	public ResponseEntity<?> consultarAppointments(){
		
		List<Appointment> ListaAppointments = this.appointmentsServicesimpl.consultarAppointmets();
		if(ListaAppointments.isEmpty()){
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			return ResponseEntity.ok(ListaAppointments);
		}
	}
	
	@PostMapping
	
	public ResponseEntity<?> guardarappointment (@RequestBody Appointment appointment){
		
		
		
		if(appointment.getAffiliateId()==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		if(appointment.getTestId()==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		if(appointment.getDateAppointment()==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		if(appointment.getHourAppointment()==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		if (this.appointmentsServicesimpl.guardarAppointment(appointment)==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		try {
			Appointment appointmentGuardado = this.appointmentsServicesimpl.guardarAppointment(appointment);
			return ResponseEntity.status(HttpStatus.CREATED).body(appointmentGuardado);
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}
	
	@DeleteMapping
	
	@RequestMapping(value="{appointment_id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminarAppointment(@PathVariable Long appointment_id){
		try {
			this.appointmentsServicesimpl.eliminarAppointment(appointment_id);
			return ResponseEntity.ok().build();
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		
	}
	
	@PutMapping
	
	public ResponseEntity<?> actualizatAppointment (@RequestBody Appointment Newappointment){
		
		if(Newappointment.getAffiliateId()==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		if(Newappointment.getTestId()==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		if(Newappointment.getDateAppointment()==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		if(Newappointment.getHourAppointment()==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		if(appointmentsServicesimpl.actualizatAppointment(Newappointment)==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		try {
			Appointment appointmentActualizado = this.appointmentsServicesimpl.actualizatAppointment(Newappointment);
			return ResponseEntity.status(HttpStatus.CREATED).body(appointmentActualizado);
			
			}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}
	
	@GetMapping()
	
	@RequestMapping(value="{appointment_id}", method = RequestMethod.GET)
	public ResponseEntity<?> consultarAppointmentById (@PathVariable Long appointment_id){
		
		
		try {
			Appointment appointment = this.appointmentsServicesimpl.getByIdAppointment(appointment_id);
			return ResponseEntity.ok(appointment);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}
	
	@GetMapping()
	
	@RequestMapping(value="/date/{dateAppointment}", method = RequestMethod.GET)
	public ResponseEntity<?> appointmentsBydate (@PathVariable ("dateAppointment") String dateApp){
		
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate dateAppointment = LocalDate.parse(dateApp,dateFormat);
		List<Appointment> appointmentsbydate = this.appointmentsServicesimpl.getByDate(dateAppointment);
		
		
		try {
			if(appointmentsbydate.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}

			return ResponseEntity.ok(appointmentsbydate);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}
	
	@GetMapping()
	
	@RequestMapping(value="/affiliate/{affiliatesId}", method = RequestMethod.GET)
	public ResponseEntity<?> appointmentsByIdaffiliate (@PathVariable ("affiliatesId") Affiliates affiliatesId){
		
		
		try {
			List<Appointment> appointmentsbyaffiliate = this.appointmentsServicesimpl.getByIdaffiliate(affiliatesId);
			if(appointmentsbyaffiliate.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			return ResponseEntity.ok(appointmentsbyaffiliate);
		}catch (Exception e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}
	
}









