package controller;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.ControlPoint;
import model.DocumentListener;
import model.Drawing;
import model.Figure;
import view.Canvas;
import view.MainFrames;

public final class App {
	private App() {
		model = new Drawing();

		view = new MainFrames("EG v0.001");
	}

	public static App getInstance() {
		if (app == null) {
			app = new App();
		}
		return app;
	}

	public static void main(String[] args) {
		App app = App.getInstance();
		app.run();
	}

	private void run() {
		view.init();
		view.setVisible(true);
		
	}

	public void paint(Graphics2D g) {
		model.paint(g);
	}

	public void addFigure(Figure f) {
		model.addFigure(f);
		view.repaint();

	}
	
	public void select(Point pt) {
		model.select( pt );
		
		updateView();
		
		boolean b = true; // TODO
		view.setFillColorEnabled(
			b
		);
	}

	private void updateView() {
		view.repaint();
		
	}

	public void select(Point ptp, Point ptr) {
		model.select( ptp, ptr );
		
		updateView();
		
		boolean b = true; // TODO
		view.setFillColorEnabled(
			b
		);
	}
	

	public void cambiarCursorANormal() {

		view.setCursor(Cursor.DEFAULT_CURSOR);
	}

	public void setFiguraEnConstruccion(Figure figura) {

		model.setFiguraEnConstruccion(figura);
		view.repaint();
	}

	public Color getCurrentColor() {
		return this.colorDeLinea;
	}

	public void setColorFigure(Color colorDeLinea) {
		this.colorDeLinea = colorDeLinea;
	}

	public float getCurrentThickness() {

		return 4;
	}

	public float[] getLineStyle() {

		return null;
	}

	public void setActiveTool(int tool) {
		view.setActiveTool(tool);

	}

	public void addListener(DocumentListener dl) {
		model.addListener(dl);
	}

	public void saveDocument() {
		String docName = model.getName();//debe ser el path absoluto del fichero
		if (docName == null) {// se guarda por primera vez
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setSelectedFile(new File(""));
			FileNameExtensionFilter filter = new FileNameExtensionFilter("DWF", "dwf");
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int returnValue= fileChooser.showSaveDialog(null);
			
			
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				docName = fileChooser.getSelectedFile().getAbsolutePath();
			}
			
			
		}

		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(docName)));
			if (model.save(oos)) {

			} else {
				// ERROR
			}
			model.save(oos);
			oos.close();
//			oos.defaultWriteObject(figures);
		} catch (Exception e) {
			System.err.println(e.toString());
		}

	}

	public void loadDocument() {
		File selectedFile = null;
		String docName = model.getName();
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setSelectedFile(new File(""));
		fileChooser.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("DWF", "dwf");
		fileChooser.addChoosableFileFilter(filter);
		int returnValue = fileChooser.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {

			selectedFile = fileChooser.getSelectedFile();
			System.out.println("we selected: " + selectedFile);

			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(selectedFile));
				if (model.load(ois)) {

				} else {

				}
				model.load(ois);
				ois.close();
//	    			ois.defaultWriteObject(figures);
			} catch (Exception e) {
				System.err.println(e.toString());
			}

		}

	}
	
	public Figure getSelectedFigure( Point pt ) {
		return model.getSelectedFigure( pt );
	}

	public int numFigures() {
		return model.numFigures();
	}

	public Color getCurrentColorPrincipal() {
		return this.colorPrincipal;
	}

	public void setColorPrincipalFigure(Color colorPrincipal) {
		this.colorPrincipal = colorPrincipal;
	}

	public Color getColorSegundario() {
		return colorSegundario;
	}

	public void setColorSegundario(Color colorSegundario) {
		this.colorSegundario = colorSegundario;
	}

	public void clearDocument() {
		model.clearDocument();
		view.repaint();
	}
	
	public void deselectAll() {
		model.deselectAll();
		view.repaint();
	}

	public Cursor getCursor( Point pt ) {
		return model.getCursor( pt );
	}
	
	public void resizeFigure( ControlPoint cp, int dx, int dy ) {
		model.resizeFigure( cp, dx, dy );
		updateView();		
	}

	
	public Canvas getCanvas() {
		return view.getCanvas();
	}

	public Graphics2D getGraphics() {
		return view.getCanvasGraphics();
	}

	public void releaseGraphics(Graphics2D g) {
		view.releaseCanvasGraphics( g );
		
	}

	public void moveFigure( Figure f, int dx, int dy ) {
		model.moveFigure( f, dx, dy );
		updateView();
	}

	public ControlPoint getSelectedCtrlPoint( Point pt ) {
		return model.getSelectedCtrlPoint( pt );
	}

	public void windowDispose() {
		view.windowDispose();
		
	}
	
	private static App app;
	private Drawing model;
	private MainFrames view;
	private Color colorDeLinea = Color.BLACK;
	private Color colorPrincipal = Color.LIGHT_GRAY;// el que vamos a usar para rellenar
	private Color colorSegundario = Color.BLACK;// para la linea
	
	
	

}
