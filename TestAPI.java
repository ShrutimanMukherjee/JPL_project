import java.util.Collection;
import java.util.ArrayList;
import quizlib.QuizDB;
public class TestAPI
{
	public static void main(String args[])
	{
		QuizDB qobj = new QuizDB();
		qobj.runQuery("SELECT * FROM course");
		System.out.println("Testing resultDisplay:");
		qobj.resultDisplay();
		
		System.out.println("\nTesting getResult:");
		ArrayList<ArrayList<String>> result = qobj.getResult();
		for(ArrayList<String> row : result)
		{
			for(String data : row)
				System.out.print(data+"\t|\t");
			System.out.println();	
		}
	}
}