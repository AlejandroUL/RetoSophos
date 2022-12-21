package com.reto.citas.servers;

import java.util.List;
import com.reto.citas.entities.Tests;

public interface TestServices {

	List<Tests> consultarTest();
	Tests guardarTest(Tests test);
	void eliminarTest(Long id);
	Tests actualizarTest(Tests test);
	Tests getById(Long id);
}
