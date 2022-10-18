package com.fca.calidad.dobles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


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
	
}
