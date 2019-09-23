package model;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class Line extends GeomFigure {

	public Line( Point pt, Dimension size, Color color, Color relleno,	float thickness, float[] style) {
		super(pt, size, color, relleno, thickness, style);

	}
	


	
	


	@Override
	protected void doPaint(Graphics2D g) {
		/*
		Point pt = getPosition();
		Dimension size = getSize();
		
		g.setColor(colorDeLinea);
		g.setStroke(new BasicStroke());
		//Point pt = getPosition();
		g.drawLine(pt.x, pt.y, pt.x + size.width, pt.y + size.height);
		
		*/
		
		Point pos = getPosition();
		Dimension size = getSize();
		g.setColor(colorDeLinea);
		g.setStroke(new BasicStroke());
		
		g.drawLine(
			pos.x, 
			pos.y, 
			pos.x + size.width, 
			pos.y + size.height
		);
		
	}


	@Override
	public void rellenarFigura(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}


}
