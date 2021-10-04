package com.zhuyue.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class CalculatorFunctionTest {

    @Test
    void testPlusInt() {
        Assertions.assertEquals(new BigDecimal("3.000000000000000"),
                CalculatorFunction.plus(new BigDecimal("1"), new BigDecimal("2")));
    }

    @Test
    void testPlusNormalDecimal() {
        Assertions.assertEquals(new BigDecimal("3.300000000000000"),
                CalculatorFunction.plus(new BigDecimal("1.1"), new BigDecimal("2.2")));
    }

    @Test
    void testPlusLongDecimalRoundDown() {
        Assertions.assertEquals(new BigDecimal("3.300000000000000"),
                CalculatorFunction.plus(new BigDecimal("1.1000000000000001"),
                        new BigDecimal("2.2000000000000001")));
    }

    @Test
    void testPlusLongDecimalRoundUp() {
        Assertions.assertEquals(new BigDecimal("3.300000000000001"),
                CalculatorFunction.plus(new BigDecimal("1.1000000000000001"),
                        new BigDecimal("2.2000000000000005")));
    }

    @Test
    void testSubtractInt() {
        Assertions.assertEquals(new BigDecimal("-1.000000000000000"),
                CalculatorFunction.subtract(new BigDecimal("1"), new BigDecimal("2")));
    }

    @Test
    void testSubtractNormalDecimal() {
        Assertions.assertEquals(new BigDecimal("-1.100000000000000"),
                CalculatorFunction.subtract(new BigDecimal("1.1"), new BigDecimal("2.2")));
    }

    @Test
    void testSubtractLongDecimalRoundDown() {
        Assertions.assertEquals(new BigDecimal("-1.100000000000000"),
                CalculatorFunction.subtract(new BigDecimal("1.1000000000000001"),
                        new BigDecimal("2.2000000000000002")));
    }

    @Test
    void testSubtractLongDecimalRoundUp() {
        Assertions.assertEquals(new BigDecimal("-1.100000000000000"),
                CalculatorFunction.subtract(new BigDecimal("1.1000000000000009"),
                        new BigDecimal("2.2000000000000005")));
    }

    @Test
    void testMultiplyInt() {
        Assertions.assertEquals(new BigDecimal("2.000000000000000"),
                CalculatorFunction.multiply(new BigDecimal("1"), new BigDecimal("2")));
    }

    @Test
    void testMultiplyNormalDecimal() {
        Assertions.assertEquals(new BigDecimal("2.420000000000000"),
                CalculatorFunction.multiply(new BigDecimal("1.1"), new BigDecimal("2.2")));
    }

    @Test
    void testMultiplyLongDecimalRoundDown() {
        Assertions.assertEquals(new BigDecimal("2.420000000000000"),
                CalculatorFunction.multiply(new BigDecimal("1.10000000000000001"),
                        new BigDecimal("2.20000000000000001")));
    }

    @Test
    void testMultiplyLongDecimalRoundUp() {
        Assertions.assertEquals(new BigDecimal("2.420000000000001"),
                CalculatorFunction.multiply(new BigDecimal("1.1000000000000003"),
                        new BigDecimal("2.2000000000000001")));
    }

    @Test
    void testDivideInt() {
        Assertions.assertEquals(new BigDecimal("0.500000000000000"),
                CalculatorFunction.divide(new BigDecimal("1"), new BigDecimal("2")));
    }

    @Test
    void testDivideNormalDecimal() {
        Assertions.assertEquals(new BigDecimal("0.590909090909091"),
                CalculatorFunction.divide(new BigDecimal("1.3"), new BigDecimal("2.2")));
    }

    @Test
    void testDivideLongDecimal() {
        Assertions.assertEquals(new BigDecimal("6.015479973562391"),
                CalculatorFunction.divide(new BigDecimal("23.9876542435467876"),
                        new BigDecimal("3.9876542435467879")));
    }

    @Test
    void testSqrt() {
        Assertions.assertEquals(new BigDecimal("10.168579055108929"),
                CalculatorFunction.sqrt(new BigDecimal("103.4")));
    }
}