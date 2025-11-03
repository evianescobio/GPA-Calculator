package gpa.core;

// This class provides a method to convert GPA numeric value to letter grade.
public class Letter {
    public static char fromGpa(double gpa) {
        if (gpa >= 3.7) return 'A';
        else if (gpa >= 2.7) return 'B';
        else if (gpa >= 1.7) return 'C';
        else if (gpa >= 0.7) return 'D';
        else return 'F';
    }
}
