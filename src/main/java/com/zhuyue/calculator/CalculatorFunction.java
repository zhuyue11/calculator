package com.zhuyue.calculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class CalculatorFunction {

    private static final int PRECISION = 15;
    private static final MathContext MATH_CONTEXT = new MathContext(PRECISION);

    private CalculatorFunction(){}

    /**
     * Returns a {@code BigDecimal} whose value is <code>(numberOne +
     * numberTwo)</code>, and whose scale is 15, value will be rounded in half-up mode.
     *
     * @param  numberOne value to be added {@code BigDecimal}.
     * @param  numberTwo value to be added {@code BigDecimal}.
     * @return {@code numberOne + numberTwo}
     */
    public static BigDecimal plus(BigDecimal numberOne, BigDecimal numberTwo) {
        return numberOne.add(numberTwo).setScale(PRECISION, RoundingMode.HALF_UP);
    }

    /**
     * Returns a {@code BigDecimal} whose value is <code>(numberOne -
     * numberTwo)</code>, and whose scale is 15, value will be rounded in half-up mode.
     *
     * @param  numberOne value to be subtracted {@code BigDecimal}.
     * @param  numberTwo value to subtract {@code BigDecimal}.
     * @return {@code numberOne - numberTwo}
     */
    public static BigDecimal subtract(BigDecimal numberOne, BigDecimal numberTwo) {
        return numberOne.subtract(numberTwo).setScale(PRECISION, RoundingMode.HALF_UP);
    }

    /**
     * Returns a {@code BigDecimal} whose value is <code>(numberOne &times;
     * numberTwo)</code>, and whose scale is 15, value will be rounded in half-up mode.
     *
     * @param  numberOne value to be multiplied {@code BigDecimal}.
     * @param  numberTwo value to be multiplied {@code BigDecimal}.
     * @return {@code numberOne &times; numberTwo}
     */
    public static BigDecimal multiply(BigDecimal numberOne, BigDecimal numberTwo) {
        return numberOne.multiply(numberTwo).setScale(PRECISION, RoundingMode.HALF_UP);
    }

    /**
     * Returns a {@code BigDecimal} whose value is <code>(numberOne /
     * numberTwo)</code>, and whose scale is 15, value will be rounded in half-up mode.
     *
     * @param  numberOne is the numerator {@code BigDecimal}.
     * @param  numberTwo is the denominator {@code BigDecimal}.
     * @return {@code numberOne / numberTwo}
     */
    public static BigDecimal divide(BigDecimal numberOne, BigDecimal numberTwo) {
        return numberOne.divide(numberTwo, PRECISION, RoundingMode.HALF_UP);
    }

    /**
     * Returns a {@code BigDecimal} whose value is <code>(sqrt(numberOne)</code>,
     * and whose scale is 15, value will be rounded in half-up mode.
     *
     * @param  number value to be sqrt {@code BigDecimal}.
     * @return {@code sqrt(numberOne)}
     */
    public static BigDecimal sqrt(BigDecimal number) {
        var intPartLength = number.sqrt(MATH_CONTEXT).toString().split("\\.")[0].length();
        return number.sqrt(new MathContext(intPartLength + PRECISION));
    }

}
