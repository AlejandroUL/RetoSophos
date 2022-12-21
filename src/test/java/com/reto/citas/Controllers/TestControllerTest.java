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


import com.reto.citas.controllers.TestsController;
import com.reto.citas.entities.Tests;
import com.reto.citas.servers.TestServices;


@ExtendWith(MockitoExtension.class)
public class TestControllerTest {
	
	@Mock
	private TestServices TestServicesimpl;
	
	@InjectMocks
	private TestsController testController;
	
	Tests test = new Tests(1L,"Dopping","Clerbutamol");
	
	
	@Test
	public void testGetTestsOk(){
		
		List<Tests> records = new ArrayList <Tests>();
		records.add(new Tests());
		
		when(TestServicesimpl.consultarTest()).thenReturn(records);
		var response = testController.consultarTest();
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		
	}
	@Test
	public void getTestsNotOk() {
		
		when(TestServicesimpl.consultarTest()).thenReturn(Collections.emptyList());
		var response = testController.consultarTest();
		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		
		
	}
	@Test
	public void testGetByIdTestOk (){
		
		Tests test = new Tests();
		
		when(TestServicesimpl.getById(anyLong())).thenReturn(test);
		var response = testController.getById(anyLong());

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());	
	}
	@Test
	public void testGetByIdTestNotOk(){
		
		when(TestServicesimpl.getById(anyLong())).thenThrow(new RuntimeException("Exception"));
		var response = testController.getById(anyLong());
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		
		
	}
	@Test
	public void testPostTestOk() {
		
		when(TestServicesimpl.guardarTest(test)).thenReturn(test);
		var response = testController.guardarTest(test);
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		
	}
	@Test
	public void testPostTestNotOk() {
		
		when(TestServicesimpl.guardarTest(test)).thenReturn(null);
		var response = testController.guardarTest(test);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		
		
	}
	
	@Test
	void testPutTestOk() {
		
		when(TestServicesimpl.actualizarTest(test)).thenReturn(test);
		var response = testController.actualizarTest(test);
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());	
		}
	@Test
	void testPutTestNotOk() {
		
		when(TestServicesimpl.actualizarTest(test)).thenReturn(null);
		var response = testController.actualizarTest(test);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());	
		}
	@Test
	public void testDeleteAffiliateOk () {
		
		doNothing().when(TestServicesimpl).eliminarTest(anyLong());
		var response = testController.eliminarTest(anyLong());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		
	}	
	@Test
	public void testDeleteAffiliateNotOk () {
		
		doThrow(new RuntimeException("Exception")).when(TestServicesimpl).eliminarTest(anyLong());
		var response = testController.eliminarTest(anyLong());
		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
}
