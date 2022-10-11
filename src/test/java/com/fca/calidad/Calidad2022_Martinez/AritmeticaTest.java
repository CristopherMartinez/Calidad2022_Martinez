package com.fca.calidad.Calidad2022_Martinez;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;



public class AritmeticaTest {
	private Aritmetica aritmetica;
	
	
	@Before
	public void setUp() throws Exception {
		aritmetica = new Aritmetica();
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Este es el after");
	}

	@Test
	public void sumaTest() {
		double resultadoEsperado = 4; 
		double resultadoEjecucion = 0;
		resultadoEjecucion = aritmetica.suma(2, 2);
		assertThat(resultadoEsperado, is(resultadoEjecucion));
	}
	
	@Test
	public void sumaTestFalla() {
		double resultadoEsperado = 4; 
		double resultadoEjecucion = 0;
		resultadoEjecucion = aritmetica.suma(1, 2);
		assertThat(resultadoEsperado, is(resultadoEjecucion));
	}
	
	@Test
	public void restaTest() {
		double result = 3; 
		double r = 0;
		r = aritmetica.resta(10, 7);
		assertThat(result, is(r));
	}
	
	@Test
	public void restaTestFalla() {
		double resultEsperadoRes = 3; 
		double r = 0;
		r = aritmetica.resta(10, 6);
		assertThat(resultEsperadoRes, is(r));
	}
	
	@Test
	public void multiplicacionTest() {
		double resultEsperadoRes = 20; 
		double r = 0;
		r = aritmetica.multiplicacion(4, 5);
		assertThat(resultEsperadoRes, is(r));
	}
	
	@Test
	public void multiplicacionTestFalla() {
		double resultEsperadoRes = 20; 
		double r = 0;
		r = aritmetica.multiplicacion(4, 4);
		assertThat(resultEsperadoRes, is(r));
	}
	
	@Test
	public void division() {
		double resultEsperadoRes = 5; 
		double r = 0;
		r = aritmetica.division(20, 4);
		assertThat(resultEsperadoRes, is(r));
	}
	
	@Test 
	public void divisionEntreCero() {
		
		double resultEsperadoRes = Double.POSITIVE_INFINITY; 
		double r = 0;
		r = aritmetica.division(10, 0);
		assertThat(resultEsperadoRes, is(r));
	}
	
	
	@Test (expected = ArithmeticException.class)
	public void divisionEntera() {
		double resultEsperadoRes = Double.POSITIVE_INFINITY; 
		double resultadoEjecucion = 0;
		resultadoEjecucion = aritmetica.divisionEntera(100, 0);
		
	}



}
