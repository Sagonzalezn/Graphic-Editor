package view;

import java.awt.Cursor;
import java.awt.event.MouseEvent;

import controller.App;
import model.Figure;

public abstract class CreationTool extends FTool {

	protected abstract Figure createFigure();

	public CreationTool() {
		// TODO Auto-generated constructor stub
	}


	@Override
	protected void processMouseReleased() {
		if (ptReleased.equals(ptPressed)) {
			// NOOP
		} else {
			Figure f = createFigure();
			if (f == null) {
				// NOOP user cancelled
			} else {
				App.getInstance().addFigure(f);
				
			}
		}

	}

//	@Override
//	public void mouseDragged(MouseEvent e) {
//		super.mouseDragged(e);
//		
//		if (ptDxMouse.equals(ptPressed)) {
//			// NOOP
//		} else {
//			Figure f = createFigure();
//			if (f == null) {
//				// NOOP user cancelled
//			} else {
//				App.getInstance().setFiguraEnConstruccion(f);
//			}
//		}
//	}

}
