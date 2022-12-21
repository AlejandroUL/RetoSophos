package com.reto.citas.Controllers;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

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

import com.reto.citas.controllers.AffiliatesController;
import com.reto.citas.entities.Affiliates;
import com.reto.citas.servers.AffiliatesServices;



@ExtendWith(MockitoExtension.class)
public class AffiliateControllerTest {
	
	Affiliates aff = new Affiliates(1L, "Messi",35,"elantibicho");
	Affiliates badaff = new Affiliates();
	
	
	@Mock
	private AffiliatesServices affiliatesServicesimpl;
	
	
	@InjectMocks
	private AffiliatesController affiliatesController;
		
	
	@Test
	public void getAffiliatesOk(){
	
		List <Affiliates> records = new ArrayList<Affiliates>();
		records.add(new Affiliates());
		
		when(affiliatesServicesimpl.consultarAffiliates()).thenReturn(records);
		var response = affiliatesController.consultaraffiliates();
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
		
	@Test
	public void getAffiliatesNotOk() {
		
		when(affiliatesServicesimpl.consultarAffiliates()).thenReturn(Collections.emptyList());
		var response = affiliatesController.consultaraffiliates();
		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		
	}
	
	
	@Test
	public void testGetByIdAffiliateOk (){
		
		Affiliates affiliate1 = new Affiliates();
		
		when(affiliatesServicesimpl.getByIdAffiliates(anyLong())).thenReturn(affiliate1);
		var response = affiliatesController.consultaraffiliateById(anyLong());

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testGetByIdAffiliateNotOk (){
		
		when(affiliatesServicesimpl.getByIdAffiliates(anyLong())).thenThrow(new RuntimeException("Exception"));
		var response = affiliatesController.consultaraffiliateById(anyLong());
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		
	}
	@Test
	public void testPostAffiliateOk() {
		
		when(affiliatesServicesimpl.guardarAffiliate(aff)).thenReturn(aff);
		var response = affiliatesController.guardaraffiliates(aff);
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	@Test
	public void testPostAffiliateNotOk() {
		
		when(affiliatesServicesimpl.guardarAffiliate(aff)).thenReturn(null);
		var response = affiliatesController.guardaraffiliates(aff);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	public void testDeleteAffiliateOk () {
		
		doNothing().when(affiliatesServicesimpl).eliminarAffiliate(anyLong());
		var response = affiliatesController.eliminarAffiliate(anyLong());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		
	}	
	@Test
	public void testDeleteAffiliateNotOk () {
		
		doThrow(new RuntimeException("Exception")).when(affiliatesServicesimpl).eliminarAffiliate(anyLong());
		var response = affiliatesController.eliminarAffiliate(anyLong());
		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
	
	@Test
	void testPutAffiliateOk() {
		
		when(affiliatesServicesimpl.actualizarAffiliate(aff)).thenReturn(aff);
		var response = affiliatesController.actualizaraffiliate(aff);
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());	
		}
	@Test
	void testPutTestAffiliateNotOk() {
		
		when(affiliatesServicesimpl.actualizarAffiliate(aff)).thenReturn(null);
		var response = affiliatesController.actualizaraffiliate(aff);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());	
		}
	

}
