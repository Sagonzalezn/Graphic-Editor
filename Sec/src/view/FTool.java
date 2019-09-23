package view;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import model.ControlPoint;
import model.Figure;

public abstract class FTool extends MouseAdapter {

	public abstract void showFeedback(MouseEvent e);
	protected abstract void processMouseReleased();

	protected Point ptPressed;
	protected Point ptDxMouse;
	protected Point ptReleased;
	private Figure fMove;
	protected ControlPoint cpRes;

	public FTool() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		ptDxMouse = e.getPoint();
		showFeedback(e);

	}

	@Override
	public void mousePressed(MouseEvent e) {
		ptPressed = e.getPoint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		ptReleased = e.getPoint();

		processMouseReleased();
	}

	public void setFigure(Figure f) {
		fMove = f;
	}

	public void setControlPoint(ControlPoint cp) {
		cpRes = cp;
	}


}
