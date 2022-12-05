import quizlib.QuizDB;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class User
{
	private long id;
	private String name;
	private String pwd;
	
	public User(long id, String name, String pwd)
	{
		this.pwd = pwd;
		this.name = name;
		this.id = id;
	}
	
	public boolean inDB()
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
		qobj.runQuery("select user_id, password from user_list where user_id="+id);
		ArrayList<ArrayList<String>> result = qobj.getResult();
		String real_pwd = (result.get(1)).get(1);
		if(real_pwd.equals(pwd))
			return true;
		else
			return false;
	}
	
	public abstract void push2db();
	public abstract void viewMarks(String id_test);
}

class Teacher extends User
{
	public String type;
	private String course;

	public Teacher(long id, String name, String pwd, String course)
	{
		super(id,name,pwd);
		type = "teacher";
		this.course = course;
	}
	
	public void push2db()
	{
		QuizDB qobj = new QuizDB();
		qobj.runQuery("select * from user_list where user_id="+id);
		ArrayList<ArrayList<String>> result = qobj.getResult();
		if(result.size()>1)
			System.out.println("User ID already exists");
		else
			qobj.runQuery("insert into user_list values("+id+", '"+name+"', '"+pwd+"', '"+type+"','"+course+"')");
	}
	
	public void setTest()
	{
		Scanner sc = new Scanner(System.in);
		QuizDB qobj = new QuizDB();
		
		// Set the test metadata
		String id_test;
		while(true)
		{
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
			System.out.print("Enter the qestion: ");
			qstr = sc.nextLine();
			System.out.print("Enter option A: ");
			A = sc.nextLine();
			System.out.print("Enter option B: ");
			B = sc.nextLine();
			System.out.print("Enter option C: ");
			C = sc.nextLine();
			System.out.print("Enter option D: ");
			D = sc.nextLine();
			System.out.print("Enter the qestion: ");
			correct = sc.next().charAt(0);
			Question q = new Question(qid, qstr, A, B, C, D, correct);
			q.push2db();
		}
		
		e.push2db();
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
}