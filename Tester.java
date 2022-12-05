public class Tester
{
	public static void main(String args[])
	{
		Teacher t = new Teacher(5,"Sheetal Chaudhary","Sheetal_pwd","CS001");
		t.viewMarks("JavaT1");
		t.viewMarks("JavaT2");
	}
}
// Sign up - duplicate w, 1 teacher w, 1student w
/*
for(int i=0; i<3; i++)
	User.signup();
	
Enter user id: 2
Enter user name: R
Enter password: R_pwd
Enter user type: student
User ID already exists
Enter user id: 4
Enter user name: Rahul
Enter password: RPat_pwd
Enter user type: student
SQL Exception : No results were returned by the query.
Enter user id: 5
Enter user name: Sheetal Chaudhary
Enter password: Sheetal_pwd
Enter user type: teacher
Enter Course ID: CS001
SQL Exception : No results were returned by the query.
*/

// Sign in - incorrect, correct
/*
System.out.println(User.validate(1,"RPat_pwd"));
System.out.println(User.validate(4,"RPat_pwd"));

false
true
*/

// Teacher - (setTest --> 2 questions) 
/*
	Teacher t = new Teacher(5,"Sheetal Chaudhary","Sheetal_pwd","CS001");
	t.setTest();
	t.setTest();
	
Set the test id: JavaT1
Set the test name: Java Test 1
Set the start time:
         year = 2022
         month = 12
         day of Month = 9
         hour = 10
         minute = 0
         second = 0
Set the end time:
         year = 222
         month = 12
         day of Month = 9
         hour = 11
         minute = 0
         second = 0
Enter the number of questions: 2
Enter the qestion1: Q 1
Enter option A: A 1
Enter option B: B 1
Enter option C: C 1
Enter option D: D 1
Enter the correct option: A
SQL Exception : No results were returned by the query.
Enter the qestion2: Q 2
Enter option A: A 2
Enter option B: B 2
Enter option C: C 2
Enter option D: D 2
Enter the correct option: B
SQL Exception : No results were returned by the query.
SQL Exception : No results were returned by the query.
Set the test id: JavaT2
Set the test name: Java Test 2
Set the start time:
         year = 2022
         month = 12
         day of Month = 24
         hour = 9
         minute = 0
         second = 0
Set the end time:
         year = 2022
         month = 12
         day of Month = 24
         hour = 10
         minute = 0
         second = 0
Enter the number of questions: 2
Enter the qestion1: q1
Enter option A: a1
Enter option B: b1
Enter option C: c1
Enter option D: d1
Enter the correct option: C
SQL Exception : No results were returned by the query.
Enter the qestion2: q2
Enter option A: a2
Enter option B: b2
Enter option C: c2
Enter option D: d2
Enter the correct option: D
SQL Exception : No results were returned by the query.
SQL Exception : No results were returned by the query.
*/

// Student - attemptTest X 2 
/*
Student s1 = new Student(4,"Rahul","RPat_pwd");
s1.attemptTest("JavaT1");
s1.attemptTest("JavaT2");

SQL Exception : No results were returned by the query.
----------- Question ---------------
Question ID : JavaT1_1
Question : Q 1
A : A 1
B : B 1
C : C 1
D : D 1
Enter your choice
A
SQL Exception : No results were returned by the query.
SQL Exception : No results were returned by the query.
----------- Question ---------------
Question ID : JavaT1_2
Question : Q 2
A : A 2
B : B 2
C : C 2
D : D 2
Enter your choice
B
SQL Exception : No results were returned by the query.
SQL Exception : No results were returned by the query.
----------- Question ---------------
Question ID : JavaT2_1
Question : q1
A : a1
B : b1
C : c1
D : d1
Enter your choice
C
SQL Exception : No results were returned by the query.
SQL Exception : No results were returned by the query.
----------- Question ---------------
Question ID : JavaT2_2
Question : q2
A : a2
B : b2
C : c2
D : d2
Enter your choice
D
SQL Exception : No results were returned by the query.
*/

// Student - view marks X 2 
/*
Student s1 = new Student(4,"Rahul","RPat_pwd");
s1.viewMarks("JavaT1");
s1.viewMarks("JavaT2");

-------------------------------
Test ID: JavaT1

Question Wise Score:
q_id    |       stud_id |       score   |       q_str   |
---------------------
JavaT1_1        |       4       |       1       |       Q 1     |
JavaT1_2        |       4       |       1       |       Q 2     |

Total Score:
stud_id |       tot_score       |
---------------------
4       |       2       |
-------------------------------
Test ID: JavaT2

Question Wise Score:
q_id    |       stud_id |       score   |       q_str   |
---------------------
JavaT2_1        |       4       |       1       |       q1      |
JavaT2_2        |       4       |       1       |       q2      |

Total Score:
stud_id |       tot_score       |
---------------------
4       |       2       |
*/

// Teacher - view marks X 2 
/*
Teacher t = new Teacher(5,"Sheetal Chaudhary","Sheetal_pwd","CS001");
t.viewMarks("JavaT1");
t.viewMarks("JavaT2");

-------------------------------
Test ID: JavaT1

Question Wise Score:
q_id    |       stud_id |       score   |       q_str   |
---------------------
JavaT1_1        |       4       |       1       |       Q 1     |
JavaT1_2        |       4       |       1       |       Q 2     |

Total Score:
stud_id |       tot_score       |
---------------------
4       |       2       |
-------------------------------
Test ID: JavaT2

Question Wise Score:
q_id    |       stud_id |       score   |       q_str   |
---------------------
JavaT2_1        |       4       |       1       |       q1      |
JavaT2_2        |       4       |       1       |       q2      |

Total Score:
stud_id |       tot_score       |
---------------------
4       |       2       |
*/