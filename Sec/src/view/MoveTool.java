package view;

import java.awt.event.MouseEvent;

import controller.App;
import model.Figure;

public class MoveTool extends FTool {
	
	private Figure fMove;
	
	@Override
	public void mouseDragged( MouseEvent e ) {
		int dx = e.getPoint().x - ptPressed.x;
		int dy = e.getPoint().y - ptPressed.y;
		
		if ( fMove == null ) {
			// NOOP
		}
		else {
			App.getInstance().moveFigure( fMove, dx, dy );
		}
		
		ptPressed = e.getPoint();
 	}

	@Override
	protected void processMouseReleased() {
		fMove = null;
	}

	public void setFigure( Figure f ) {
		fMove = f;
	}

	@Override
	public void showFeedback(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}    
}
