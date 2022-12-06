import quizlib.QuizDB;
import java.util.*;

public class Driver
{
	public static void user_options(User u, String type)
	{
		Scanner sc = new Scanner(System.in);
		if(type.equals("teacher"))
		{
			u.display();
			System.out.println("1. set a test\n2. view marks\n3.exit");
			System.out.print("Enter choice: ");
			int choice = sc.nexInt();
			switch(choice)
			{
				case 1:
				{
					((Teacher) u).setTest();
				}
				break;

				case 2:
				{
					System.out.println("Enter test id to view marks");
					String id_test = sc.next();
					((Teacher) u).viewMarks(id_test);
				}
				break;
			}
		}
		else if(type.equals("student"))
		{
			u.display();
			System.out.println("1. attempt a test\n2. view own marks\n3.exit");
			System.out.print("Enter choice: ");
			int choice = sc.nexInt();
			switch(choice)
			{
				case 1:
				{
					System.out.println("Available Tests:");
					QuizDB qobj = new QuizDB();
					qobj.runQuery("select * from test");
					qobj.resultDisplay();


					System.out.println("Enter test id to view marks");
					String id_test = sc.next();
					((Teacher) u).viewMarks(id_test);
				}
				break;

				case 2:
				{
					System.out.println("Enter test id to view marks");
					String id_test = sc.next();
					((Teacher) u).viewMarks(id_test);
				}
				break;
			}
		}
		else
		{
			System.out.println("No menu for admin.");
		}
	}
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		User u = null;
		while (true)
		{
			System.out.println("1 --> Log in\n 2 --> Sign up");
			int init_choice = sc.nextInt();
			boolean done = false;
			switch (init_choice) {
				case 1: {
					System.out.println("Enter User Id: ");
					int uid = sc.nextInt();
					System.out.println("Enter Password: ");
					String pwd = sc.nextLine();
					done = User.validate(uid, pwd);
				}
					break;

				case 2: {
					User.signup();
				}
					break;
			}
			if (done)
				break;
		}
		QuizDB qobj = new QuizDB();
		qobj.runQuery("select user_id, password from user_list where user_id="+id);
		ArrayList<ArrayList<String>> result = qobj.getResult();
		ArrayList<String> user_det = result.get(1);
		String type = user_det.get(3);
		if (type == "student")
		{
			u = new Student(Integer.parseInt(user_det.get(0)), user_det.get(1), user_det.get(2));
		}
		else if(type == "teacher")
		{
			u = new Teacher(Integer.parseInt(user_det.get(0)), user_det.get(1), user_det.get(2), user_det.get(4));
		}
		else
		{
			System.out.println("The type of user is admin. No need to login.");
		}
	}
}
