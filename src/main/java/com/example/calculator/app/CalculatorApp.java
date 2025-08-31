package com.example.calculator.app;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.example.calculator.ops.ArithmeticOperations;

public class CalculatorApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArithmeticOperations ops = new ArithmeticOperations();

        System.out.println("=== Arithmetic Calculator ===");

        while (true) {
            System.out.println("1) Add  2) Subtract  3) Multiply  4) Divide  0) Exit");
            System.out.print("Enter choice: ");
            int choice;
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException ex) {
                sc.nextLine(); // clear
                System.out.println("Please enter a number.");
                continue;
            }
            if (choice == 0) break;

            try {
                System.out.print("Enter first number: "); double a = sc.nextDouble();
                System.out.print("Enter second number: "); double b = sc.nextDouble();
                double res = switch (choice) {
                    case 1 -> ops.add(a, b);
                    case 2 -> ops.subtract(a, b);
                    case 3 -> ops.multiply(a, b);
                    case 4 -> ops.divide(a, b);
                    default -> throw new IllegalArgumentException("Invalid choice");
                };
                System.out.println("Result = " + res);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        sc.close();
        System.out.println("Goodbye!");
    }
}
