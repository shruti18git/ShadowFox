import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //Basic Arithmetic
        // Ask for first number
        System.out.print("Enter first number: ");
        double num1 = sc.nextDouble();

        // Ask for second number
        System.out.print("Enter second number: ");
        double num2 = sc.nextDouble();

        // Ask for operator
        System.out.print("Choose operation (+, -, *, /): ");
        char op = sc.next().charAt(0);

        double result = 0;

        // Perform operation
        if (op == '+') {
            result = num1 + num2;
        } else if (op == '-') {
            result = num1 - num2;
        } else if (op == '*') {
            result = num1 * num2;
        } else if (op == '/') {
            if (num2 == 0) {
                System.out.println("Error: Cannot divide by zero.");
                return;
            }
            result = num1 / num2;
        } else {
            System.out.println("Invalid operation.");
            return;
        }

        // Show result
        System.out.println("Result: " + result);

        sc.close();
    }
}
