package GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class LobbyPanel extends JPanel{
	private JLabel myCompName;
	private JTextField connectionField;
	private JButton connect;
	private JTextPane connectedToPane;
	private JScrollPane connectedToScroll;
	
	
	public LobbyPanel(){
		this.setLayout(new MigLayout());
		
		try {
			myCompName = new JLabel(InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		myCompName.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Local Name"));
		
		connectionField = new JTextField(20);
		connect = new JButton("connect");
		connect.addActionListener(new Connect());
		connectedToPane = new JTextPane();
		connectedToPane.setEditable(false);
		connectedToPane.setPreferredSize(new Dimension(300, 200));
		connectedToScroll = new JScrollPane(connectedToPane);
		connectedToScroll.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Current Connections"));
		
		add(myCompName, "span 2, pushx, wrap");
		add(connectionField, "pushx, growx");
		add(connect, "wrap");
		add(connectedToScroll, "span 2, pushx, growx, pushy, growy");
	}
	
	private class Connect implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			connectionField.setText("");
		}
	}
	
	
	public static void main(String[] args){
		JFrame frame = new JFrame("Testing");
		frame.add(new LobbyPanel());
		frame.pack();
		frame.setVisible(true);
	}
}
