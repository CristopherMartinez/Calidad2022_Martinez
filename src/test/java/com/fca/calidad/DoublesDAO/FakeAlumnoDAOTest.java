package com.fca.calidad.DoublesDAO;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class FakeAlumnoDAOTest {
	
	private fakeAlumnoDAO DAO;
	public HashMap<String, Alumno> baseDatos;
	
	@Before
	public void setUp() throws Exception {
		DAO = Mockito.mock(fakeAlumnoDAO.class);
		baseDatos = new HashMap<String, Alumno>();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void agregarTest() {
		
		when(DAO.addAlumno(any(Alumno.class))).thenAnswer(new Answer<Boolean>() {
			
			public Boolean answer(InvocationOnMock invocation) throws Throwable{
				Alumno arg = (Alumno) invocation.getArguments()[0];
				baseDatos.put("1", arg);
				System.out.println("Tamaño despues = " + baseDatos.size() + "\n");
				return true;
			}
		
		});
		
		Alumno a = new Alumno("nombre","id",14,"email");
		int sizeBefore = baseDatos.size();
		Boolean res = DAO.addAlumno(a);
		int sizeAfter = baseDatos.size();
		
		assertThat(sizeAfter,is(sizeBefore + 1));
		
	}
	
	
	

}
