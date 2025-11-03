package gpa.core;

// This class provides a method to convert letter grades to numeric points.
public class Points {
    public static double toPoints(char letter) {
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
