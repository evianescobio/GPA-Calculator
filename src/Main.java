import java.util.Scanner;

public class Main {
    // Constants for GPA thresholds
    private static final double EXCELLENT_GPA = 4.0;
    private static final double GOOD_GPA = 3.0;
    private static final double REGULAR_GPA = 2.0;
    private static final double POOR_GPA = 1.0;

    // Enum for grades with their corresponding GPA points
    private enum Grade {
        A_PLUS(4.0), A(4.0), A_MINUS(3.7),
        B_PLUS(3.3), B(3.0), B_MINUS(2.7),
        C_PLUS(2.3), C(2.0), C_MINUS(1.7),
        D_PLUS(1.3), D(1.0), D_MINUS(0.7),
        F(0.0);

        private final double points;

        Grade(double points) {
            this.points = points;
        }

        public double getPoints() {
            return points;
        }

        public static Grade fromString(String grade) {
            try {
                return Grade.valueOf(grade.toUpperCase().replace("+", "_PLUS").replace("-", "_MINUS"));
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid grade entered. Counting as F.");
                return F;
            }
        }
    }

    // Convert string grade to GPA points
    private static double gradeToPoints(String grade) {
        return Grade.fromString(grade).getPoints();
    }

    // Method to calculate the GPA for each semester.
    public static double calculateSemesterGPA(Scanner input, int semesterNumber, double[] totals) {
        int numOfClasses;
        do {
            System.out.print("How many classes did you take in semester " + semesterNumber + "?: ");
            while (!input.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                input.next();
            }
            numOfClasses = input.nextInt();
            if (numOfClasses < 0) {
                System.out.println("Number of classes cannot be negative. Please try again.");
            }
        } while (numOfClasses < 0);

        double totalPoints = 0.0;
        input.nextLine(); // Clear the buffer

        for (int j = 1; j <= numOfClasses; j++) {
            System.out.print("Enter grade for class " + j + " (A+, A, A-, B+, B, B-, etc.): ");
            String grade = input.nextLine().trim();
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

        try {
            int semesters;
            do {
                System.out.print("How many semesters have you taken?: ");
                while (!input.hasNextInt()) {
                    System.out.println("Please enter a valid number.");
                    input.next();
                }
                semesters = input.nextInt();
                if (semesters <= 0) {
                    System.out.println("Number of semesters must be positive. Please try again.");
                }
            } while (semesters <= 0);

            double[] totals = {0.0, 0.0}; // [0]=points, [1]=classes

            for (int i = 1; i <= semesters; i++) {
                double semesterGPA = calculateSemesterGPA(input, i, totals);
                System.out.printf("Semester %d GPA: %.2f%n", i, semesterGPA);
            }

            System.out.println();

            if (totals[1] == 0) {
                System.out.println("No classes entered. General GPA cannot be computed.");
                return;
            }

            double generalGPA = totals[0] / totals[1];
            System.out.printf("General GPA across all semesters: %.2f%n", generalGPA);

            // Feedback using constants
            System.out.println("\nGPA Assessment:");
            if (generalGPA == EXCELLENT_GPA) {
                System.out.println("Outstanding! You've achieved a perfect GPA. Keep up the excellent work!");
            } else if (generalGPA >= GOOD_GPA) {
                System.out.println("Great job! You're maintaining a strong academic performance.");
            } else if (generalGPA >= REGULAR_GPA) {
                System.out.println("You're meeting academic standards, but there's room for improvement.");
            } else if (generalGPA >= POOR_GPA) {
                System.out.println("Consider seeking academic support to improve your performance.");
            } else {
                System.out.println("It's important to meet with an academic advisor to discuss improvement strategies.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            input.close();
        }
    }
}
