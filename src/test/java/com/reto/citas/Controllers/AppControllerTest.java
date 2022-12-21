package com.reto.citas.Controllers;

import static org.mockito.Mockito.when;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.HttpStatus;

import com.reto.citas.controllers.AppointmentsController;
import com.reto.citas.entities.Affiliates;
import com.reto.citas.entities.Appointment;
import com.reto.citas.entities.Tests;
import com.reto.citas.servers.AppointmentsServices;

@ExtendWith(MockitoExtension.class)
public class AppControllerTest {
	
	@Mock
	private AppointmentsServices appointmentsServices;
	
	@InjectMocks
	private AppointmentsController appController;
	
	String dateApp = "25-08-2023";
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	LocalDate date = LocalDate.parse(dateApp,dateFormat);
	
	String hourApp = "13:00";
	DateTimeFormatter hourFormat = DateTimeFormatter.ofPattern("HH:MM");
	LocalTime hour = LocalTime.parse(hourApp, hourFormat);
	
	Affiliates aff = new Affiliates();
	Tests test = new Tests();
	Appointment app = new Appointment(1L,date,hour,aff,test);
	
	@Test
	public void testgetAppOK() {
		List<Appointment> app = new ArrayList<Appointment>();
		app.add(new Appointment());
		when(appointmentsServices.consultarAppointmets()).thenReturn(app);
		var response = appController.consultarAppointments();
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		
	}
	@Test
	public void testgetAppNotOK() {
		
		when(appointmentsServices.consultarAppointmets()).thenReturn(Collections.emptyList());
		var response = appController.consultarAppointments();
		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		
	}
	
	@Test
	public void testGetByIdAppOk (){
		
		Appointment app = new Appointment();
		when(appointmentsServices.getByIdAppointment(anyLong())).thenReturn(app);
		var response = appController.consultarAppointmentById(anyLong());
		
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testGetByIdAppNotOk (){
		
		when(appointmentsServices.getByIdAppointment(anyLong())).thenThrow(new RuntimeException("Exception"));
		var response = appController.consultarAppointmentById(anyLong());
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	public void testPostAffiliateOk() {
		
		
		when(appointmentsServices.guardarAppointment(app)).thenReturn(app);
		var response = appController.guardarappointment(app);
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	@Test
	public void testPostAffiliateNotOk() {
		
		
		when(appointmentsServices.guardarAppointment(app)).thenReturn(null);
		var response = appController.guardarappointment(app);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	
	}
	
	@Test
	public void testDeleteAppOk () {
		
		doNothing().when(appointmentsServices).eliminarAppointment(anyLong());
		var response = appController.eliminarAppointment(anyLong());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		
	}	
	
	@Test
	public void testDeleteAppNotOk () {
		
		doThrow(new RuntimeException("Exception")).when(appointmentsServices).eliminarAppointment(anyLong());
		var response = appController.eliminarAppointment(anyLong());
		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	
	}
	@Test
	void testPutAffiliateOk() {
		
		when(appointmentsServices.actualizatAppointment(app)).thenReturn(app);
		var response = appController.actualizatAppointment(app);
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());	
		}
	
	@Test
	void testPutAffiliateNotOk() {
		
		when(appointmentsServices.actualizatAppointment(app)).thenReturn(null);
		var response = appController.actualizatAppointment(app);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());	
		}
	@Test
	void testGetByDateAppOk() {
		List<Appointment> app = new ArrayList<Appointment>();
		app.add(new Appointment());
		String dateApp = "25-08-2023";
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.parse(dateApp,dateFormat);
		when(appointmentsServices.getByDate(date)).thenReturn(app);
		var response = appController.appointmentsBydate(dateApp);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());	
	}
	
	@Test
	void testGetByDateAppNotOk() {
		List<Appointment> appo = new ArrayList<Appointment>();
		appo.add(app);
		
		String dateApp = "25-08-2023";
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.parse(dateApp,dateFormat);
		
		when(appointmentsServices.getByDate(date)).thenReturn(null);
		var response = appController.appointmentsBydate(dateApp);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());	
	}
	
	@Test
	void testGetAppByIdAffOk() {
		List<Appointment> app = new ArrayList<Appointment>();
		app.add(new Appointment());
		Affiliates aff = new Affiliates();
		when(appointmentsServices.getByIdaffiliate(aff)).thenReturn(app);
		var response = appController.appointmentsByIdaffiliate(aff);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());	
	}
	
	@Test
	void testGetAppByIdAffNotOk() {
		List<Appointment> app = new ArrayList<Appointment>();
		app.add(new Appointment());
		Affiliates aff = new Affiliates();
		when(appointmentsServices.getByIdaffiliate(aff)).thenReturn(null);
		var response = appController.appointmentsByIdaffiliate(aff);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());	
	}
	
}
