package com.example.crapsgame262;

/**
 * This class represent the Arithmetic Operations
 *
 * @author Jhonathan Delgado
 * @version 1.1
 * @since 1.0
 */
public class ArithmeticOperations {

    /**
     * The calculator name
     * @serialField
     */
    private String calculatorName;

    /**
     * Calculate a plus b
     * @param a operator 1
     * @param b operator 2
     * @return a + b
     */
    public int addition(int a, int b) {
        return a + b;
    }

    /**
     * Calculate the division a divide b
     * @param a dividend
     * @param b divisor
     * @return a / b
     * @throws ArithmeticException If divisor is zero.
     */
    public int division(int a, int b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Division by Zero");
        }
        return a / b;
    }

    /**
     * Calculate mutiplication a per b
     * @param a multiplicator 1
     * @param b multiplicator 2
     * @return a * b
     * @since 1.0
     */
    public int multiplication(int a, int b) {
        return a * b;
    }
}
