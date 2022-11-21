package com.fca.calidad.DoublesDAO;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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

	//Correcto
	@Test
	public void agregarAlumnoTest() {
		
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

	//Correcto
	  @Test
    public void eliminarAlumnoTest() {
  	
		when(DAO.deleteAlumno(any(Alumno.class))).thenAnswer(new Answer<Boolean>() {
			
			public Boolean answer(InvocationOnMock invocation) throws Throwable{
				//Seteamos
				Alumno arg = (Alumno) invocation.getArguments()[0];
				
				//Removemos del hashMap con metodo remove
				baseDatos.remove(arg.getId());
				
				return true;
			}
		
		});
		
		Alumno alumno1 = new Alumno("nombre","1",14,"email");
		
		//Ingresamos a alumno 
		baseDatos.put("1", alumno1);
		
		int sizeS = baseDatos.size();
		
		//Borramos a alumno1 y almacenamos el valor en res 
		Boolean res = DAO.deleteAlumno(alumno1);
		
		int sizeAfterDelete = baseDatos.size();
		
		//Verificamos
		assertThat(sizeAfterDelete, is(sizeS - 1));
		System.out.println("TestEliminar : " + res);
		
	
	}
	  
	 //Correcto
	@Test
	public void actualizarAlumnoTest() {
		when(DAO.updateEmail(any(Alumno.class))).thenAnswer(new Answer<Boolean>() {
			
			public Boolean answer(InvocationOnMock invocation) throws Throwable{
				Alumno arg = (Alumno) invocation.getArguments()[0];
				baseDatos.put(arg.getId(), arg);
				return true;
			}
		
		});
		Alumno alumno = new Alumno("nombre","id",14,"email");
		baseDatos.put("1", alumno);
		
		//Cambiar el correo
		String emailNuevo = "Nuevocorreo";
		alumno.setEmail(emailNuevo);
		
		//Llamar al metodo update
		DAO.updateEmail(alumno);
		
		//Verificar
		String valorEsperado = emailNuevo;
		String valorEjecucion = baseDatos.get("1").getEmail();		
		
		//Lo comparamos con assertThat
		assertThat(valorEsperado, is(valorEjecucion));
		
		System.out.print("TestActualizar : " + DAO.updateEmail(alumno));
	}
	
      //Correcto
	  @Test
	  public void buscarAlumnoTest() {
		  when(DAO.searchAlumno(anyString())).thenAnswer(new Answer<Alumno>() {
				public Alumno answer(InvocationOnMock invocation) throws Throwable{
					// Set behavior
					String id = (String) invocation.getArguments()[0];
					Alumno a = baseDatos.get(id);
					return a;
				}
			});
			
		  	//Creamos un nuevo alumno
			Alumno a = new Alumno("nombre","1",14,"email");
			//Agregamos a la base de datos
			baseDatos.put("1", a);
			
			//Llamamos a metodo buscarAlumno
			Alumno res = DAO.searchAlumno("1");
			
			//Verificamos
			String nomEsperado = res.getNombre();
			String idEsperado = res.getId();
			int edadEsperado = res.getEdad();
			String emailEsperado = res.getEmail();
			String nomEjecucion = baseDatos.get("1").getNombre();
			String idEjecucion = baseDatos.get("1").getId();
			int edadEjecucion = baseDatos.get("1").getEdad();
			String emailEjecucion = baseDatos.get("1").getEmail();
			
			//Verificamos cada dato
			assertThat(nomEsperado,is(nomEjecucion));
			assertThat(idEsperado,is(idEjecucion));
			assertThat(edadEsperado,is(edadEjecucion));
			assertThat(emailEsperado,is(emailEjecucion));
			System.out.print("TestBuscar : " + res);
	  }
      
      
      

	

}
