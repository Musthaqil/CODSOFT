package task5;
import java.util.*;
import java.sql.*;

class Student{
	PreparedStatement ps;
	void add(int rollno, String name, String grade) {
		name = name.toUpperCase();
		char[] nameArr = name.toCharArray();
		for(int i=0; i<nameArr.length; i++) {
			if((int)nameArr[i]<65 || (int)nameArr[i]>90) {
				System.out.println("Name should be allowed only Alphabets.");
				return;
			}
		}
		grade = grade.toUpperCase();
		String[] allGrades = {"O", "A+", "A", "B+", "B", "C", "RA"};
		int flag=0;
		for(int i=0; i<allGrades.length; i++) {
			if(grade.equals(allGrades[i])) {
				flag++;
			}
		}
		if(flag==0) {
			System.out.println("Please enter the valid Grade from the below options");
			System.out.println(Arrays.toString(allGrades));
			return;
		}
		
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33070/student","root","mu$Thaqil09");
			String insertQuery = "INSERT INTO gradetable VALUES(?,?,?)";
			ps = con.prepareStatement(insertQuery);
			ps.setInt(1,rollno);
			ps.setString(2,name);
			ps.setString(3, grade);
			ps.executeUpdate();
			con.close();
			System.out.println("Student "+name+"'s details added Successfully");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	void edit(int rollno) {
		Scanner sc = new Scanner(System.in);
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33070/student","root","mu$Thaqil09");
			String viewQuery = "SELECT * FROM gradetable";
			ps = con.prepareStatement(viewQuery);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int checkRoll=rs.getInt(1);
				if(checkRoll==rollno) {
					System.out.print("Please Enter name to edit: ");
					String updateName = sc.nextLine();
					System.out.print("Please Enter grade to update: ");
					String updateGrade = sc.nextLine();
					String updateQuery = "UPDATE gradetable set name=?, grade=? where rollno=?";
					ps = con.prepareStatement(updateQuery);
					ps.setString(1, updateName);
					ps.setString(2, updateGrade);
					ps.setInt(3, rollno);
					ps.executeUpdate();
					
					sc.close();
					con.close();
					System.out.println("Student Details updated Successfully");
					break;
				}
				else {
					System.out.println(rollno+" is not available. Please enter the existing student roll no");
					return;
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	void search(int rollno) {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33070/student","root","mu$Thaqil09");
			String viewQuery = "SELECT * FROM gradetable";
			ps = con.prepareStatement(viewQuery);
			ResultSet rs = ps.executeQuery();
			int flag=0;
			while(rs.next()) {
				if(rs.getInt(1)==rollno) {
					System.out.println("Name: "+rs.getString(2));
					System.out.println("Grade: "+rs.getString(3));
					flag++;
					break;
				}
			}
			if(flag==0) {
				System.out.println("Searched student data is not available");
			}
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	void display() {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33070/student","root","mu$Thaqil09");
			String displayQuery = "SELECT * FROM gradetable";
			ps = con.prepareStatement(displayQuery);
			ResultSet rs = ps.executeQuery();
			System.out.println("RollNo \tName \t\tGrade");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
			}
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
public class StudentManagementSystem {
	public static void main(String args[]) {
		Scanner sc = new Scanner (System.in);
		int rollno;
		String name;
		String grade;
		System.out.println("Welcome to Student Management System!");
		
		while(true) {
		System.out.println("Please enter an option from below:");
		System.out.println("1. Add a new Student");
		System.out.println("2. Edit an existing Student");
		System.out.println("3. Search for a Student");
		System.out.println("4. Display all Students");
		System.out.println("5. Exit");
		int choice = sc.nextInt();
		
		Student s = new Student();
		switch(choice) {
		case 1: System.out.print("Enter Roll Number: ");
				rollno = sc.nextInt();
				sc.nextLine();
				System.out.print("Enter Name: ");
				name = sc.nextLine();
				System.out.print("Enter Grade: ");
				grade = sc.nextLine();
				s.add(rollno, name, grade);
				break;
				
		case 2: System.out.print("Enter an existing Student's Roll number: ");
				rollno = sc.nextInt();
				s.edit(rollno);
				break;
				
		case 3: System.out.print("Enter an existing Student's Roll number: ");
				rollno = sc.nextInt();
				s.search(rollno);
				break;
				
		case 4: s.display();
				break;
				
		case 5: System.out.println("Thank you! Visit again");
				sc.close();
				return;
				
		default: System.out.println("Invalid choice! Please choose the correct option");
				break;
		}
		}
		
		
	}
}