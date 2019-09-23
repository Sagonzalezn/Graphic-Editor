package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

import controller.App;
import model.BoundBox;
import model.Figure;
import model.Rectangle;

public class RectCreationTool extends CreationTool {

	@Override
	protected Figure createFigure() {
		Rectangle rect=null ;
		if(ptPressed.x < ptDxMouse.x ) {
			if(ptPressed.y<ptDxMouse.y) {
				rect= new Rectangle( 
						ptPressed, 
						new Dimension(
							Math.abs(ptDxMouse.x - ptPressed.x), 
							Math.abs(ptDxMouse.y - ptPressed.y) ),
							App.getInstance().getColorSegundario(),
							App.getInstance().getCurrentColorPrincipal(),
							App.getInstance().getCurrentThickness(),
							App.getInstance().getLineStyle()
						);
			}else {
				rect= new Rectangle( 
						(new Point(ptPressed.x,ptDxMouse.y)), 
						new Dimension(
							Math.abs(ptDxMouse.x - ptPressed.x), 
							Math.abs(ptDxMouse.y - ptPressed.y) ),
							App.getInstance().getColorSegundario(),
							App.getInstance().getCurrentColorPrincipal(),
							App.getInstance().getCurrentThickness(),
							App.getInstance().getLineStyle()
						);
			}
			
			
		}else {
			if(ptPressed.y<ptDxMouse.y) {
				rect= new Rectangle( 
						(new Point(ptDxMouse.x,ptPressed.y)), 
						new Dimension(
							Math.abs(ptDxMouse.x - ptPressed.x), 
							Math.abs(ptDxMouse.y - ptPressed.y)),
							App.getInstance().getColorSegundario(),
							App.getInstance().getCurrentColorPrincipal(),
							App.getInstance().getCurrentThickness(),
							App.getInstance().getLineStyle()
						);
			}else {
				rect= new Rectangle( 
						ptDxMouse, 
						new Dimension(
							Math.abs(ptDxMouse.x - ptPressed.x), 
							Math.abs(ptDxMouse.y - ptPressed.y) ),
							App.getInstance().getColorSegundario(),
							App.getInstance().getCurrentColorPrincipal(),
							App.getInstance().getCurrentThickness(),
							App.getInstance().getLineStyle()
						);
			}
			
		}
		
	
		
	
		
			return rect;
	}

	@Override
	public void showFeedback(MouseEvent me) {
		Graphics2D g = App.getInstance().getGraphics();
		
		float dash[] = {
				4.0f, 4.0f
		};
		BasicStroke dashed = new BasicStroke(
			1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f 
		);
		g.setStroke( dashed );

		g.setXORMode( Color.WHITE );
		
		if ( ptReleased == null ) ptReleased = me.getPoint();
		BoundBox bbox = new BoundBox( ptPressed, ptReleased );
		ptReleased = me.getPoint();

		g.drawRect( bbox.x, bbox.y, bbox.width, bbox.height );
		bbox = new BoundBox( ptPressed, ptReleased );
		g.drawRect( bbox.x, bbox.y, bbox.width, bbox.height );		
		
		App.getInstance().releaseGraphics( g );
	}

}
