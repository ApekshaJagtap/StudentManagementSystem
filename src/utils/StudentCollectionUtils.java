package utils;
import static com.app.core.Subject.ANGULAR;
import static com.app.core.Subject.DBT;
import static com.app.core.Subject.JAVA;
import static com.app.core.Subject.REACT;
import static java.time.LocalDate.parse;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

import com.app.core.Address;
import com.app.core.Student;
import com.app.core.Subject;

public class StudentCollectionUtils {
	private static int index = 0;

	 public static ArrayList<Student> populateList() {
		ArrayList<Student> students = new ArrayList<>(
				Arrays.asList(new Student("dac-001", "ravi", parse("1990-12-13"), ANGULAR, 3.2),
						new Student("dac-009", "riya", parse("1992-12-13"), REACT, 6.9),
						new Student("dac-004", "shekhar", parse("1991-12-13"), DBT, 8.9),
						new Student("dac-002", "priya", parse("1990-02-23"), JAVA, 7.7),
						new Student("dac-007", "kiran", parse("1993-02-13"), DBT, 8.1),
						new Student("dac-003", "meeta", parse("1993-12-13"), DBT, 7.9),
						new Student("dac-008", "sameer", parse("1991-12-06"), JAVA, 8.2)));
		List<Address> adrs = Arrays.asList(new Address("pune", "MH", "452446"), new Address("pune", "MH", "652446"),
				new Address("nagpur", "MH", "852446"), new Address("indore", "MP", "752446"),
				new Address("mumbai", "MH", "672446"), new Address("pune", "MH", "692446"),
				new Address("chennai", "TN", "862446"));

		students.forEach(s -> s.assignAddress(adrs.get(index++)));
		return students;// rets populated growable list of students with adr
	}

	// add a static method to accept list of student details & rets a map populated with the same
	public static Map<String, Student> populateMap(List<Student> list) {
		HashMap<String, Student> hm = new HashMap<>();
		list.forEach(s -> hm.put(s.getRollNo(), s));
		return hm;

	}
	
	public static void displayAll(Map<String,Student> map)
	{
		map.values()
		.stream()
		.forEach(System.out::println);
	}
	
	
	public static void storeDetails(Map<String,Student> map)throws Exception
	{
		
		try(PrintWriter pw=new PrintWriter(new FileWriter("stuDetail.txt"))){
		map.values()
		.stream()
		.forEach(i->pw.println(i.getSubject()+" "+i.getAddress()));
		}
	}
	
	public static void passedStudent(Map<String,Student>map)throws Exception
	{
		try(PrintWriter pw=new PrintWriter(new FileWriter("passedStudent.txt")))
		{
			Comparator<Student> comp=(p1,p2)->p1.getDob().compareTo(p2.getDob());
			map.values().stream()
			.filter(i->i.getGpa()>6)
			.sorted(comp)
			.forEach(i->pw.println(i));
		}
	}
	
	public static void storeSortedByGPA(Map<String,Student> map) throws Exception
	{
		try(PrintWriter pw=new PrintWriter(new FileWriter("sortedData.txt")))
		{
		map.values().stream()
		.sorted((s1,s2)->((Double)s1.getGpa()).compareTo(s2.getGpa()))
		.forEach(i->pw.println(i));
		}
	}
	
	public static void sortGPA(Map<String,Student> map)
	{
		map.values().stream()
		.sorted((s1,s2)->((Double)s1.getGpa()).compareTo(s2.getGpa()))
		.forEach(System.out::println);
	}
	
	public static OptionalDouble findAverage(ArrayList<Student> list,String subject) throws Exception
	{

		OptionalDouble avg=list.stream()
		.filter(s->s.getSubject().toString().equals(subject.toUpperCase()))
		.mapToDouble(s->s.getGpa()).average();
		return avg;
//		double sum=0;
//		int ctr=0;
//		for(Student s:list)
//		{
//			if(s!=null && s.getSubject().toString().equals(subject.toUpperCase()) )
//			{
//				sum+=s.getGpa();
//				ctr++;
//			}
//		}
//		double avg=sum/ctr;
//		return avg;
		
	}
	
	public static String getTopper(Map<String,Student> map,String subject)
	{
		String name=map.values().stream().filter(s->s.getSubject().toString().equals(subject.toUpperCase())).max((s1,s2)->((Double)s1.getGpa()).compareTo(s2.getGpa())).get().getName();
		return name;
	}
	
//	Print no of  failures for the specified subject from the specified city ,chosen  from user.
//	(gpa < 5 : failed case)
	
	public static long calculateFailure(Map<String,Student> map, String subject,String city)
	{
		long c=map.values().stream()
		.filter(i->i.getSubject().toString().equals(subject.toUpperCase()))
		.filter(i->i.getAddress().getCity().equals(city))
		.filter(i->i.getGpa()<5).count();
		return c;
	}
	
	public static void startWith(Map<String,Student> map)
	{
		map.values().stream().filter(i->i.getName().startsWith("r")).forEach(System.out::println);;
	}
	
	public static void cancelAdmission(String rollno,Map<String,Student> map)
	{
		Iterator<Student> itr=map.values().iterator();
		while(itr.hasNext())
		{
			if(itr.next().getRollNo().equals(rollno))
			{
				itr.remove();
			}
		}
	}
	
//	public static void cancelAdmission(Map<String,Student> map,String roll)
//	{
//		
//		map.values().removeIf(i->i.getRollNo().equals(roll));
//	}
	
	public static void cancelCource(String subject,Map<String,Student> map)
	{
		Iterator<Student> itr=map.values().iterator();
		while(itr.hasNext())
		{
			Student s=itr.next();
			if(s.getSubject().toString().equals(subject.toUpperCase()))
			{
				map.get(s.getRollNo()).setSubject(null);
			}
		}
	}
	
//	public static void storeDetails(Map<String,Student> map)
//	{
//		
//	}
	
	
	
	
	
	
	
	
	
}
