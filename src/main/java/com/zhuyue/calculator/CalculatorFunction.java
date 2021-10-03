package com.zhuyue.calculator;

public class CalculatorFunction {

    private CalculatorFunction(){}

    public static double plus(double numberOne, double numberTwo) {
        return numberOne + numberTwo;
    }

    public static double subtract(double numberOne, double numberTwo) {
        return numberOne - numberTwo;
    }

    public static double multiply(double numberOne, double numberTwo) {
        return numberOne * numberTwo;
    }

    public static double divide(double numberOne, double numberTwo) {
        return numberOne / numberTwo;
    }

    public static double sqrt(double number) {
        return Math.sqrt(number);
    }

}
