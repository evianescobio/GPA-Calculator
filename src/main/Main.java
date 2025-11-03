package main;

import java.util.Scanner;
import gpa.ui.CalculateGPA;

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
                    CalculateGPA.calculateGPA(in);
                    break;
                case 2:
                    System.out.println("Thank you for using the GPA Calculator. Goodbye!");
                    // Close the scanner and exit the program
                    in.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        
        }

    }
}