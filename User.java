import quizlib.QuizDB;
import java.util.ArrayList;
public class User // Also the Tester class for testing functions
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
	
	public static void main(String args[])
	{
		User u = new User(0,"Shrutiman0","ShM_pwd0");
		User u1 = new User(1,"Shrutiman","ShM_pwd");
		
		u.push2db();
		u1.push2db();
		
		System.out.println(User.validate(1,"ShM_pwd"));
		
		u1.display();
	}
}

/*class Teacher extends User
{
	
}

class Student extends User
{
	
}*/