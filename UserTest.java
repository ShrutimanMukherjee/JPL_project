public class UserTest
{
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