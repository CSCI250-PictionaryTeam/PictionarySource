package drawer;


import javax.imageio.ImageIO;
import javax.swing.*;

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
	JPanel sideBar, whatToPanel;
	private Color defaultColor;
	
	
	//each panel in the drawing editor exists in a layeredPane, the canvas goes on bottom and the two other panels on top
	public DrawingEditor() throws IOException {
		lPane = new JLayeredPane();
		this.setPreferredSize(new Dimension(700, 700));
		
		this.setLayout(new BorderLayout());
		
		view = new Canvas();
		view.setBounds(0, 0, 700, 700);
		pen = new MousePencil(view,  this);
		
		this.add(lPane, BorderLayout.CENTER);
		lPane.add(view, new Integer(0), 0);
		
		addSideBar();
		
		whatToDraw = new JLabel("Haunted House");
		whatToPanel = new JPanel();
		whatToPanel.add(whatToDraw);
		whatToPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		whatToPanel.setBackground(Color.yellow);
		lPane.add(whatToPanel, new Integer(1), 0);
	}
	
	private void addSideBar() throws IOException {
		sideBar = new JPanel();
		sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
		sideBar.setBorder(BorderFactory.createLineBorder(Color.black));
		
		BufferedImage pencilIcon = ImageIO.read(new File("black-white-metro-pencil-icon.png"));
		draw = new JButton(new ImageIcon(pencilIcon));
		draw.setBorder(BorderFactory.createEmptyBorder());
		draw.addActionListener(new Drawer());
		draw.setToolTipText("Pencil");
		draw.setBackground(Color.yellow);
		
		BufferedImage eraserIcon = ImageIO.read(new File("Eraser-icon.png"));
		erase = new JButton(new ImageIcon(eraserIcon));
		erase.setBorder(BorderFactory.createEmptyBorder());
		erase.addActionListener(new Eraser());
		erase.setToolTipText("Eraser");
		
		BufferedImage clearIcon = ImageIO.read(new File("black-white-metro-delete-icon.png"));
		clear = new JButton(new ImageIcon(clearIcon));
		clear.setBorder(BorderFactory.createEmptyBorder());
		clear.addActionListener(new Clearer());
		clear.setToolTipText("Clear Screen");
		defaultColor = clear.getBackground();
		
		colorButtons = new ArrayList<JButton>();
		black = makeColorButton("black", Color.black);
		red = makeColorButton("red", Color.red);
		green = makeColorButton("green", Color.green);
		blue = makeColorButton("blue", Color.blue);
		yellow = makeColorButton("yellow", Color.yellow);
		black.setBorderPainted(true);
		colorButtons.add(black);
		colorButtons.add(red);
		colorButtons.add(green);
		colorButtons.add(blue);
		colorButtons.add(yellow);
		
		sideBar.add(draw);
		sideBar.add(erase);
		sideBar.add(clear);
		for (JButton b : colorButtons){
			sideBar.add(b);
		}
				
		lPane.add(sideBar, new Integer(1), 0);
	}	
	
	//call this method to move everything into place, should work with resizing
	public void update(){
		int textLen = whatToDraw.getText().length() * 8;
		whatToPanel.setBounds((this.getWidth() - textLen)/2, 0, textLen, 30);
		sideBar.setBounds(0, (int)(this.getHeight() * .25), 32, 258);
	}
	
	private JButton makeColorButton(String name, Color c) throws IOException{
		BufferedImage clearIcon = ImageIO.read(new File("Alpha32.png"));
		JButton toReturn = new JButton(new ImageIcon(clearIcon));
		toReturn.setBorder(BorderFactory.createLineBorder(Color.gray, 3));
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
			draw.setBackground(defaultColor);
		}
	}	
	
	private class Drawer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			pen.draw();
			draw.setBackground(Color.yellow);
			erase.setBackground(defaultColor);
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
					b.setBorder(BorderFactory.createLineBorder(Color.gray, 3));
				}else{
					b.setBorderPainted(false);
				}
			}
		}		
	}
	
	private class changeColorRed implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			pen.c = Color.RED;
		}		
	}
	
	public static void main(String[] args) throws IOException {
		DrawingEditor gui = new DrawingEditor();
		gui.setVisible(true);
	}
}

//old code that might be useful later
//
//private class Swapper implements ActionListener {
//	public void actionPerformed(ActionEvent e) {
//		changeIndexedDrawing();
//	}
//}
//
//private Drawing d() {return view.getDrawing();}
//
//private Drawing makeNewDrawing() {return new Drawing(90, 90);}
