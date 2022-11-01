package com.fca.calidad.dbunit;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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


import junit.framework.TestCase;

public class DAOEstudianteSqlLiteTest extends TestCase {

	IDatabaseConnection connection;
	DAOEstudianteSQLlite daoSQLite;
	
	
	public DAOEstudianteSqlLiteTest(String name) {
		super(name);
	}
	
	protected IDataSet getDataSet() throws Exception{
		return new FlatXmlDataSetBuilder().build(new File("src/resources/initDB.xml"));
	}
	
	@Before
	public void setUp() throws Exception {
	super.setUp();
	daoSQLite = new DAOEstudianteSQLlite();
	Connection jdbcConnection;
	jdbcConnection = DriverManager.getConnection
	("jdbc:sqlite:C:\\Users\\alnmi\\OneDrive\\Escritorio\\Clases\\Calidad\\Proyectos java\\Alumnos.db");
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
	
	public void crearEstudiante() {
		Estudiante alumno = new Estudiante("nombre","apellido","email","carrera");
		
		//Insertar estudiante
		daoSQLite.createEstudiante(alumno);
		
		//Verificar
		int numEsperado = 4;
		int numReal = -1;
		
		try {
			IDataSet databaseDataSet = getConnection().createDataSet();
			
			ITable actualTable = databaseDataSet.getTable("Estudiante");
			numReal = actualTable.getRowCount();
			
			//verificar (Falto)
			//assertThat(numEsperado,is(numReal));
			
		}catch(Exception e) {
			System.out.println("Error en crearAlumnoTest");
		}
		
		
	}
    
	
	
	
	
	
	
}
