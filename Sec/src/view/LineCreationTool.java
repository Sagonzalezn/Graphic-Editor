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
import model.Line;
import model.Rectangle;

public class LineCreationTool extends CreationTool {


	@Override
	protected Figure createFigure() {
		Line line=null ;
		if(ptPressed.x < ptDxMouse.x ) {
			if(ptPressed.y<ptDxMouse.y) {
				line= new Line( 
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
				line= new Line( 
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
				line= new Line( 
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
				line= new Line( 
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
		return line;
		
//		return new Line( 
//				ptPressed, 
//				new Dimension(
//					ptReleased.x - ptPressed.x, 
//					ptReleased.y - ptPressed.y ),
//					App.getInstance().getColorSegundario(),
//					App.getInstance().getCurrentColorPrincipal(),
//					App.getInstance().getCurrentThickness(),
//					App.getInstance().getLineStyle()
//				);
		
		
		
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

		g.drawLine( bbox.x, bbox.y, bbox.x + bbox.width, bbox.y + bbox.height );
		bbox = new BoundBox( ptPressed, ptReleased );
		g.drawLine( bbox.x, bbox.y, bbox.x + bbox.width, bbox.y + bbox.height);		
		
		App.getInstance().releaseGraphics( g );
	}
	
	
	
	
}
