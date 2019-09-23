package view;

import java.awt.event.MouseEvent;

import controller.App;

public class ResizeTool extends FTool {

	@Override
	public void mouseDragged( MouseEvent e ) {
		int dx = e.getPoint().x - ptPressed.x;
		int dy = e.getPoint().y - ptPressed.y;
		
		if ( cpRes == null ) {
			// NOOP
		}
		else {
			App.getInstance().resizeFigure( cpRes, dx, dy );
		}
		
		ptPressed = e.getPoint();
	}

	@Override
	protected void processMouseReleased() {
		cpRes = null;
	}

	@Override
	public void showFeedback(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
