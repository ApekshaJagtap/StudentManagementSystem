package Tester;
import static utils.StudentCollectionUtils.*;

import java.util.Map;
import java.util.Scanner;

import com.app.core.Student;
public class StudentTester {
	
//	new Student("dac-009", "riya", parse("1992-12-13"), REACT, 6.9),
//	new Student("dac-004", "shekhar", parse("1991-12-13"), DBT, 8.9),
//	new Student("dac-002", "priya", parse("1990-02-23"), JAVA, 7.7),
//	new Student("dac-007", "kiran", parse("1993-02-13"), DBT, 8.1),
//	new Student("dac-003", "meeta", parse("1993-12-13"), DBT, 7.9),
//	new Student("dac-008", "sameer", parse("1991-12-06"), JAVA, 8.2)));
	
	public static void main(String[] args)
	{
		try (Scanner sc=new Scanner(System.in))
		{
			Map<String,Student> map=populateMap(populateList());
			boolean exit=false;
			while(!exit)
			{
				System.out.println("1:DisplayAll \n2:Cancel Admission \n3:Cancel Course \n4:Store in file /n5:Exit");
				switch(sc.nextInt())
				{
				case 1:
					displayAll(map);
					break;
				case 2:
					System.out.println("Enter roll no: ");
					cancelAdmission(sc.next(),map);
					break;
				case 3:
					System.out.println("Enter rollno course: ");
					cancelCource(sc.next(),map);
					break;
				case 4:
					passedStudent(map);
					storeDetails(map);
					storeSortedByGPA(map);
					break;
				case 5:
					exit=true;
					System.out.println("Exit...");
					
				}
			}
			
//			populateList().stream().forEach(System.out::println);
			
//			System.out.println(findAverage(populateList(),"java"));
//			System.out.println(getTopper(map,"java"));
			
//			System.out.println(calculateFailure(map,"angular","pune"));
			
//			startWith(map);
			
			
		
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

}
