package com.reto.citas.controllers;

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
import com.reto.citas.entities.Tests;
import com.reto.citas.servers.TestServices;

@RestController
@RequestMapping("/tests")
public class TestsController {
	
	@Autowired
	private TestServices testServiceImpl;
	
	@GetMapping()
	
	public ResponseEntity<?> consultarTest(){
		
		List<Tests> testconsultados = this.testServiceImpl.consultarTest();
		if(testconsultados.isEmpty()){
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			return ResponseEntity.ok(testconsultados);
		}
		
		
	}
	@PostMapping
	
	public ResponseEntity<?> guardarTest(@RequestBody Tests test){
		
		
		if(test.getName()==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		if(test.getDescription()==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		if(testServiceImpl.guardarTest(test)==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		try {
			Tests testguardado = this.testServiceImpl.guardarTest(test);
			return ResponseEntity.status(HttpStatus.CREATED).body(testguardado);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}
	
	@PutMapping
	
	public ResponseEntity<?> actualizarTest(@RequestBody Tests test){
		
		
		
		if (test.getName()==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		if (test.getDescription()==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		if(testServiceImpl.actualizarTest(test)==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		try {
			Tests testActualizado = this.testServiceImpl.actualizarTest(test);
			return ResponseEntity.status(HttpStatus.CREATED).body(testActualizado);
			
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}
	
	@DeleteMapping
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminarTest(@PathVariable Long id){
		
		try {		
			this.testServiceImpl.eliminarTest(id);
			return ResponseEntity.ok().build();
			
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}

	}
	@GetMapping()
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable Long id){
		
		
		try {
			Tests testById = this.testServiceImpl.getById(id);
			return  ResponseEntity.ok(testById);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
}

