package com.zhuyue.calculator.rpn;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class InputValidator {

    private static final List<String> VALID_SINGLE_NUMBER_OPERATORS = List.of("sqrt");
    private static final List<String> VALID_DOUBLE_NUMBER_OPERATORS = List.of("+", "-", "*", "/");
    private static final int DECIMAL_SCALE = 15;

    private InputValidator() {}

    public static boolean isSingleNumberOperator(String item) {
        return VALID_SINGLE_NUMBER_OPERATORS.contains(item);
    }

    public static boolean isDoubleNumberOperator(String item) {
        return VALID_DOUBLE_NUMBER_OPERATORS.contains(item);
    }

    public static boolean isOperator(String item) {
        return VALID_DOUBLE_NUMBER_OPERATORS.contains(item) || VALID_SINGLE_NUMBER_OPERATORS.contains(item);
    }

    public static BigDecimal strToBigDecimal(String item) {
        try {
            return new BigDecimal(item).setScale(DECIMAL_SCALE, RoundingMode.HALF_UP);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
