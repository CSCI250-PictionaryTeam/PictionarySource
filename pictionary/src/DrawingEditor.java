

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


@SuppressWarnings("serial")
public class DrawingEditor extends JPanel {
	private JLayeredPane lPane;
	private MousePencil c;
	private DrawingPanel view;
	private JButton clear, draw, erase;
	private JLabel whatToDraw;
	
	private Drawing d() {return view.getDrawing();}
	
	private Drawing makeNewDrawing() {return new Drawing(90, 90);}
	
	public DrawingEditor() throws IOException {
		lPane = new JLayeredPane();
		this.setPreferredSize(new Dimension(700, 700));
		
		this.setLayout(new BorderLayout());
		
		view = new DrawingPanel(makeNewDrawing());
		view.setBounds(0, 0, 700, 700);
		c = new MousePencil(view);
		
		this.add(lPane, BorderLayout.CENTER);
		lPane.add(view, new Integer(0), 0);
		
		addViewByLabel();
		
		whatToDraw = new JLabel("Haunted House");
		whatToDraw.setOpaque(false);
		whatToDraw.setBorder(BorderFactory.createLineBorder(Color.black));
		whatToDraw.setBounds(100, 0, 100, 20);
		lPane.add(whatToDraw, new Integer(1), 0);
	}
	private void addViewByLabel() throws IOException {
		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));
		dataPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
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
		erase.setContentAreaFilled(false);
		
		BufferedImage clearIcon = ImageIO.read(new File("black-white-metro-delete-icon.png"));
		clear = new JButton(new ImageIcon(clearIcon));
		clear.setBorder(BorderFactory.createEmptyBorder());
		clear.addActionListener(new Clearer());
		clear.setToolTipText("Clear Screen");
		clear.setContentAreaFilled(false);
		
		dataPanel.add(draw);
		dataPanel.add(erase);
		dataPanel.add(clear);
		
		dataPanel.setBounds(0, 50, 32, 99);
				
		lPane.add(dataPanel, new Integer(1), 0);
	}	
	
	public static void main(String[] args) throws IOException {
		DrawingEditor gui = new DrawingEditor();
		gui.setVisible(true);
	}
	
			
	private class Clearer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			view.resetDrawing(makeNewDrawing());
		}
	}
	
//	private class Swapper implements ActionListener {
//		public void actionPerformed(ActionEvent e) {
//			changeIndexedDrawing();
//		}
//	}
	
	private class Eraser implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			c.erase();
			erase.setContentAreaFilled(true);
			erase.setBackground(Color.yellow);
			draw.setBackground(Color.white);
			draw.setContentAreaFilled(false);
		}
	}	
	
	private class Drawer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			c.draw();
			draw.setContentAreaFilled(true);
			draw.setBackground(Color.yellow);
			erase.setBackground(Color.white);
			erase.setContentAreaFilled(false);
		}
	}
		
}
