package com.example.calculator.ops;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ArithmeticOperationsTest {

    ArithmeticOperations arithmeticOps;
    private static final double EPSILON = 1e-9; // epsilon value for floating point comparisons

    @BeforeEach
    void setUp() {
        arithmeticOps = new ArithmeticOperations();
    }

    // What are Nested classes in JUnit5?
    // Read here: https://www.baeldung.com/junit-5-nested-test-classes
    @Nested
    @DisplayName("add(a,b)")
    class AddTests {
        @Test
        @DisplayName("Positive case: positive + positive")
        void add_positive_positive() {
            assertEquals(7.0, arithmeticOps.add(3.0, 4.0));
        }

        // No negative test case (reason: add() is type safe)

        @Test
        @DisplayName("Edge case: positive with zero")
        void add_positive_zero() {
            assertEquals(5.0, arithmeticOps.add(5.0, 0.0));
        }

        @ParameterizedTest(name = "Equivalence test: {0} + {1} = {2}")
        @CsvSource({
                "10.0, 5.0, 15.0",     // both positive
                "-10.0, -5.0, -15.0",  // both negative
                "10, -5, 5",     // mixed signs
                "0.0, 12.3, 12.3"      // zero + positive
        })
        void add_equivalence_class_check(double a, double b, double expected) {
            assertEquals(expected, arithmeticOps.add(a, b));
        }

        @RepeatedTest(2)
        @DisplayName("Determinism: expect same results for the same input")
        void add_determinism() {
            assertEquals(7.0, arithmeticOps.add(2.0, 5.0));
        }
    }

    @Nested
    @DisplayName("subtract(a,b)")
    class SubtractTests {
        @Test
        @DisplayName("Positive: 7 - 4 = 3")
        void subtract_positive_positive() {
            assertEquals(3.0, arithmeticOps.subtract(7.0, 4.0));
        }

        @Test
        @DisplayName("Edge: positive with zero")
        void subtract_positive_zero() {
            assertEquals(5.0, arithmeticOps.subtract(5.0, 0.0));
        }

        @Test
        @DisplayName("Edge: negative result")
        void subtract_negative_result() {
            assertEquals(-3.0, arithmeticOps.subtract(4.0, 7.0));
        }

        @ParameterizedTest(name = "Equivalence test: {0} - {1} = {2}")
        @CsvSource({
                "10.0, 5.0, 5.0",       // both positive
                "-15.0, -5.0, -10.0",   // both negative
                "10.0, -5.0, 15.0",     // mixed signs
                "0.0, 12.3, -12.3"      // zero + positive
        })
        void subtract_equivalence_class_check(double a, double b, double expected) {
            assertEquals(expected, arithmeticOps.subtract(a, b));
        }

        // TODO: Optimize this test using proper annotations
        @RepeatedTest(5)
        @DisplayName("Determinism: same inputs, same result")
        void subtract_determinism() {

            assertEquals(7.0, arithmeticOps.subtract(10.0, 3.0));
        }
    }

// TODO: Implement test cases to test the multiply() method.
// Make sure you follow the same conventions in the codebase. I.e. use of @Nested, @DisplayName etc.
// Things to consider:
//    1. positive * positive multiplication
//    2. Edge cases (multiplication with 0, and multiplication with 1)
//    3. Check equivalence classes
//    4. Check determinism

    @Nested
    @DisplayName("multiply(a.b)")
    class MultiplyTests {
        @Test
        @DisplayName("positive: 3*4 = 12")
        void multiply_positive_12() {
            assertEquals(12.0, arithmeticOps.multiply(3.0, 4.0));
        }

        @Test
        @DisplayName("edge:multiply by zero")
        void multiply_edge_zero() {
            assertEquals(0.0, arithmeticOps.multiply(5.0, 0.0));
        }

        @Test
        @DisplayName("edge: multiply by one")
        void multiply_edge_one() {
            assertEquals(5.0, arithmeticOps.multiply(5.0, 1.0));
        }

        @ParameterizedTest(name = "Equivalence test:{0}*{1}={2}")
        @CsvSource({
                "10.0, 5.0, 50.0",
                "-10.0, 5.0,-50.0",
                "-10.0,-5.0,50.0",
                "12.5,2.0,25.0"
        })
        void multiply_equivalence_class_check(double a, double b, double expected) {
            assertEquals(expected, arithmeticOps.multiply(a, b));
        }

        @RepeatedTest(5)
        @DisplayName("Determinism: same inputs, same resut")
        void multiply_determinism() {
            assertEquals(50.0, arithmeticOps.multiply(10.0, 5.0));
        }
    }


    // TODO: Implement the divide method to pass the given test cases (TDD example).
    @Nested
    @DisplayName("divide(a,b)")
    class DivideTests {
        @Test
        @DisplayName("Positive: 8 / 4 = 2")
        void divide_integer_result() {
            assertEquals(2.0, arithmeticOps.divide(8.0, 4.0));
        }

        //  This test case can be too brittle if assertEquals(double expected, double actual) is used,
        //  due to precision issues in floating point numbers.
        //  Instead, assertEquals(double expected, double actual, double delta) is recommended.
        // See: https://stackoverflow.com/a/5939833
        @Test
        @DisplayName("Positive: 7 / 2 = 3.5")
        void divide_fraction_result() {
            assertEquals(3.5, arithmeticOps.divide(7.0, 2.0), EPSILON);
        }

        // check if an ArithmeticException is thrown and the thrown exception contains the given message.
        @Test
        @DisplayName("Negative: divide by zero throws")
        void divide_by_zero() {
            var ex = assertThrows(ArithmeticException.class, () -> arithmeticOps.divide(1.0, 0.0));
            assertTrue(ex.getMessage().contains("divide by zero"));
        }

        @Test
        @DisplayName("Edge: zero numerator")
        void divide_zero_numerator() {
            assertEquals(0.0, arithmeticOps.divide(0.0, 5.0));
        }

        @Test
        @DisplayName("Edge: negative divided by positive")
        void divide_negative_positive() {
            assertEquals(-3.0, arithmeticOps.divide(-9.0, 3.0));
        }

        // TODO: Optimize
        @RepeatedTest(5)
        @DisplayName("Determinism: same inputs, same result")
        void divide_determinism() {
            assertEquals(3.5, arithmeticOps.divide(7.0, 2.0));

        }
    }
}

