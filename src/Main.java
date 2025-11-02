import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("=== Welcome to My GPA Calculator ===\n");

        while (true) {
            System.out.println("1. Calculate GPA");
            System.out.println("2. Exit");
            System.out.print("Select one option from the menu (1-2): ");
            int choice = in.nextInt();
            switch (choice) {
                case 1:
                    calculateGPA(in);
                    break;
                case 2:
                    System.out.println("Thank you for using the GPA Calculator. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        }

    }

    private static void calculateGPA(Scanner in) {
        System.out.print("How many semesters have you taken?: ");
        int semesters = in.nextInt();

        double totalPoints = 0.0;
        int totalCourses = 0;

        for (int i = 1; i <= semesters; i++) {
            System.out.print("How many classes did you take in your " + ordinal(i) + " semester?: ");
            int classes = in.nextInt();
            for (int j = 1; j <= classes; j++) {
                System.out.print("Enter the letter grade for class " + j + ": ");
                char grade = in.next().toUpperCase().charAt(0);
                double points = toPoints(grade);
                totalPoints += points;
                totalCourses++;
            }
        }

        if (totalCourses > 0) {
            double gpa = totalPoints / totalCourses;
            System.out.printf("Your overall GPA is: %.2f%n", gpa);
            System.out.println("Your letter GPA is: " + getLetter(gpa));
        } else {
            System.out.println("No courses found.");
        }
    }

    private static String ordinal(int n) {
        int mod100 = n % 100;
        if (mod100 >= 11 && mod100 <= 13) {
            return n + "th";
        }
        switch (n % 10) {
            case 1: return n + "st";
            case 2: return n + "nd";
            case 3: return n + "rd";
            default: return n + "th";
        }
    }

    private static char getLetter (double gpa) {
        if (gpa >= 3.7) return 'A';
        else if (gpa >= 2.7) return 'B';
        else if (gpa >= 1.7) return 'C';
        else if (gpa >= 0.7) return 'D';
        else return 'F';
    }

    private static double toPoints(char letter) {
        switch (letter) {
            case 'A': return 4.0;
            case 'B': return 3.0;
            case 'C': return 2.0;
            case 'D': return 1.0;
            case 'F': return 0.0;
            default: return 0.0;
        }
    }
}