package model;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

import model.DocumentListener.DocumentEvent;

public class Drawing {

	public Drawing() {
		figures = new LinkedList<Figure>();
		listeners = new LinkedList<DocumentListener>();
	}
	
	public void addListener(DocumentListener dl) {
		listeners.add(dl);
	}
	
	public void removeListener(DocumentListener dl) {
		listeners.add(dl);
	}
	
	public void addFigure( final Figure f ) {
		if (f == null) {
			//NOOP (No Operation)
		}
		else {
			figures.add(f);
			notifyListeners(DocumentEvent.ADD_FIGURE);
		}
	}
	
	
	public void paint( Graphics2D g ) {
		for (Figure f : figures) {
			f.paint(g);
		}
		
		if(this.pintandoActualmente!=null) {
			this.pintandoActualmente.paint(g);
		}
		
	}
	
	 public void setFiguraEnConstruccion(Figure f) {
		 this.pintandoActualmente = f;
	 }
	
	 private void notifyListeners(DocumentEvent event) {
			
			for(DocumentListener e:listeners) {
				e.documentChange(event);
			}
			
	 }
	 
	 public boolean save(ObjectOutputStream oos) throws IOException {
		 try {
			oos.writeObject(figures);
			notifyListeners(DocumentEvent.SAVED);
			return true;
		} catch (IOException e) {
			return false;
		}
		 
	 }
	 
	 public boolean load(ObjectInputStream ois)  {
		 try {
			figures = (List<Figure>)ois.readObject();
			notifyListeners(DocumentEvent.LOADED);
			return true;
		} catch (Exception e) {
//			??
			return false;
		}
		 
	 }
	 
	 public Figure getSelectedFigure( Point pt ) {
			Figure figure = null;
			
			for ( int c = figures.size() - 1;
					c >= 0;
					c-- ) {
				
					Figure f = figures.get( c );
					
					if ( f.isSelected() ) {
						if ( f.contains( pt ) ) {
							figure = f;
							break;
						}
					}
					else {
						// skip it
					}
				}
			
			return figure;
		}
	 

		public void deselectAll() {
			
			for ( int c = figures.size() -1;c >= 0;c-- ) {
			
				Figure f = figures.get( c );
				 
				f.setSelected( false );
			}				
		}
	 
	 public void select(Point pt) {
			for ( int c = figures.size() - 1;
				c >= 0;
				c-- ) {
			
				Figure f = figures.get( c );
				
				f.select( pt );
				
				if ( f.isSelected() ) {
					break;
				}
			}
		}

	 public void select( Point ptp, Point ptr ) {
			BoundBox bbox = new BoundBox( 
				ptp, 
				ptr 
			);
			
			bbox.normalize();

			for ( int c = figures.size() - 1;
				c >= 0;
				c-- ) {
			
				Figure f = figures.get( c );
							
				f.select( bbox );
			}
		}
	 
	public int numFigures() {
			return figures.size();
		}
	 
	public void clearDocument() {
		figures.clear();
		pintandoActualmente = null;
		
		}
	
	public String getName() {
		return name;
	}
	
	public Cursor getCursor( Point pt ) {
		Cursor cursor = null;
		
		for ( int c = figures.size() - 1;
			c >= 0;
			c-- ) {
		
			Figure f = figures.get( c );
			
			if ( f.isSelected() ) {
				cursor = f.getCursor(pt);
				
				if ( cursor != null ) {
					break;
				}
			}
			else {
				// skip it
			}
		}
		
		return cursor;
	}
	
	private List<Figure> figures;
	private Figure pintandoActualmente;
	private transient List<DocumentListener> listeners;
	private transient String name;
	
	public void moveFigure( Figure f, int dx, int dy ) {
		f.move(  dx, dy );
	}

	public void resizeFigure( ControlPoint cp, int dx, int dy) {
		cp.resizeFiguras( dx, dy );

	}

	public ControlPoint getSelectedCtrlPoint( Point pt ) {
		ControlPoint cp = null;
		
		for ( int c = figures.size() -1;
				c >= 0;
				c-- ) {
			
				Figure fig = figures.get( c );
				
				if ( fig.isSelected() ) {
					cp = fig.getControlPoint( pt );
					
					if ( cp == null ) {
						// NOOP
					}
					else {
						break;
					}
				}
				else {
					// skip it
				}
		}
		
		return cp;
	}
	
	

}
