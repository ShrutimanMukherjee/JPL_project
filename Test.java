import org.postgresql.*;
import java.sql.*;

public class Test
{
	public static void main(String args[])
	{
		try {
            Class.forName("org.postgresql.Driver");
        }
        catch (java.lang.ClassNotFoundException e) {
            System.out.println("1) "+e.getMessage());
        }
		// "jdbc:postgresql://host:port/database"
		// jdbc:postgresql://heffalump.db.elephantsql.com:5432/qoagvwfc
        // jdbc:postgres://qoagvwfc:QuS_0KbA1Nz7huCy2c8GyhurimfVg6Vi@heffalump.db.elephantsql.com/qoagvwfc
		String url = "jdbc:postgresql://heffalump.db.elephantsql.com:5432/qoagvwfc";
        String username = "qoagvwfc";
        String password = "QuS_0KbA1Nz7huCy2c8GyhurimfVg6Vi";

        try {
            Connection db = DriverManager.getConnection(url, username, password);
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM course");
			System.out.println("Course ID\t\tCourse Name");
			System.out.println("---------\t\t----------");
            while (rs.next()) {
                System.out.println(rs.getString("course_id")+"\t\t"+rs.getString("course_name"));
				//Can use 1 and 2 in place of attr names
            }
            rs.close();
            st.close();
        }
        catch (java.sql.SQLException e) {
            System.out.println("2) "+e.getMessage());
		}
	}
}