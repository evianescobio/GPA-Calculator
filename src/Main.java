import java.util.Scanner;

public class Main {

    // Convert letter grade to GPA points
    private static double gradeToPoints(char grade) {
        switch (Character.toUpperCase(grade)) {
            case 'A': return 4.0;
            case 'B': return 3.0;
            case 'C': return 2.0;
            case 'D': return 1.0;
            case 'F': return 0.0;
            default:
                System.out.println("Invalid grade entered. Counting as 0.0");
                return 0.0;
        }
    }

    // Method to calculate the GPA for each semester.
    public static double calculateSemesterGPA(Scanner input, int semesterNumber, double[] totals) {
        System.out.print("How many classes did you take in semester " + semesterNumber + "?: ");
        int numOfClasses = input.nextInt();

        double totalPoints = 0.0;

        for (int j = 1; j <= numOfClasses; j++) {
            System.out.print("Enter grade for class " + j + " (A, B, C, D, F): ");
            char grade = input.next().toUpperCase().charAt(0);
            totalPoints += gradeToPoints(grade);
        }

        // Update cumulative totals
        totals[0] += totalPoints;   // total grade points
        totals[1] += numOfClasses;  // total number of classes

        // Avoid /0 if user typed 0 classes this semester
        return (numOfClasses == 0) ? 0.0 : totalPoints / numOfClasses;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("How many semesters have you taken?: ");
        int semesters = input.nextInt();

        double[] totals = {0.0, 0.0}; // [0]=points, [1]=classes

        for (int i = 1; i <= semesters; i++) {
            double semesterGPA = calculateSemesterGPA(input, i, totals);
            System.out.printf("Semester %d GPA: %.2f%n", i, semesterGPA);
        }

        System.out.println();

        if (totals[1] == 0) {
            System.out.println("No classes entered. General GPA cannot be computed.");
            input.close();
            return;
        }

        double generalGPA = totals[0] / totals[1];
        System.out.printf("General GPA across all semesters: %.2f%n", generalGPA);

        // Feedback
        if (generalGPA == 4.0) {
            System.out.println("Wow, congratulations! You have a perfect GPA.");
        } else if (generalGPA >= 3.0) {
            System.out.println("Congratulations! Your GPA is good.");
        } else if (generalGPA >= 2.0) {
            System.out.println("Your GPA is regular.");
        } else if (generalGPA >= 1.0) {
            System.out.println("Your GPA is bad.");
        } else { // < 1.0
            System.out.println("Your GPA is tremendously horrible.");
        }

        input.close();
    }
}
