package view;


import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import controller.App;
import view.MenuManager;
import view.ToolListener.ToolEvent;
import model.DocumentListener;

public class MainFrames extends JFrame implements ToolListener,DocumentListener{
	
	private Canvas canvas;
	private String title;
	private JMenuItem miFil;
	private JToolBar toolBar;
	private int docC = 0;
	private transient List<ToolListener> Toollisteners;
	
	public MainFrames(String title) throws HeadlessException {
		super(title);
		canvas = new Canvas();
		this.title = title;
		
		toolBar = new JToolBar(
				JToolBar.VERTICAL
			);
	
	}
	
	private void addToolBar() {
		JToggleButton sel = new JToggleButton( 
			"Selection" 
		);
		sel.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					canvas.setActiveTool(
						Canvas.SELT
					);
				}
			}
		);

		JToggleButton lin = new JToggleButton( 
			"Line Creation" 
		);
		lin.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					canvas.setActiveTool(
						Canvas.CLIN
					);
				}
			}
		);

		JToggleButton rec = new JToggleButton( 
			"Rect Creation" 
		);
		rec.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					canvas.setActiveTool(
						Canvas.CREC
					);
				}
			}
		);

		JToggleButton eli = new JToggleButton( 
			"Ellipse Creation" 
		);
		eli.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					canvas.setActiveTool(
						Canvas.CELI
					);
				}
			}
		);
		
		ButtonGroup group = new ButtonGroup();
		group.add( sel );
		group.add( lin );
		group.add( rec );
		group.add( eli );
		
		toolBar.add( 
			sel
		);
		toolBar.add( 
			lin 
		);
		toolBar.add( 
			rec
		);
		toolBar.add( 
			eli
		);

		canvas.setActiveTool( Canvas.CELI );
		eli.setSelected( true );
		
		add( 
			toolBar, BorderLayout.EAST
		);
	}
	
	
	
	public Canvas getCanvas() {
		return canvas;
	}

	public void init() {
		setBounds(100, 100, 640, 480);
//		setExtendedState(MAXIMIZED_BOTH);
		addToolBar();
		App.getInstance().addListener(this);
		canvas.init();
		setJMenuBar(MenuManager.getInstance());
		add(canvas, BorderLayout.CENTER);
		
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			this.addWindowListener(
				
				new WindowAdapter() {
				
				@Override
				public void windowClosing(WindowEvent e) {
					if (docC == 1) {
					exit();
					}
					else {
						windowDispose();
					}
				}
				
			});
		} 
		
	

	public void exit() {
		int a = JOptionPane.showConfirmDialog(null, "Do you want to save changes to the document?", "Warning" , JOptionPane.YES_NO_OPTION);
		if (a==JOptionPane.YES_OPTION) {
			
			App.getInstance().saveDocument();
		}
		if (a==JOptionPane.NO_OPTION) {
			
			this.dispose();
		}
	}
	

	public void setActiveTool(int tool) {
		canvas.setActiveTool(tool);
		
	}
	
	public void setFillColorEnabled( boolean b ) {
		;//miFil.setEnabled( b );
	}


	@Override
	public void documentChange(DocumentEvent event) {
		System.out.println("Document Change");
		if(event.name().equals("SAVED")) {
		setTitle(
				title
				);
			docC = 0;
	    }else {
	    	setTitle(
	    	title + "*"
	    );
	    	docC = 1;
	    }
	}
	
	public void windowDispose() {
		this.dispose();
	}

	public Graphics2D getCanvasGraphics() {
		return (Graphics2D)canvas.getGraphics();
	}

	public void releaseCanvasGraphics(Graphics2D g) {
		g.dispose();
	}
	
	private void notifyToolListeners(ToolEvent event) {
		
		for(ToolListener e:Toollisteners) {
			e.toolChange(event);
		}
		
  }
	
	@Override
	public void toolChange(ToolEvent e) {
		if(e.name().equals("ClIN")) { 
			
		}
		if(e.name().equals("CREC")) { 		
			
		}
		if(e.name().equals("CELI")) { 	
			
		}
		if(e.name().equals("CELT")) { 	
			
		}
			
	}
	
	}
	

