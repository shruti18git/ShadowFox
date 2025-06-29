import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

            System.out.println("\n---Enhanced Console Calculator---");
            System.out.println("1. Basic Arithmetic");
            System.out.println("2. Scientific Calculator");
            System.out.println("3. Unit Conversion");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    basicArithmetic(sc);
                    break;
                case 2:
                    scientificCalculator(sc);
                    break;
                case 3:
                    unitConversion(sc);
                    break;
                case 4:
                    System.out.println("Exiting calculator. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        sc.close();
    }

    // 1. Basic Arithmetic
    public static void basicArithmetic(Scanner sc) {
        System.out.print("Enter first number: ");
        double num1 = sc.nextDouble();

        System.out.print("Enter second number: ");
        double num2 = sc.nextDouble();

        System.out.print("Choose operation (+, -, *, /): ");
        char op = sc.next().charAt(0);

        double result;

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

        System.out.println("Result: " + result);
    }

    // 2. Scientific Calculator
    public static void scientificCalculator(Scanner sc) {
        System.out.println("\n--- Scientific Calculator ---");
        System.out.println("1. Square Root");
        System.out.println("2. Power (a^b)");
        System.out.print("Choose an option (1 or 2): ");
        int choice = sc.nextInt();

        if (choice == 1) {
            System.out.print("Enter number: ");
            double number = sc.nextDouble();

            if (number >= 0) {
                double result = Math.sqrt(number);
                System.out.println("Square root of " + number + " is: " + result);
            } else {
                System.out.println("Error: Negative number doesn't have a real square root.");
            }
        }

        else if (choice == 2) {
            System.out.print("Enter base (a): ");
            double base = sc.nextDouble();

            System.out.print("Enter exponent (b): ");
            double exponent = sc.nextDouble();

            double result = Math.pow(base, exponent);
            System.out.println(base + " raised to the power " + exponent + " is: " + result);
        }

        else {
            System.out.println("Invalid choice.");
        }
    }

    // 3. Unit Conversion
    public static void unitConversion(Scanner sc) {
        System.out.println("\n--- Unit Conversion ---");
        System.out.println("1. Celsius to Fahrenheit");
        System.out.println("2. Fahrenheit to Celsius");
        System.out.println("3. USD to INR");
        System.out.print("Choose an option (1-3): ");
        int choice = sc.nextInt();

        if (choice == 1) {
            System.out.print("Enter Celsius: ");
            double c = sc.nextDouble();
            double f = (c * 9 / 5) + 32;
            System.out.println("Temperature in Fahrenheit: " + f);
        }

        else if (choice == 2) {
            System.out.print("Enter Fahrenheit: ");
            double f = sc.nextDouble();
            double c = (f - 32) * 5 / 9;
            System.out.println("Temperature in Celsius: " + c);
        }

        else if (choice == 3) {
            System.out.print("Enter USD: ");
            double usd = sc.nextDouble();
            double inr = usd * 83.0; // Example rate, you can update it
            System.out.println("Equivalent INR: ₹" + inr);
        }

        else {
            System.out.println("Invalid option.");
        }
    }
}
