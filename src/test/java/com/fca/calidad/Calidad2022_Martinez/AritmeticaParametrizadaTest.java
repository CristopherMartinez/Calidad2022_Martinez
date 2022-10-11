package com.fca.calidad.Calidad2022_Martinez;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class AritmeticaParametrizadaTest {
	private float arg1;
	private float arg2;
	private float expected;
	private Aritmetica aritmetica;
	
	
	public AritmeticaParametrizadaTest (float arg1, float arg2, float expected) {
		this.arg1 = arg1;
		this.arg1 = arg1;
		this.expected = expected;
		
	}
	
	@Parameters
		public static Collection<Object[]> data(){
			return Arrays.asList(new Object[][]{
				{4,2,8},
				{6,-3,-18},
				{-5,-5,25}
			});
		}
		

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		//fail("Not yet implemented");
	}

}
