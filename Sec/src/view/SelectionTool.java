package view;

import java.awt.event.MouseEvent;

import controller.App;

public class SelectionTool extends FTool{
	
	@Override
	protected void processMouseReleased() {
		if ( ptPressed.equals( ptReleased ) ) {
			App.getInstance().select( ptPressed );
		}
		else {
			App.getInstance().select( ptPressed, ptReleased );
		}
	}

	@Override
	public void showFeedback(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
