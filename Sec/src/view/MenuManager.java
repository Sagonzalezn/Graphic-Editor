package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JToolBar;

import controller.App;
import model.DocumentListener;
import view.ToolListener.ToolEvent;

public class MenuManager extends JMenuBar implements DocumentListener  {
	
	private Canvas canvas;
	private JToolBar toolBar;
	private static MenuManager barraDeMenu;
	
	private MenuManager(){
		init();
	}
	
    public static MenuManager getInstance(){
    	
    	if(barraDeMenu==null) {
    		barraDeMenu= new MenuManager();
    	}
    	
    	return barraDeMenu;
    }
    
	
	
	public void init() {
		
		App.getInstance().addListener(this);
		// INICIALIZAR LO ITEMS DEL MENU DE OPCIONES
		JMenu menuFile = new JMenu("File");
		JMenu menuTool = new JMenu("Tool");
		JMenu menuHelp = new JMenu("Help");
	    add(menuFile);
	    add(menuTool);
	    add(menuHelp);
	    
	    JMenuItem newf = new JMenuItem("new");
	    menuFile.add(newf);
	    JMenuItem load = new JMenuItem("load");
	    menuFile.add(load);
	    JMenuItem save = new JMenuItem("save");
	    menuFile.add(save);
	    menuFile.add(new JSeparator()); // SEPARATOR
	    JMenuItem exit = new JMenuItem("exit");
	    menuFile.add(exit);
	   
	   
	    
	    exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().windowDispose();
			}
		});
	    
	    newf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().clearDocument();
			}
		});
	    
	    save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().saveDocument();
			}
		});
	    
	    load.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				App.getInstance().loadDocument();
			}
		});
	    
	    JMenuItem miSel = new JMenuItem( "Selection" );
		miSel.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					App.getInstance().getCanvas().setActiveTool(
						Canvas.SELT
					);
				}
			}
		);
	    
	    JMenuItem bcolorTool = new JMenuItem("Border Color Tool");
	    menuTool.add(bcolorTool);
	    menuTool.add(new JSeparator()); // SEPARATOR
	    JMenuItem colorTool = new JMenuItem("Fill Color Tool");
	    menuTool.add(colorTool);
	    menuTool.add(new JSeparator()); // SEPARATOR
	    JMenuItem opcionNuevaLinea= new JMenuItem("Line creation");
	    menuTool.add(opcionNuevaLinea);
	    menuTool.add(new JSeparator()); // SEPARATOR
	    JMenuItem opcionNuevoRect = new JMenuItem("Rectangle creation");
	    menuTool.add(opcionNuevoRect);
	    menuTool.add(new JSeparator()); // SEPARATOR
	    JMenuItem opcionNuevaEllipse = new JMenuItem("Ellipse creation");
	    menuTool.add(opcionNuevaEllipse);
	    menuTool.add( new JSeparator() );		
		menuTool.add( miSel );
	    
	    bcolorTool.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
			
				App laApp= App.getInstance();
		        Color elColor = JColorChooser.showDialog(null, "JColorChooser Sample", laApp.getColorSegundario());
		        laApp.setColorSegundario(elColor);
				
			}
		});
	    

	    colorTool.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
			
				App laApp= App.getInstance();
		        Color elColor = JColorChooser.showDialog(null, "JColorChooser Sample", laApp.getCurrentColorPrincipal());
		        laApp.setColorPrincipalFigure(elColor);
				
			}
		});
	    
	    opcionNuevaLinea.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				App.getInstance().setActiveTool(Canvas.CLIN );
			}
			
			
		});
	    
	    opcionNuevoRect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				App.getInstance().setActiveTool(Canvas.CREC );
			}
		});
	    
	    opcionNuevaEllipse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				App.getInstance().setActiveTool(Canvas.CELI );
			}
		});
	    
	    menuHelp.add(new JMenuItem("About"));
	    
	}
	
	

	@Override
	public void documentChange(DocumentEvent de) {
		
		System.out.println("MenuManager:he sido notificado.. )");
		
	}
	
	
	
	
}
