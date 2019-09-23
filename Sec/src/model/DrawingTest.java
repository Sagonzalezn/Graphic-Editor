package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DrawingTest {
	
	private Drawing drawing;

	@BeforeEach
	void setUp() throws Exception {
		drawing = new Drawing();
	}

	@Test
	void test() {
		assertEquals(0, drawing.numFigures());
	}
	
	@Test
	void test2() {
		drawing.addFigure( 
				new Line(new Point(100, 100), new Dimension(500, 300), Color.BLUE, Color.LIGHT_GRAY,10.0f, null)
				);
		assertEquals(1, drawing.numFigures());
	}

}
