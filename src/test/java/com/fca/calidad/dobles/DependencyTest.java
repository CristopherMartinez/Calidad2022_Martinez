package com.fca.calidad.dobles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
//import static org.mockito.ArgumentMatchers.*;


public class DependencyTest {
	
	
	Dependency dependency;
	
	
	@Before
	public void setUp() throws Exception {
	dependency = Mockito.mock(Dependency.class);
	}
	
	@Test
	public void test() {
		assertThat(dependency.getClassName(),is("Dependency"));
	}


	private void setBehavior() {

		when(dependency.getClassName()).thenReturn("Nombre de la clase");
	}

	@Test
	public void getClassNametest() {
		setBehavior();
		String resultadoEsperado = "Nombre de la clase";
		assertThat(dependency.getClassName(),is(resultadoEsperado));

	}
	
	@Test
	public void mockArgumentTest() {
		int expectedResult = 2;
		int expectedResult2 = 5;
		
		when(dependency.addTwo(0)).thenReturn(2);
		
		int runningResult = dependency.addTwo(0);
		int runningResult2 = dependency.addTwo(5);
		
		assertThat(expectedResult, is(runningResult));
		assertThat(expectedResult2, is(runningResult2));

	}
	
	@Test (expected = ArithmeticException.class)
	public void mockExecption_Test() {
		
		when(dependency.getClassName()).thenThrow(ArithmeticException.class);
		dependency.getClassName();
		
	}
	
	//Prueba del CallRealMethod
	@Test
	public void CallRealMethod() {
		int expectedResult = 47;
		int expectedResult2 = 59;
		
		when(dependency.addTwo(anyInt())).thenCallRealMethod();
		
		int runningResult = dependency.addTwo(45);
		int runningResult2 = dependency.addTwo(57);
		//Se verifica 
		assertThat(expectedResult, is(runningResult));
		assertThat(expectedResult2, is(runningResult2));

	}
	
	//Test for a mocked argument matchers
	@Test
	public void mockAnswer_Test() {
		//Set the behavior of the instance
		when(dependency.addTwo(anyInt())).thenAnswer(
				new Answer<Integer>() {
					
					public Integer answer(InvocationOnMock invocation) throws Throwable{
						//Set behavior in every invocation
						int arg = (Integer) invocation.getArguments()[0];
						System.out.println("Este es el argumento: " + arg);
						System.out.println("Esta es la suma mas dos: " + (arg+2));
						//Return the invoked value
						return arg + 10;
					}
				}
				
				
				
				);
		//Verify
		assertThat(20,is(dependency.addTwo(10)));
		assertThat(49,is(dependency.addTwo(39)));
		
		
	}
	
	
	
	
	
}
