package model;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;

public class ControlPoint implements Serializable {

	public final int SIZE = 3;
	
	enum Cardinal {
		NW, N, NE, E, SE, S, SW, W
	}
	
	private BoundBox owner;
	private Cardinal cardinal;
	
	public ControlPoint( 
		final BoundBox owner,
		final Cardinal cardinal ) {
		
		this.owner = owner;
		this.cardinal = cardinal;
	}


	public Cursor getCursor() {
		switch ( cardinal ) {
			case NW: return Cursor.getPredefinedCursor(
				Cursor.NW_RESIZE_CURSOR
			);

			case N: return Cursor.getPredefinedCursor(
				Cursor.N_RESIZE_CURSOR
			);

			case NE: return Cursor.getPredefinedCursor(
				Cursor.NE_RESIZE_CURSOR
			);

			case E: return Cursor.getPredefinedCursor(
				Cursor.E_RESIZE_CURSOR
			);

			case SE: return Cursor.getPredefinedCursor(
				Cursor.SE_RESIZE_CURSOR
			);

			case S: return Cursor.getPredefinedCursor(
				Cursor.S_RESIZE_CURSOR
			);

			case SW: return Cursor.getPredefinedCursor(
				Cursor.SW_RESIZE_CURSOR
			);				

			case W: return Cursor.getPredefinedCursor(
				Cursor.W_RESIZE_CURSOR
			);				
		}
		
		return null;
	}

	public void paint( Graphics g ) {
		
		g.setColor( owner.getColor() );
		
		Point pt = getPosition();
		
		g.fillRect(
			pt.x - SIZE, 
			pt.y - SIZE, 
			2 * SIZE + 1, 
			2 * SIZE + 1
		);		
	}

	private Point getPosition() {
		Point pt = new Point();
		
		switch ( cardinal ) {
			case NW: 
				pt.x = owner.x; 
				pt.y = owner.y;
				break;

			case N: 
				pt.x = owner.x + owner.width / 2; 
				pt.y = owner.y;
				break;

			case NE: 
				pt.x = owner.x + owner.width; 
				pt.y = owner.y;
				break;

			case E: 
				pt.x = owner.x + owner.width; 
				pt.y = owner.y + owner.height / 2;
				break;

			case SE: 
				pt.x = owner.x + owner.width; 
				pt.y = owner.y + owner.height;
				break;

			case S: 
				pt.x = owner.x + owner.width / 2; 
				pt.y = owner.y + owner.height;
				break;

			case SW: 
				pt.x = owner.x; 
				pt.y = owner.y + owner.height;
				break;				

			case W: 
				pt.x = owner.x; 
				pt.y = owner.y + owner.height / 2;
				break;				
		}
		
		return pt;
	}


	public boolean contains( Point pt ) {
		
		Point pos = getPosition();
		
		Rectangle r = new Rectangle(
			pos.x - SIZE,
			pos.y - SIZE,
			2 * SIZE + 1,
			2 * SIZE + 1
		);

		return r.contains( pt );
	}
	
	public void resizeFiguras(int dx, int dy) {
		if(owner.getParent() != null) {
			
			for ( int c = owner.getParent().childrens.size() -1;c >= 0;c-- ) {
				
				BoundBox box = owner.getParent().childrens.get( c );
							
				switch ( cardinal ) {
				case NW: 
					box.x += dx;  // mueve en x 
					box.width -= dx;  // modifica en x 
					box.y += dy;  // mueve en y 
					box.height -= dy; // modifica en y 
					break;
		
				case N: 
					box.y += dy;	
					owner.height -= dy;
					break;
		
				case NE: 
					box.y += dy;		
					box.width += dx;
					box.height -= dy;
					break;
		
				case E: 
					
					box.width += dx;
					break;
		
				case SE: 
					//owner.y += dy;		
					box.width += dx;
					box.height += dy;
					//owner.y += dy;  // mueve en y
					break;
		
				case S: 
					box.height += dy;
					break;
		
				case SW: 
					box.x += dx;  // mueve en x 
					box.width -= dx;  // modifica en x 
						//owner.y += dy;  // mueve en y 
					box.height += dy; // modifica en y 
					break;				
		
				case W: 
					box.x += dx;	
					box.width -= dx;
					break;				
			}			}
		}else {
			resize(dx,dy);
		}
	}
	
public void resize( int dx, int dy ) {
		
		switch ( cardinal ) {
			case NW: 
			    owner.x += dx;  // mueve en x 
				owner.width -= dx;  // modifica en x 
				owner.y += dy;  // mueve en y 
			    owner.height -= dy; // modifica en y 
				break;
	
			case N: 
				owner.y += dy;	
				owner.height -= dy;
				break;
	
			case NE: 
				owner.y += dy;		
				owner.width += dx;
				owner.height -= dy;
				break;
	
			case E: 
				
				owner.width += dx;
				break;
	
			case SE: 
				//owner.y += dy;		
				owner.width += dx;
				owner.height += dy;
				//owner.y += dy;  // mueve en y
				break;
	
			case S: 
				owner.height += dy;
				break;
	
			case SW: 
				 owner.x += dx;  // mueve en x 
					owner.width -= dx;  // modifica en x 
					//owner.y += dy;  // mueve en y 
				    owner.height += dy; // modifica en y 
				break;				
	
			case W: 
				owner.x += dx;	
				owner.width -= dx;
				break;				
		}
	}
}
