package GUI;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class ScoreBoardPanel extends JPanel{
	private HashMap<String, Integer> scoreMap;
	public ScoreBoardPanel(){
		scoreMap = new HashMap<String, Integer>();
		scoreMap.put("player1", 50);
		scoreMap.put("player2", 50);
		scoreMap.put("player3", 50);
		scoreMap.put("player4", 50);
		
		setLayout(new GridLayout(0, scoreMap.size()/2));
		
		Set<String>userSet = scoreMap.keySet();
		ArrayList<String> userList = new ArrayList<String>(userSet);
		
		for (int i = 0; i < userList.size(); i++){
			JLabel toAdd = new JLabel("<html>" + userList.get(i) + "<br><center>" + scoreMap.get(userList.get(i)) + "</center></html>", JLabel.CENTER);
			toAdd.setBorder(BorderFactory.createLineBorder(Color.black));
			add(toAdd);
		}
	}
	
	public void setScore(String playerName, int score){
		scoreMap.put(playerName, score);
	}
}
