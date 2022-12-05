import quizlib.QuizDB;
import java.util.ArrayList;

public class Question
{
	private String id;
	private String test_id;
	private String qstr;
	private String optionA;String optionB;String optionC;String optionD;
	private char correct;
	
	public Question(String id, String test_id, String qstr, String opA, String opB, String opC, String opD, char correct)
	{
		this.id = new String(id);
		this.test_id = new String(test_id);
		this.qstr = new String(qstr);
		optionA = new String(opA);
		optionB = new String(opB);
		optionC = new String(opC);
		optionD = new String(opD);
		this.correct = correct;
	}
	
	public void push2db()
	{
		QuizDB qobj = new QuizDB();
		qobj.runQuery("insert into question values('"+id+"', '"+test_id+"', '"+qstr+"', '"+optionA+"', '"+optionB+"', '"+optionC+"', '"+optionD+"', '"+correct+"')");
	}
	
	public void display()
	{
		System.out.println("----------- Question ---------------");
		System.out.println("Question ID : "+id);
		System.out.println("Question : "+qstr);
		System.out.println("A : "+optionA);
		System.out.println("B : "+optionB);
		System.out.println("C : "+optionC);
		System.out.println("D : "+optionD);
	}
	
	public boolean checkAns(char attempt)
	{
		if(attempt==correct)
			return true;
		else
			return false;
	}
	
	public static void main(String args[]) //Test the class
	{
		Question q = new Question("DemoT1_1","DemoT1","Which is not a part of OOP","Encapsulation","Polymorphism","Inheritance","Recursion",'D');
		q.push2db();
		q.display();
		System.out.println(q.checkAns('A'));
		System.out.println(q.checkAns('D'));
	}
}
