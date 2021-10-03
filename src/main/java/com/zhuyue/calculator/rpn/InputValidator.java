package com.zhuyue.calculator.rpn;

import java.util.List;

public class InputValidator {

    private static final List<String> VALID_SINGLE_NUMBER_OPERATORS = List.of("sqrt");
    private static final List<String> VALID_DOUBLE_NUMBER_OPERATORS = List.of("+", "-", "*", "/");

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

    public static Double strToDouble(String item) {
        try {
            return Double.parseDouble(item);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
