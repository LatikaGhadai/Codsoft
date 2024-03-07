import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        gradeCalculator();
    }
    public static int totalScore(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Marks Obtained in Each Subject (Out of 100)");

        System.out.print("Mathematics: ");
        int maths = scanner.nextInt();

        System.out.print("Science: ");
        int science = scanner.nextInt();

        System.out.print("English: ");
        int english = scanner.nextInt();

        System.out.print("History: ");
        int history = scanner.nextInt();

        System.out.print("Geography: ");
        int geography = scanner.nextInt();

        scanner.close();
        int totalMarks = maths + science + english + history + geography;
        
        return totalMarks;

    }
    public static void gradeCalculator(){
        int totalMarks = totalScore();
        double averagePercentage = totalMarks / 5.0;
        System.out.println("\nResults:");
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + averagePercentage);

        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        System.out.println("Grade: " + grade);
        
    }
}