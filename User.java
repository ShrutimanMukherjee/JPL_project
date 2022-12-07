import quizlib.QuizDB;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.SQLException;

public abstract class User
{
	public static QuizDB qobj;
	protected long id;
	protected String name;
	protected String pwd;
	
	public User(long id, String name, String pwd)
	{
		this.pwd = pwd;
		this.name = name;
		this.id = id;
	}
	
	public static boolean userInDB(long id)
	{
		QuizDB qobj = new QuizDB();
		qobj.runQuery("select * from user_list where user_id="+id);
		ArrayList<ArrayList<String>> result = qobj.getResult();
		if(result.size()>1)
			return true;
		return false;
	}

	public void display()
	{
		System.out.println("########### User Details ###############");
		System.out.println("ID : "+id);
		System.out.println("Name : "+name);
	}
	
	public static boolean validate(long id, String pwd)
	{
		QuizDB qobj = new QuizDB();
		
		if(!userInDB(id))
		{
			System.out.println("User id cannot be found.");
			return false;
		}
		qobj.runQuery("select user_id, password from user_list where user_id="+id);
				
		ArrayList<ArrayList<String>> result = qobj.getResult();
		String real_pwd = (result.get(1)).get(1);
		if(real_pwd.equals(pwd))
			return true;
		else
		{
			System.out.println("Password is incorrect.");
			return false;
		}
	}
	
	public abstract void push2db();
	public abstract void viewMarks(String id_test);
	
	public static void signup()
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter user id: ");
		int user_id = sc.nextInt();
		System.out.print("Enter user name: ");
		String user_name = sc.nextLine(); user_name = sc.nextLine();
		System.out.print("Enter password: ");
		String user_pwd = sc.nextLine();
		//user_pwd = sc.nextLine();
		System.out.print("Enter user type: ");
		String user_type = sc.next();
		
		if(user_type.equals("teacher"))
		{
			System.out.print("Enter Course ID: ");
			String tchr_course_id = sc.nextLine(); tchr_course_id = sc.nextLine();
			Teacher t = new Teacher(user_id,user_name,user_pwd,tchr_course_id);
			t.push2db();
		}
		else if(user_type.equals("student"))
		{
			Student s = new Student(user_id,user_name,user_pwd);
			s.push2db();
		}
	}
}

class Teacher extends User
{
	public String type;
	private String course_id;

	public Teacher(long id, String name, String pwd, String course_id)
	{
		super(id,name,pwd);
		type = "teacher";
		this.course_id = course_id;
	}
	
	public void push2db()
	{
		QuizDB qobj = new QuizDB();
		qobj.runQuery("select * from user_list where user_id="+id);
		ArrayList<ArrayList<String>> result = qobj.getResult();
		if(result.size()>1)
			System.out.println("User ID already exists");
		else
			qobj.runQuery("insert into user_list values("+id+", '"+name+"', '"+pwd+"', '"+type+"','"+course_id+"')");
	}
	
	public void setTest()
	{
		Scanner sc = new Scanner(System.in);
		
		// Set the test metadata
		String id_test;
		while(true)
		{
			QuizDB qobj = new QuizDB();
			System.out.print("Set the test id: ");
			id_test = sc.nextLine();
			qobj.runQuery("select test_id from test where test_id='"+id_test+"'");
			ArrayList<ArrayList<String>> result = qobj.getResult();
			if(result.size()>1)
				System.out.println("Test ID already exists");
			else
				break;
		}
		System.out.print("Set the test name: ");
		String name_test = sc.nextLine();
		
		System.out.println("Set the start time: ");
		System.out.print("\t year = "); int year_start = sc.nextInt();
		System.out.print("\t month = "); int month_start = sc.nextInt();
		System.out.print("\t day of Month = "); int dom_start = sc.nextInt();
		System.out.print("\t hour = "); int hr_start = sc.nextInt();
		System.out.print("\t minute = "); int min_start = sc.nextInt();
		System.out.print("\t second = "); int sec_start = sc.nextInt();
		System.out.println("Set the end time: ");
		System.out.print("\t year = "); int year_end = sc.nextInt();
		System.out.print("\t month = "); int month_end = sc.nextInt();
		System.out.print("\t day of Month = "); int dom_end = sc.nextInt();
		System.out.print("\t hour = "); int hr_end = sc.nextInt();
		System.out.print("\t minute = "); int min_end = sc.nextInt();
		System.out.print("\t second = "); int sec_end = sc.nextInt();
		
		Exam e = new Exam(id_test, name_test, course_id, year_start,month_start,dom_start,hr_start,min_start,sec_start,year_end,month_end,dom_end,hr_end,min_end,sec_end);
		
		// Set the qestions
		System.out.print("Enter the number of questions: ");
		int n = sc.nextInt();		
		for(int i=0; i<n; i++)
		{
			String qid,qstr,A,B,C,D;
			char correct;
			qid = id_test+"_"+(i+1);
			System.out.print("Enter the qestion"+(i+1)+": ");
			qstr = sc.nextLine();qstr = sc.nextLine();
			System.out.print("Enter option A: ");
			A = sc.nextLine();
			System.out.print("Enter option B: ");
			B = sc.nextLine();
			System.out.print("Enter option C: ");
			C = sc.nextLine();
			System.out.print("Enter option D: ");
			D = sc.nextLine();
			System.out.print("Enter the correct option: ");
			correct = sc.next().charAt(0);
			Question q = new Question(qid,id_test, qstr, A, B, C, D, correct);
			q.push2db();
		}
		
		e.push2db();
	}
	
	public void viewMarks(String id_test)
	{
		System.out.println("-------------------------------");
		System.out.println("Test ID: "+id_test);
		QuizDB qobj = new QuizDB();
		
		System.out.println("\nQuestion Wise Score:");
		String query = "select m.q_id,m.stud_id,m.score,q.q_str from marks as m, question as q where m.q_id=q.q_id and q.test_id='"+id_test+"'";
		qobj.runQuery(query);
		qobj.resultDisplay();
		
		System.out.println("\nTotal Score:");
		query = "select m.stud_id,sum(m.score) as tot_score from marks as m, question as q where m.q_id=q.q_id and q.test_id='"+id_test+"' group by m.stud_id";
		qobj.runQuery(query);
		qobj.resultDisplay();
	}
}

class Student extends User
{
	public String type;
	
	public Student(long id, String name, String pwd)
	{
		super(id,name,pwd);
		type = "student";
	}

	public void push2db()
	{
		QuizDB qobj = new QuizDB();
		qobj.runQuery("select * from user_list where user_id="+id);
		ArrayList<ArrayList<String>> result = qobj.getResult();
		if(result.size()>1)
			System.out.println("User ID already exists");
		else
			qobj.runQuery("insert into user_list(user_id,user_name,password,user_type) values("+id+", '"+name+"', '"+pwd+"', '"+type+"')");
	}
	
	public void viewMarks(String id_test)
	{
		System.out.println("-------------------------------");
		System.out.println("Test ID: "+id_test);
		QuizDB qobj = new QuizDB();
		System.out.println("\nQuestion Wise Score:");
		String query = "select m.q_id,m.stud_id,m.score,q.q_str from marks as m, question as q where m.q_id=q.q_id and q.test_id='"+id_test+"' and stud_id='"+this.id+"'";
		qobj.runQuery(query);
		qobj.resultDisplay();
		
		System.out.println("\nTotal Score:");
		query = "select m.stud_id,sum(m.score) as tot_score from marks as m, question as q where m.q_id=q.q_id and q.test_id='"+id_test+"' and stud_id='"+this.id+"' group by m.stud_id";
		qobj.runQuery(query);
		qobj.resultDisplay();
	}
	
	private void attemptQestion(String qid)
	{
		Scanner sc = new Scanner(System.in);
		QuizDB qobj = new QuizDB();
		qobj.runQuery("select q_id, stud_id from marks where stud_id='"+this.id+"' and q_id='"+qid+"'");
		ArrayList<ArrayList<String>> result = qobj.getResult();
		if(result.size()==1)
			qobj.runQuery("insert into marks values('"+qid+"','"+this.id+"',0)");
		
		qobj.runQuery("select * from question where q_id='"+qid+"'");
		result = qobj.getResult();
		ArrayList<String> curr = result.get(1);
		Question q = new Question(curr.get(0),curr.get(1),curr.get(2),curr.get(3),curr.get(4),curr.get(5),curr.get(6),curr.get(7).charAt(0));
		q.display();
		char correct = curr.get(7).charAt(0);
		System.out.println("Enter your choice");
		char chosen = sc.next().charAt(0);
		
		if(chosen==correct)
			qobj.runQuery("update marks set score=1 where stud_id='"+this.id+"' and q_id='"+qid+"'");
	}
	
	public void attemptTest(String id_test)
	{
		System.out.println("-------------------------------");
		System.out.println("Test ID: "+id_test);
		QuizDB qobj = new QuizDB();
		qobj.runQuery("select q_id from question where test_id='"+id_test+"'");
		ArrayList<ArrayList<String>> result = qobj.getResult();
		if(result.size()<2)
		{
			System.out.println("Questions of this test don't exist.");
			return;
		}
		
		for(int i=1; i<result.size(); i++)
		{
			ArrayList<String> curr = result.get(i);
			attemptQestion(curr.get(0));
		}
	}
}

/*
	
*/