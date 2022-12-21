package com.reto.citas.servers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto.citas.entities.Tests;
import com.reto.citas.repository.TestRepository;
import com.reto.citas.servers.TestServices;

@Service
public class TestsServiceimpl implements TestServices {

	@Autowired
	private TestRepository testRepository;
	
	
	@Override
	public List<Tests> consultarTest() {
		List<Tests> listaTest = this.testRepository.findAll();
		
		return listaTest;
	}

	@Override
	public Tests guardarTest(Tests test) {
		
		return testRepository.save(test);
	}
	
	@Override
	public Tests actualizarTest(Tests test) {
		
		return this.testRepository.save(test);
	}

	@Override
	public void eliminarTest(Long id) {
		this.testRepository.deleteById(id);
		
	}
	
	@Override
	public  Tests getById (Long id) {
		Tests test = this.testRepository.findById(id).get();
		return  test;
	}
}
