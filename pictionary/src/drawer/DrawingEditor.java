package drawer;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


@SuppressWarnings("serial")
public class DrawingEditor extends JFrame {
	private MousePencil pen;
	private Canvas view;
	String Update;
	private JButton clear, drawErase, red, blue, green, black, white;
		
	public DrawingEditor(boolean drawer) {
		setSize(700, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setLayout(new BorderLayout());
		
		view = new Canvas();
		if(drawer == true){
			pen = new MousePencil(view, this);
			addViewByLabel();
		}
		//IF NOT TRUE WE NEED TO BUILD AN UPDATE LISTENER WITH VIEW
		this.add(view, BorderLayout.CENTER);
		
	}
		
	public static void main(String[] args) {
		DrawingEditor gui = new DrawingEditor(true);
		gui.setVisible(true);
	}
	
			
	private class Clearer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			view.clear();
			pen.draw();
			drawErase.setText("Erase");
		}
	}
	
	private void addViewByLabel() {

		JPanel dataPanel = new JPanel();
		dataPanel.setBorder(BorderFactory.createBevelBorder(0));
				
		clear = new JButton("Clear");
		clear.addActionListener(new Clearer());
		dataPanel.add(clear);
		
		drawErase = new JButton("Erase");
		drawErase.addActionListener(new DrawEraser());
		dataPanel.add(drawErase);
		
		black = new JButton (" ");
		black.setBackground(Color.black);
		black.addActionListener(new changeColorBlack());
		dataPanel.add(black);
		
		red = new JButton (" ");
		red.setBackground(Color.RED);
		red.addActionListener(new changeColorRed());
		dataPanel.add(red);
			
		view.add(dataPanel, BorderLayout.NORTH);
	}
		
	private class DrawEraser implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (pen.isDrawing()) {
				drawErase.setText("Draw");
				pen.erase();
			} else {
				drawErase.setText("Erase");
				pen.draw();
			}
			
		}
	}
	MousePencil getPencil(){
		return pen;
	}
	private class changeColorBlack implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			pen.c = Color.BLACK;
		}		
	}
	private class changeColorRed implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			pen.c = Color.RED;
		}		
	}

}
