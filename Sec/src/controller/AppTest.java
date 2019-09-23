package controller;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import controller.App;

class AppTest {
	
	@Test
	void test() {
		App app = App.getInstance();
		
		assertNotNull(app);
	}
	
	@Test
	void test2() {
		App app = App.getInstance();
		
		assertEquals(0, app.numFigures());
	}


}
