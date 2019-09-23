package model;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;
import java.security.InvalidParameterException;

public abstract class Figure implements Serializable {

	public Figure(Point pt, Dimension size, Color color, Color relleno) {

		super();
		this.size = size;
		this.colorDeLinea = color;
		this.colorDeRelleno = relleno;
		this.pt = pt;
		
		this.bbox = new BoundBox(pt, size);
		
		if ( needsNormalization() ) { 
			bbox.normalize();
		}
	}


	protected abstract void rellenarFigura(Graphics2D g);
	protected abstract void doPaint(Graphics2D g);
	
	public void paint(Graphics2D g) {
		rellenarFigura(g);
		doPaint(g);
		
		if ( isSelected() ) {
			if ( needsNormalization() ) { 
				bbox.paint( g );
			}
			else {
				BoundBox norm = new BoundBox( bbox );
				norm.normalize();
				
				norm.paint( g );
			}
		}
		
	}


	public void setPosition(Point pt) {
		if (pt == null) {
			throw new InvalidParameterException("dfxgcvd");
		}
		this.pt = pt;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void select( Point pt ) {
		
		if ( needsNormalization() ) { 
			setSelected(
				bbox.contains( pt )
			);
		}
		else {
			BoundBox norm = new BoundBox( bbox );
			norm.normalize();
			
			setSelected(
				norm.contains( pt )
			);			
		}		
	}
	
	public boolean contains( Point pt ) {
		return bbox.contains( pt );
	}
	
	public void select( BoundBox user ) {
		if ( needsNormalization() ) { 
			setSelected(user.contains(bbox));
			if(user.contains(bbox))
			{
				bbox.setParent(user);
				user.childrens.add(bbox);
			}
		}
		else {
			BoundBox norm = new BoundBox( bbox );
			norm.normalize();
			setSelected(user.contains( norm )
			);			
		}		
	}
	
	protected boolean needsNormalization() {
		return true;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
		if(!selected){
			this.bbox.setParent(null);	
		}
		
		
		
	}
	
	public Point getPosition() {
		return bbox.getLocation();
	}

	public Dimension getSize() {
		return bbox.getSize();
	}

	public Cursor getCursor( Point pt ) {
		Cursor cursor = bbox.getCursor( 
			pt
		);
		
		if ( cursor == null ) {
			if ( needsNormalization() ) { 
				if ( bbox.contains( pt ) ) {
					cursor = Cursor.getPredefinedCursor(
						Cursor.MOVE_CURSOR
					);
				}
			}
			else {
				BoundBox norm = new BoundBox( bbox );
				norm.normalize();
				
				if ( norm.contains( pt ) ) {
					cursor = Cursor.getPredefinedCursor(
						Cursor.MOVE_CURSOR
					);
				}
			}
		}
		else {
			// OK
		}
		
		return cursor;
	}

	protected Point pt;
	protected Dimension size;
	protected Color colorDeLinea;
	protected Color colorDeRelleno;
	protected boolean selected;
	private BoundBox bbox;
	
	public void move( int dx, int dy ) {
		if(bbox.getParent()!=null) {
			
		for ( int c = bbox.getParent().childrens.size() -1;c >= 0;c-- ) {
				
				BoundBox box = bbox.getParent().childrens.get( c );	
				box.doMove( dx, dy );

		}
		
		}else {
			bbox.doMove( dx, dy );

		}
	}


	public ControlPoint getControlPoint( Point pt ) {
		return bbox.getControlPoint( pt );
	}
}
