# JPL_project
5th sem java project
```diff
- !!!!!!!!!!!!!!!!!!!! Download files folders in the same format as the repo !!!!!!!!!!!!!!!!!!!!
```
## File exection on cmd from the given parent folder
- jcmpl CodeFile.java
- jrun CodeFile

## class QuizDB - API for querying the quiz database
Present in the quizlib package
### Attributes
- fields : List of fields
- table : matrix of all the rows below the heading
### Methods
- ```void runQuery(String query)``` : Updates the field and table contents based on the String query
- ```void resultDisplay()``` : Display result table
- ```ArrayList< ArrayList<String> > getResult()``` : returns the matrix of entire result table. The first row comprises the headers (field names)
### Example
```Java
import java.util.Collection;
import java.util.ArrayList;
import quizlib.QuizDB;

public class TestAPI
{
	public static void main(String args[])
	{
		QuizDB qobj = new QuizDB();
		qobj.runQuery("SELECT * FROM course");
		System.out.println("Testing resultDisplay:");
		qobj.resultDisplay();
		
		System.out.println("\nTesting getResult:");
		ArrayList<ArrayList<String>> result = qobj.getResult();
		for(ArrayList<String> row : result)
		{
			for(String data : row)
				System.out.print(data+"\t|\t");
			System.out.println();	
		}
	}
}
```
![image](https://user-images.githubusercontent.com/88941689/201488526-fea844f6-f106-45f2-9e02-62791cdb6314.png)

#
command_script.txt (unnecessary) has a dump of some commands that may or may not be needed
