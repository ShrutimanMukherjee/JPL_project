import java.util.Collection;
import java.util.ArrayList;
import org.postgresql.*;
import java.sql.*;

public class QuizDB
{
	private static final String url = "jdbc:postgresql://heffalump.db.elephantsql.com:5432/qoagvwfc";
	private static final String username = "qoagvwfc";
	private static final String password = "QuS_0KbA1Nz7huCy2c8GyhurimfVg6Vi";
	
	private ArrayList<String> fields;
	private ArrayList<ArrayList<String>> table;
	private Connection db;
	
	public QuizDB()
	{
		try
		{
            Class.forName("org.postgresql.Driver");
        }
        catch (java.lang.ClassNotFoundException e)
		{
            System.out.println("Cannot find : "+e.getMessage());
        }
		
		try
		{
			db = DriverManager.getConnection(url, username, password);
		}
		catch (java.sql.SQLException e)
		{
			System.out.println("SQL-Driver Exception : "+e.getMessage());
		}
		fields = new ArrayList<String>();
		table = new ArrayList<ArrayList<String>>();
	}
	
	public void runQuery(String query)
	{
		try 
		{
			Statement st = db.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			for(int field=1; field<=columns; field++)
			{
				fields.add(rsmd.getColumnName(field));
			}
			
			while (rs.next())
			{
				ArrayList<String> record = new ArrayList<String>();
				for(int field=1; field<=columns; field++)
				{
					record.add(rs.getString(field));
				}
				table.add(record);
			}
			rs.close();
			st.close();
		}
		catch (java.sql.SQLException e)
		{
			System.out.println("SQL Exception : "+e.getMessage());
		}
	}
	
	public void resultDisplay()
	{
		for(String fieldName : fields)
			System.out.print(fieldName+"\t|\t");
		System.out.println("\n---------------------");
		
		for(ArrayList<String> row : table)
		{
			for(String fieldValue : row)
				System.out.print(fieldValue+"\t|\t");
			System.out.println();
		}
	}
	
	public static void main(String args[])
	{
		QuizDB qobj = new QuizDB();
		qobj.runQuery("SELECT * FROM course");
		qobj.resultDisplay();
	}
}