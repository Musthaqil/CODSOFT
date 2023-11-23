package task2;
import java.util.*;
public class StudentGradeCalculator {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the no. of Subjects: ");
		int n = sc.nextInt();
		
		double totalMarks = 0.0;
		for(int i=1; i<=n; i++) {
			System.out.println("Enter the mark of Subject "+i);
			double marks = sc.nextDouble();
			totalMarks+=marks;
		}
		
		sc.close();
		double avgPercentage = totalMarks/n;
		String grade ="";
		if(avgPercentage>=90) {
			grade="O";
		}
		else if(avgPercentage<90 && avgPercentage>=80) {
			grade="A+";
		}
		else if(avgPercentage<80 && avgPercentage>=70) {
			grade="A";
		}
		else if(avgPercentage<70 && avgPercentage>=60) {
			grade="B+";
		}
		else if(avgPercentage<60 && avgPercentage>=55) {
			grade="B";
		}
		else if(avgPercentage<55 && avgPercentage>=50) {
			grade="C";
		}
		else {
			grade="RA";
		}
		
		System.out.println("Scorecard:-");
		System.out.println("Total Marks: "+totalMarks+"/"+(n*100));
		System.out.println("Percentage: "+avgPercentage+"%");
		System.out.println("Grade: "+grade);
	}

}
