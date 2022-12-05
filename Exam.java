import quizlib.QuizDB;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Exam
{
	String id;
	String test_name;
	String course_id;
	LocalDateTime start;
	LocalDateTime end;
	
	public Exam(String id, String tname, String cid, int syear, int smonth, int sdayOM, int shour, int smin, int ssec, int eyear, int emonth, int edayOM, int ehour, int emin, int esec)
	{
		this.id = id;
		this.course_id = cid;
		this.test_name = tname;
		start = LocalDateTime.of(syear, smonth, sdayOM, shour, smin, ssec);
		end = LocalDateTime.of(eyear, emonth, edayOM, ehour, emin, esec);
	}
	
	public void push2db()
	{
		String startstr = start.toString();
		String endstr = end.toString();
		
		startstr.replace('T','\s');
		endstr.replace('T','\s');
		
		QuizDB qobj = new QuizDB();
		qobj.runQuery("insert into test values('"+id+"', '"+test_name+"', '"+course_id+"', '"+startstr+"', '"+endstr+"')");
	}
	
	public void display()
	{
		System.out.println("------------- "+test_name+" -------------");
		System.out.println("Course : "+course_id);
		QuizDB qobj = new QuizDB();
		qobj.runQuery("select * from question, test where question.test_id = '"+this.id+"'");
		ArrayList<ArrayList<String>> result = qobj.getResult();
		for(int i=1; i<result.size(); i++)
		{
			ArrayList<String> curr = result.get(i);
			Question q = new Question(curr.get(0),curr.get(1),curr.get(2),curr.get(3),curr.get(4),curr.get(5),curr.get(6),curr.get(7).charAt(0));
			q.display();
		}
	}

	public ArrayList<Question> getQuestions()
	{
		ArrayList<Question> qlist = new ArrayList<Question>();
		qobj.runQuery("select * from question, test where question.test_id = '"+this.id+"'");
		ArrayList<ArrayList<String>> result = qobj.getResult();
		for(int i=1; i<result.size(); i++)
		{
			ArrayList<String> curr = result.get(i);
			Question q = new Question(curr.get(0),curr.get(1),curr.get(2),curr.get(3),curr.get(4),curr.get(5),curr.get(6),curr.get(7).charAt(0));
			qlist.add(q);
		}
	}
}
/*
	public static void main(String args[]) //Testing 
	{
		Exam e = new Exam("DemoT1", "Demo Test", "CS001", 2022,12,1,16,0,0, 2022,12,1,17,0,0);
		e.push2db();
		e.display();		
	}
*/