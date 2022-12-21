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

import com.reto.citas.entities.Affiliates;
import com.reto.citas.servers.AffiliatesServices;

@RestController 

@RequestMapping("/affiliates")
public class AffiliatesController {
	

	@Autowired
	private AffiliatesServices affiliatesServicesimpl;
	
	@GetMapping() 

	public ResponseEntity<?> consultaraffiliates(){

		List<Affiliates> affiliatesconsultados = this.affiliatesServicesimpl.consultarAffiliates();
		
		if(affiliatesconsultados.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			return ResponseEntity.ok(affiliatesconsultados);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> guardaraffiliates(@RequestBody Affiliates affiliate){
		
		
		if (affiliate.getAffiliatesName()==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		if (affiliate.getMail()==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		if (affiliate.getAffiliatesAge() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		if (affiliatesServicesimpl.guardarAffiliate(affiliate)==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		try {
			Affiliates affiliateguardado = this.affiliatesServicesimpl.guardarAffiliate(affiliate);
			return ResponseEntity.status(HttpStatus.CREATED).body(affiliateguardado);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	
	}
	
	@PutMapping
	
	public ResponseEntity<?> actualizaraffiliate(@RequestBody Affiliates NewAffiliate){
		
		if (NewAffiliate.getAffiliatesName()==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		if (NewAffiliate.getMail()==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		if (NewAffiliate.getAffiliatesAge() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		if(affiliatesServicesimpl.actualizarAffiliate(NewAffiliate)==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		try {
			Affiliates affiliateActualizado = this.affiliatesServicesimpl.actualizarAffiliate(NewAffiliate);
			return ResponseEntity.status(HttpStatus.CREATED).body(affiliateActualizado);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}
	@DeleteMapping
	
	@RequestMapping(value="{affiliates_id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminarAffiliate(@PathVariable Long affiliates_id ){
		
		try {
			this.affiliatesServicesimpl.eliminarAffiliate(affiliates_id);
			return ResponseEntity.ok().build();
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}

	}
	
	@GetMapping()
	
	@RequestMapping(value="/{affiliates_id}", method = RequestMethod.GET)
	public ResponseEntity<?> consultaraffiliateById (@PathVariable Long affiliates_id){
		
		
		try {
			Affiliates afiliado = this.affiliatesServicesimpl.getByIdAffiliates(affiliates_id);
			return ResponseEntity.ok(afiliado);
			
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}

}
