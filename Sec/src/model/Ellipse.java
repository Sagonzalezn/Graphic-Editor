package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

public class Ellipse extends GeomFigure {



	public Ellipse( Point pt, Dimension size, Color color,Color relleno, float thickness, float[] style) {
		super(pt, size, color, relleno, thickness, style);

	}
	

	@Override
	protected void doPaint(Graphics2D g) {
		
		Point pt = getPosition();
		Dimension size = getSize();
		
		g.setColor(colorDeLinea);
		g.setStroke(new BasicStroke());
		//Point pt = getPosition();
		g.drawOval(pt.x, pt.y, size.width, size.height);
		
	}

	@Override
	public void rellenarFigura(Graphics2D g) {
		
		Point pt = getPosition();
		Dimension size = getSize();
		
		g.setColor(colorDeRelleno);
		g.fillOval(pt.x, pt.y, size.width, size.height);
	}
	
	

}
