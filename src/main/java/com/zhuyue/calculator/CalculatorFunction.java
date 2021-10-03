package com.zhuyue.calculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class CalculatorFunction {

    private static final int PRECISION = 15;
    private static final MathContext MATH_CONTEXT = new MathContext(PRECISION);

    private CalculatorFunction(){}

    public static BigDecimal plus(BigDecimal numberOne, BigDecimal numberTwo) {
        return numberOne.add(numberTwo).setScale(PRECISION, RoundingMode.HALF_UP);
    }

    public static BigDecimal subtract(BigDecimal numberOne, BigDecimal numberTwo) {
        return numberOne.subtract(numberTwo).setScale(PRECISION, RoundingMode.HALF_UP);
    }

    public static BigDecimal multiply(BigDecimal numberOne, BigDecimal numberTwo) {
        return numberOne.multiply(numberTwo).setScale(PRECISION, RoundingMode.HALF_UP);
    }

    public static BigDecimal divide(BigDecimal numberOne, BigDecimal numberTwo) {
        return numberOne.divide(numberTwo, MATH_CONTEXT);
    }

    public static BigDecimal sqrt(BigDecimal number) {
        return number.sqrt(MATH_CONTEXT);
    }

}
