package gpa.ui;

import java.util.Scanner;
import java.util.InputMismatchException;
import gpa.core.Points;
import gpa.core.Ordinal;
import gpa.core.Letter;

// This class provides a static method that Main can call to run the interactive GPA calculation flow.
public class CalculateGPA {
    public static void calculateGPA(Scanner in) {
        int semesters = 0;
        boolean validInput = false;
        
        // Prompt for number of semesters with input validation
        while (!validInput) {
            try {
                System.out.print("How many semesters have you taken?: ");
                semesters = in.nextInt();
                validInput = true;
            } 
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                in.nextLine(); // Clear the invalid input
            }
        }

        double totalPoints = 0.0;
        int totalCourses = 0;
        
        // Loop through each semester
        for (int i = 1; i <= semesters; i++) {
            int classes = 0;
            boolean validClasses = false;
            
            // Prompt for number of classes with input validation
            while (!validClasses) {
                try {
                    System.out.print("How many classes did you take in your " + Ordinal.format(i) + " semester?: ");
                    classes = in.nextInt();
                    
                    // Validate number of classes
                    if (classes < 0) {
                        System.out.println("Number of classes cannot be negative. Please try again.");
                        continue;
                    }
                    if (classes == 0) {
                        System.out.println("No classes taken in this semester.");
                        validClasses = true;
                        continue;
                    }
                    if (classes > 10) {
                        System.out.println("Invalid number of classes. Please enter a number between 1 and 10.");
                        continue;
                    }
                    validClasses = true;
                } 
                catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    in.nextLine(); // Clear the invalid input
                }
            }

            // Loop through each class in the semester
            for (int j = 1; j <= classes; j++) {
                System.out.print("Enter the letter grade for your " + j + Ordinal.format(j) + " class (A, B, C, D, F): ");
                String input = in.next().toUpperCase();
                char grade;
                while (!input.matches("[A-F]")) {
                    System.out.println("Incorrect input. Please enter a valid letter grade (A-F)");
                    System.out.print("Enter the letter grade for your " + j + Ordinal.format(j) + " class (A, B, C, D, F): ");
                    input = in.next().toUpperCase();
                }
                grade = input.charAt(0);
                double points = Points.toPoints(grade);
                totalPoints += points;
                totalCourses++;
            }
        
        }
        
        // Calculate and display overall GPA, semesters, and total courses
        if (totalCourses > 0) {
            double gpa = totalPoints / totalCourses;
            System.out.printf("\n== Your overall GPA is: %.2f%n", gpa);
            System.out.println("== Your letter GPA is: " + Letter.fromGpa(gpa) + "\n");
            System.out.println("==Total semesters you have taken: " + semesters);
            System.out.println("==Total courses taken: " + totalCourses + "\n");
        } else {
            System.out.println("No courses found.");
        }

        System.out.print("Do you want to calculate another GPA? (y/n): ");
        char again = in.next().toLowerCase().charAt(0);
        if (again == 'y') {
            calculateGPA(in);
        }
        else {
            System.out.println("Thank you for using the GPA Calculator. Goodbye!");
            System.exit(0);
        }

    }
        
}

