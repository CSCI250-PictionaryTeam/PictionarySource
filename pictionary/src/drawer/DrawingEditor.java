package drawer;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class DrawingEditor extends JPanel {
	private JLayeredPane lPane;
	private MousePencil pen;
	private Canvas view;
	private JButton clear, draw, erase, black, red, green, blue, yellow;
	private ArrayList <JButton> colorButtons;
	private JLabel whatToDraw;
	private JPanel sideBar, whatToPanel;
	private boolean isDrawer;
	final static private Color DEFAULT_BUTTON_COLOR = new ColorUIResource(238, 238, 238);
	final static private Dimension CANVAS_PREFERRED_DIM = new Dimension(700, 700);
	final static private int COLOR_BORDER_WIDTH = 3;
	final static private Color COLOR_BORDER_COLOR = Color.GRAY;
	final static private int PIXELS_PER_CHAR = 10;
	final static private double TOP_LABEL_ALIGNMENT = .5;
	final static private double SIDEBAR_ALIGNMENT = .25;
	final static private int SIDEBAR_WIDTH = 32;
	final static private int SIDEBAR_HEIGHT = 258;
	final static private int TOP_LABEL_HEIGHT = 25;
	final static private String DRAW_FILENAME = "black-white-metro-pencil-icon.png";
	final static private String ERASER_FILENAME = "Eraser-icon.png";
	final static private String CLEAR_FILENAME = "black-white-metro-delete-icon.png";
	final static private String ALPHA_FILENAME = "Alpha32.png";
	
	
	
	//each panel in the drawing editor exists in a layeredPane, the canvas goes on bottom and the two other panels on top
	public DrawingEditor() throws IOException {
		this.setPreferredSize(CANVAS_PREFERRED_DIM);
		this.setLayout(new BorderLayout());

		lPane = new JLayeredPane();
		view = new Canvas();
		pen = new MousePencil(view, new ArrayList<String>());
		lPane.add(view, new Integer(0), 0);
		
		addSideBar();
		addWhatToPanel();
		
		this.add(lPane, BorderLayout.CENTER);
	}
	
	public DrawingEditor(ArrayList<String> connectionsList) throws IOException {
		this.setPreferredSize(CANVAS_PREFERRED_DIM);
		this.setLayout(new BorderLayout());

		lPane = new JLayeredPane();
		view = new Canvas();
		pen = new MousePencil(view, connectionsList);
		lPane.add(view, new Integer(0), 0);
		
		addSideBar();
		addWhatToPanel();
		
		this.add(lPane, BorderLayout.CENTER);
	}
	
	private void addWhatToPanel() {
		whatToDraw = new JLabel("You're Drawing: Haunted House");
		whatToPanel = new JPanel();
		whatToPanel.add(whatToDraw);
		whatToPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		whatToPanel.setBackground(Color.cyan);
		lPane.add(whatToPanel, new Integer(1), 0);
	}

	private void addSideBar() throws IOException {
		sideBar = new JPanel();
		sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
		sideBar.setBorder(BorderFactory.createLineBorder(Color.black));
		
		makeDrawButton();
		makeEraseButton();
		makeClearButton();
		makeColorButtons();
		assembleButtons();
		
		lPane.add(sideBar, new Integer(1), 0);
	}	

	private void assembleButtons() {
		sideBar.add(draw);
		sideBar.add(erase);
		sideBar.add(clear);
		for (JButton b : colorButtons){
			sideBar.add(b);
		}
	}

	private void makeColorButtons() throws IOException {
		colorButtons = new ArrayList<JButton>();
		black = makeColorButton("black", Color.black);
		red = makeColorButton("red", Color.red);
		green = makeColorButton("green", Color.green);
		blue = makeColorButton("blue", Color.blue);
		yellow = makeColorButton("yellow", Color.yellow);
		black.setBorderPainted(true);//this color starts as default
		colorButtons.add(black);
		colorButtons.add(red);
		colorButtons.add(green);
		colorButtons.add(blue);
		colorButtons.add(yellow);
	}

	private void makeDrawButton() throws IOException {
		draw = makeImagePressButton(DRAW_FILENAME);
		draw.addActionListener(new Drawer());
		draw.setToolTipText("Pencil");
		draw.setBackground(Color.yellow);//this button starts as default
	}
	private void makeEraseButton() throws IOException {
		erase = makeImagePressButton(ERASER_FILENAME);
		erase.addActionListener(new Eraser());
		erase.setToolTipText("Eraser");
		
	}
	private void makeClearButton() throws IOException {
		clear = makeImagePressButton(CLEAR_FILENAME);
		clear.addActionListener(new Clearer());
		clear.setToolTipText("Clear Screen");
	}

	private JButton makeImagePressButton(String filename) throws IOException {
		BufferedImage clearIcon = ImageIO.read(new File(filename));
		JButton toReturn = new JButton(new ImageIcon(clearIcon));
		toReturn.setBorder(BorderFactory.createEmptyBorder());
		return toReturn;		
	}

	//this updates all the dynamic components in this panel
	public void refresh(){
		view.setBounds(0, 0, this.getWidth(), this.getHeight());
		if (isDrawer){
			sideBar.setVisible(true);
			whatToPanel.setVisible(true);
			view.setEnabled(true);
			int textLen = whatToDraw.getText().length() * PIXELS_PER_CHAR;
			whatToPanel.setBounds((int)((this.getWidth() - textLen) * TOP_LABEL_ALIGNMENT), 0, textLen, TOP_LABEL_HEIGHT);
			sideBar.setBounds(0, (int)(this.getHeight() * SIDEBAR_ALIGNMENT), SIDEBAR_WIDTH, SIDEBAR_HEIGHT);
		}else{
			sideBar.setVisible(false);
			whatToPanel.setVisible(false);
			this.setEnabled(false);
		}
	}
	
	public void setWhatToDraw(String toDraw){
		whatToDraw.setText("You're Drawing: " + toDraw);
		refresh();
	}
	
	public void setDrawer(boolean isDrawer){
		this.isDrawer = isDrawer;
	}
	
	public Canvas getCanvas(){
		return view;
	}
	
	private JButton makeColorButton(String name, Color c) throws IOException{
		BufferedImage clearIcon = ImageIO.read(new File(ALPHA_FILENAME));
		JButton toReturn = new JButton(new ImageIcon(clearIcon));
		toReturn.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_COLOR, COLOR_BORDER_WIDTH));
		toReturn.setBorderPainted(false);
		toReturn.setBackground(c);
		toReturn.setName(name);
		toReturn.addActionListener(new changeColor());
		return toReturn;
	}
			
	private class Clearer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			view.clear();
		}
	}
	
	private class Eraser implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			pen.erase();
			erase.setBackground(Color.yellow);
			draw.setBackground(DEFAULT_BUTTON_COLOR);
		}
	}	
	
	private class Drawer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			pen.draw();
			draw.setBackground(Color.yellow);
			erase.setBackground(DEFAULT_BUTTON_COLOR);
		}
	}
	
	private class changeColor implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)e.getSource();
			String name = source.getName();
			if (name.equals("black")){
				pen.c = Color.black;
				setColorBorders("black");
			}else if(name.equals("red")){
				pen.c = Color.red;
				setColorBorders("red");
			}else if(name.equals("green")){
				pen.c = Color.green;
				setColorBorders("green");
			}else if(name.equals("blue")){
				pen.c = Color.blue;
				setColorBorders("blue");
			}else if(name.equals("yellow")){
				pen.c = Color.yellow;
				setColorBorders("yellow");
			}
		}

		private void setColorBorders(String name) {
			for (JButton b : colorButtons){
				if (b.getName().equals(name)){
					b.setBorderPainted(true);
					b.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_COLOR, COLOR_BORDER_WIDTH));
				}else{
					b.setBorderPainted(false);
				}
			}
		}		
	}
}

