package com.reto.citas.servers;

import java.util.List;

import com.reto.citas.entities.Affiliates;

public interface AffiliatesServices {

	List<Affiliates> consultarAffiliates();
	Affiliates guardarAffiliate(Affiliates afiliado);
	void eliminarAffiliate(Long id);
	Affiliates actualizarAffiliate(Affiliates afiliado);
	Affiliates getByIdAffiliates(Long id);
	
	
}
