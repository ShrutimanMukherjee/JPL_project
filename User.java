import quizlib.QuizDB;
import java.util.ArrayList;

public class User
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
	
	public void push2db()
	{
		QuizDB qobj = new QuizDB();
		qobj.runQuery("select * from user_list where user_id="+id);
		ArrayList<ArrayList<String>> result = qobj.getResult();
		if(result.size()>1)
			System.out.println("User ID already exists");
		else
			qobj.runQuery("insert into user_list values("+id+", '"+name+"', '"+pwd+"')");
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
}

/*class Teacher extends User
{
	
}

class Student extends User
{
	
}*/