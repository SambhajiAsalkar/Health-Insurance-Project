package com.sa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class Proj1ApplicationTests {
	 @Autowired
	    private ApplicationContext context;

	@Test
	void contextLoads() {
		  // Assert that the application context is not null, which indicates it loaded successfully
        assertNotNull(context);

        // Alternatively, you can assert that a specific bean is present
        assertTrue(context.containsBean("yourBeanName"));

	}

	private void assertNotNull(ApplicationContext context2) {
		// TODO Auto-generated method stub
		
	}

	private void assertTrue(boolean containsBean) {
		// TODO Auto-generated method stub
		
	}

}

