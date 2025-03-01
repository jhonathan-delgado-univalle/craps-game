package com.example.crapsgame.models;

/**
 * ChallangesFPOE class have the challenges that we did in class
 *
 * @author Jhonathan Delgado
 * @version 1.0.0
 */
public class ChallengesFPOE {

    /**
     * It is the student full name
     * @serialField
     */
    private String studentName;

    /**
     * To calcute rectangle area
     * @param width: To represent the base of rectangle
     * @param height: To represent the height of rectangle
     * @return The rectangle area
     */
    public double calculateRectangleArea(double width, double height) {
        return width * height;
    }

    /**
     * To calcute triangle area
     * @param width: To represent the base of triangle
     * @param height: To represent the height of triangle
     * @return The triangle area
     * @throws ArithmeticException Division by 0
     */
    public double calculateTriangleArea(double width, double height) throws ArithmeticException{
        int divisor = 2;
        return (width * height) / divisor;
    }

    /**
     * To print in console rectangle area
     * @param width: To represent the base of rectangle
     * @param height: To represent the height of rectangle
     * @see #calculateRectangleArea(double, double)
     * @since 1.0.0
     */
    public void showRectangleArea(double width, double height) {
        System.out.println("The rectangle area is " + this.calculateRectangleArea(width, height));
    }

}
