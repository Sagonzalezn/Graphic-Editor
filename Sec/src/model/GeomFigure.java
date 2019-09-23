package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

public abstract class GeomFigure extends Figure {

	public float thickness;
	public float[] style;
	
	public abstract void rellenarFigura(Graphics2D g);

	public GeomFigure(Point pt, Dimension size, Color color, Color relleno,float thickness, float[] style) {
		super(pt, size, color,relleno);
		this.thickness = thickness;
		this.style = style;
	}

}
