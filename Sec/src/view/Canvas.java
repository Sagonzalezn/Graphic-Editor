package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.security.InvalidParameterException;
import java.util.List;

import javax.swing.JPanel;

import controller.App;
import model.ControlPoint;
import model.DocumentListener;
import model.Figure;

public class Canvas extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener, DocumentListener {

	public static final int CLIN = 0;
	public static final int CREC = 1;
	public static final int CELI = 2;
	public static final int CTXT = 3;
	public static final int SELT = 4;
	public static final int MOV_TOOL = 5;
	public static final int RES_TOOL = 6;
	public static final int NTOOLS = RES_TOOL + 1;
	
	private transient List<ToolListener> Toollisteners;

	// comportamiento de los arreglos en java:
	// 1)Declarar
	// 2)Construir
	// 3)Llenar
	private FTool[] tools;
	private FTool activeTool;

	public Canvas() {
		setBackground(Color.LIGHT_GRAY.brighter());

		// 2. creation
		tools = new FTool[NTOOLS];

		// 3. fill
		tools[CLIN] = new LineCreationTool();
		tools[CREC] = new RectCreationTool();
		tools[SELT] = new SelectionTool();
		tools[CELI] = new EllipseCreationTool();
		tools[MOV_TOOL] = new MoveTool();
		tools[ RES_TOOL ] = new ResizeTool();
		// etc.
		setActiveTool(SELT);

		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
	}
	
	public void init() {
		App.getInstance().addListener(this);
	}

	public void setActiveTool(int idx) throws InvalidParameterException {

		if (0 <= idx && idx < tools.length) {
			activeTool = tools[idx];
		} else {
			throw new InvalidParameterException("Invalid tool index");
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		App.getInstance().paint((Graphics2D) g);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		activeTool.mouseDragged(e);

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		Cursor cursor = App.getInstance().getCursor(
				e.getPoint()
			);
			
			if ( cursor == null ) {
				setCursor( Cursor.getDefaultCursor() );
			}
			else {
				setCursor( cursor );
			}
		
		activeTool.mouseMoved(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		ControlPoint cp = App.getInstance().getSelectedCtrlPoint( e.getPoint() );
		if ( cp == null ) {
		Figure f = App.getInstance().getSelectedFigure(
				e.getPoint()
			);
			
			if ( f == null ) {
				App.getInstance().deselectAll();
			}
			else {
				setActiveTool(
					MOV_TOOL
				);
				
				((MoveTool)activeTool).setFigure( 
					f 
				);
			}
		}else {
			setActiveTool( RES_TOOL );
			activeTool.setControlPoint( cp );						
		}
		
		activeTool.mousePressed(e);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		if ( activeTool == tools[ MOV_TOOL ] ) { 
			
			((MoveTool)activeTool).setFigure(null );
			
			setActiveTool( SELT );
		}

		activeTool.mouseReleased(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void documentChange(DocumentEvent de) {
		
		System.out.println("Canvas:he sido notificado..");
		if(de.name().equals("SAVED")) {
			//NOOP
		}else {
			repaint();	
		}
		
	}
	
	public void addToolListener(ToolListener dl) {
		Toollisteners.add(dl);
	}

	

}
