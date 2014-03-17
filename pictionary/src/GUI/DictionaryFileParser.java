import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class DictionaryFileParser {
	
	private File answerFile;
	private Scanner input;
	private ArrayList<String> answers;
	private String thisAnswer;
	
	DictionaryFileParser(String pathname) throws FileNotFoundException{
		this.answerFile = new File(pathname);
		this.input = new Scanner(answerFile);
		this.answers = new ArrayList<String>();
	}
	
	public ArrayList<String> parse(){
		while(input.hasNext()){
			answers.add(input.nextLine());
		}
		input.close();
		return answers;
	}
	
	public String fetchAnswer(){
		int indexAnswer = (int) (Math.random() * answers.size());
		thisAnswer = answers.get(indexAnswer);
		return thisAnswer;
	}
	
	public boolean checkAnswer(String inputAnswer){
		return (inputAnswer.equals(thisAnswer));
	}
	
	
	

}
