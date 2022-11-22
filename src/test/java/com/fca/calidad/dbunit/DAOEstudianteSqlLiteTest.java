package com.fca.calidad.dbunit;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.dbunit.Assertion;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;
import junit.framework.TestCase;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
 
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class DAOEstudianteSqlLiteTest extends TestCase{

	
	public IDatabaseConnection connection;
	public DAOEstudianteSQLlite daoSQLite;
	
	public DAOEstudianteSqlLiteTest(String name)
	{
		super(name);
	}

	protected IDataSet getDataSet() throws Exception {
		// TODO Auto-generated method stub
		return new FlatXmlDataSetBuilder().build
				(new File("./src/resources/initDB.xml"));
	}
	
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		daoSQLite = new DAOEstudianteSQLlite ();
		Connection jdbcConnection;
		
		jdbcConnection = DriverManager.getConnection
				("jdbc:sqlite:C:\\Users\\marti\\Documents\\Calidad2022_Martinez\\Calidad2022_Martinez\\src\\resources\\Alumnos.db");
		
		connection = new DatabaseConnection(jdbcConnection);
		
		try {
			
			PreparedStatement preparedStatement;
			preparedStatement = jdbcConnection.prepareStatement("Delete from sqlite_sequence WHERE name = ?");
			// Set the values to match in the ? on query
			
			
			preparedStatement.setString(1, "Estudiante");
			
			Boolean result = false;

			// Return the result of connection and statement
			if (preparedStatement.executeUpdate() >= 1) {
				result = true;
			}
			preparedStatement.close();
			
			DatabaseOperation.CLEAN_INSERT.execute(connection, getDataSet());
		} catch(Exception e) {
			fail("Error in setup: " + e.getMessage());
			connection.close();
		} 
	}
	
	@After
	public void tearDown()
	{
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public IDatabaseConnection getConnection()  {
		return connection;
	}
	
	//TEST 
	//CORRECTO
	
	@Test
	public void testCrear() {
		Estudiante alumno = new Estudiante ("alumno004","alumnoap004","email004" ,"carrera4");
		
		int id = daoSQLite.createEstudiante(alumno);
		alumno.setId(id);
		
		//verify
		int numEsperado = 4; //sabemos que iniciamos con 3 y agregamos una mas
		int numReal = -1;
		try {
			IDataSet databaseDataSet = getConnection().createDataSet(); //esta es toda la base de datos
			
			ITable actualTable = databaseDataSet.getTable("Estudiante"); //esta es la tabla que estamos usando
			
			numReal = actualTable.getRowCount(); //número de filas
			//verificar
			assertThat(numEsperado,is(numReal));			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in insert ttest: " + e.getMessage());
		}
	}
	//TEST 
	//CORRECTO
	@Test
	 public void testBuscar()  {
		Estudiante alumno = daoSQLite.findEstudiante(0);
		
		
		try {
			
			//Declaramos variables que nos sirven para ejecutar el Query
			String tabla = "Estudiante";
			String sentencia = "SELECT * FROM estudiante where id = 0";
			
			ITable actualTable = getConnection().createQueryTable(tabla,sentencia);
			
			//Comparamos con la tabla 
			assertThat(alumno.getNombre(), is (actualTable.getValue(0, "nombre")));
			assertThat(alumno.getApellido(), is (actualTable.getValue(0, "apellido")));
			assertThat(alumno.getEmail(), is (actualTable.getValue(0, "email")));
			assertThat(alumno.getCarrera(), is (actualTable.getValue(0, "carrera")));
		
			//Imprimimos el estudiante
			System.out.println("Estudiante: " + actualTable.getValue(0, "nombre"));
			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in insert ttest: " + e.getMessage());
		}
		}
	
	//TEST 
	//CORRECTO
	@Test
	public void testUpdate() throws Exception{
		
		Estudiante Estudiante2 = daoSQLite.findEstudiante(0);
		Estudiante2.setEmail("martinezcristopher11@gmail.com");
		daoSQLite.updateEmailEstudiante(Estudiante2);
		
		
		try {
			//Declaramos variables que nos sirven para ejecutar el Query
			String tabla = "Estudiante";
			String sentencia = "SELECT * FROM estudiante where id = 0";
			
			ITable actualTable = getConnection().createQueryTable(tabla,sentencia);
			
			//Se hace la comparacion de cada campo para determinar 
			assertThat(Estudiante2.getNombre(), is(actualTable.getValue(0, "nombre")));
			assertThat(Estudiante2.getApellido(), is(actualTable.getValue(0, "apellido")));			
			assertThat(Estudiante2.getEmail(), is(actualTable.getValue(0, "email")));			
			assertThat(Estudiante2.getCarrera(), is(actualTable.getValue(0, "carrera")));
	
			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in insert ttest: " + e.getMessage());
		}
	}
	//CORRECTO
	@Test
	public void testEliminarAlumno() throws Exception{
		
		Estudiante Estudiante = daoSQLite.findEstudiante(0);
		int idAlumno = Estudiante.getId();
		daoSQLite.deleteEstudiante(idAlumno);
		
		//verify
		try {
			//Declaramos variables que nos sirven para ejecutar el Query
			String tabla = "Estudiante";
			String sentencia = "SELECT * FROM estudiante where id = 0";
			int result = 0;
			
			//Ejecutamos el createQueryTable para traernos la tabla
			ITable actualTable = getConnection().createQueryTable(tabla,sentencia);
			
			//Comparamos los rows con el result
			assertThat(actualTable.getRowCount(), is(result));
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in insert ttest: " + e.getMessage());
		}
	}
	
	
	
	/*
	 	
	@Test
	
	
	public void bTestFind() throws Exception {
		Estudiante alumno;
		alumno = daoSQLite.findEstudiante(1);
		
		try {
			ITable actualTable = getConnection().createQueryTable(
	                "Estudiante",
	                "SELECT * FROM Estudiante WHERE id = 1"); //tabla con los resultados del query
			assertThat(alumno.getApellido(), is (actualTable.getValue(1, "apellido")));
			assertThat(alumno.getCarrera(), is (actualTable.getValue(1, "carrera")));
			assertThat(alumno.getNombre(), is (actualTable.getValue(1, "nombre")));
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in insert ttest: " + e.getMessage());
		}
		}
	
	
		
	
	 
	 @Test
		public void testFind() {
			Estudiante alumno;
			alumno = daoSQLite.findEstudiante(0);
			
			try {
				ITable actualTable = getConnection().createQueryTable(
		                "Estudiante",
		                "SELECT * FROM Estudiante WHERE id = 1"); //tabla con los resultados del query
				assertThat(alumno.getApellido(), is (actualTable.getValue(1, "apellido")));
				assertThat(alumno.getCarrera(), is (actualTable.getValue(1, "carrera")));
				assertThat(alumno.getNombre(), is (actualTable.getValue(1, "nombre")));
			} catch (Exception e) {
				// TODO: handle exception
				fail("Error in insert ttest: " + e.getMessage());
			}
		}
	
		@Test
		public void testUpdate() {
		 
			DAOEstudianteSQLlite daoSQLite = new DAOEstudianteSQLlite ();
			
			Estudiante Estudiante2 = daoSQLite.findEstudiante(1);
			Estudiante2.setEmail("nuevo12@mail.com");
			daoSQLite.updateEmailEstudiante(Estudiante2);
			
			//verify
			try {
				IDataSet databaseDataSet = getConnection().createDataSet(); //esta es toda la base de datos
				
				ITable actualTable = databaseDataSet.getTable("Estudiante");
				
				//Leer el archivo con el resultado esperado
				IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/update_result.xml"));
				ITable expectedTable = expectedDataSet.getTable("Estudiante");
				
				Assertion.assertEquals(expectedTable, actualTable);
				
				
			} catch (Exception e) {
				// TODO: handle exception
				fail("Error in insert ttest: " + e.getMessage());
			}
		}
		
	  
	@Test
	public void ActualizarTest() {
	 
		DAOEstudianteSQLlite daoSQLite = new DAOEstudianteSQLlite ();
		
		Estudiante Estudiante2 = daoSQLite.findEstudiante(1);
		Estudiante2.setEmail("nuevo1@mail.com");
		daoSQLite.updateEmailEstudiante(Estudiante2);
		
		//verify
		try {
			IDataSet databaseDataSet = getConnection().createDataSet(); //esta es toda la base de datos
			
			ITable actualTable = databaseDataSet.getTable("Estudiante");
			
			//Leer el archivo con el resultado esperado
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/update_result.xml"));
			ITable expectedTable = expectedDataSet.getTable("Estudiante");
			
			Assertion.assertEquals(expectedTable, actualTable);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in insert ttest: " + e.getMessage());
		}
	}
	
	

	
	
	@Test
	public void testCrearCompararQuery() {
		Estudiante alumno = new Estudiante ("nombrePruebaCrear","appellidoCrear","email" ,"carrera");
		
		int id = daoSQLite.createEstudiante(alumno);
		alumno.setId(id);
		
		//verify
		int numEsperado = 4; //sabemos que iniciamos con 3 y agregamos una mas
		int numReal = -1;
		try {
			IDataSet databaseDataSet = getConnection().createDataSet(); //esta es toda la base de datos
			
			ITable actualTable = databaseDataSet.getTable("Estudiante"); //esta es la tabla que estamos usando
			
			numReal = actualTable.getRowCount(); //número de filas
			//verificar
			assertThat(numEsperado,is(numReal));			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in insert ttest: " + e.getMessage());
		}
	}
	*/ /*
	@Test
	public void testCrearCompararTabla() {
		//<Estudiante id="3" nombre="nombre1" apellido="apellido1" email="email" carrera = "carrera"/>
		Estudiante alumno = new Estudiante ("nombre1","apellido1","email" ,"carrera");
		
		int id = daoSQLite.createEstudiante(alumno);
		alumno.setId(id);
		
		
		try {
			ITable actualTable = getConnection().createQueryTable(
	                "estudiante",
	                "SELECT * FROM estudiante"); //tabla con los resultados del query
			
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
	                 new File("src/resources/insert_result.xml")); //archivo xml con los datos esperados después de insertar
			 ITable expectedTable = expectedDataSet.getTable("Estudiante");
	        Assertion.assertEquals(actualTable, expectedTable);		 //comparamos las tablas
			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in insert ttest: " + e.getMessage());
		}
	} */
	
	/*
	@Test
	public void testCrearCompararTabla4() {
		//<Estudiante id="3" nombre="nombre1" apellido="apellido1" email="email" carrera = "carrera"/>
		Estudiante alumno = new Estudiante ("nombre1","apellido1","email" ,"carrera");
		
		int id = daoSQLite.createEstudiante(alumno);
		alumno.setId(id);
		
		
		try {
			ITable actualTable = getConnection().createQueryTable(
	                "Estudiante",
	                "SELECT * FROM Estudiante WHERE id = 4"); //tabla con los resultados del query
			//assertThat(alumno.getApellido(), is (actualTable.getValue(0, "apellido")));
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
	                 new File("src/resources/insert2.xml")); //archivo xml con los datos esperados después de insertar
			 ITable expectedTable = expectedDataSet.getTable("Estudiante");
	        Assertion.assertEquals(actualTable, expectedTable);		 //comparamos las tablas
			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in insert ttest: " + e.getMessage());
		}
	}
	
	
	*/
	
}