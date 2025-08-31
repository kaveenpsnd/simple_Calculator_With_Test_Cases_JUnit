package com.example.calculator.ops;

public class ArithmeticOperations {
    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    // TODO: Implement the divide method which would pass the given test cases
    public double divide(double a, double b) {
        if(b == 0.0) {
            throw new ArithmeticException("cannot divide by zero");
        }
        return a / b;
    }



}
