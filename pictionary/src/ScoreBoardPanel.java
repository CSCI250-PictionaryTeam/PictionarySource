import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ScoreBoardPanel extends JPanel{
	private ArrayList<String> userList;
	private ArrayList<Integer> scoreList;
	public ScoreBoardPanel(){
		userList = new ArrayList<String>();
		userList.add("player1");
		userList.add("player2");
		userList.add("player3");
		userList.add("player4");
		
		scoreList = new ArrayList<Integer>();
		scoreList.add(50);
		scoreList.add(50);
		scoreList.add(50);
		scoreList.add(50);
		
		setLayout(new GridLayout(0, userList.size()/2));
		
		for (int i = 0; i < userList.size(); i++){
			JLabel toAdd = new JLabel("<html>" + userList.get(i) + "<br><center>" + scoreList.get(i) + "</center></html>", JLabel.CENTER);
			toAdd.setBorder(BorderFactory.createLineBorder(Color.black));
			add(toAdd);
		}
	}
}
