package com.reto.citas.servers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto.citas.entities.Affiliates;
import com.reto.citas.repository.AffiliatesRepository;
import com.reto.citas.servers.AffiliatesServices;

@Service
public class AffiliatesServiceimpl implements AffiliatesServices {

	@Autowired
	private AffiliatesRepository affiliatesRepository;
	
	@Override
	public List<Affiliates> consultarAffiliates() {
		List<Affiliates> listaAfiliados = this.affiliatesRepository.findAll();
		return listaAfiliados;
	}

	@Override
	public Affiliates guardarAffiliate(Affiliates afiliado) {
		
		return affiliatesRepository.save(afiliado);
	}
	
	@Override
	public Affiliates actualizarAffiliate(Affiliates afiliado) {
		
		return affiliatesRepository.save(afiliado);
	}

	@Override
	public void eliminarAffiliate(Long id) {
		this.affiliatesRepository.deleteById(id);
		
	}

	@Override
	public Affiliates getByIdAffiliates(Long id) {
		Affiliates afiliado = this.affiliatesRepository.findById(id).get();
		return afiliado;
	}

}
