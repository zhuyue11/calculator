package com.zhuyue.calculator.rpn;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Deque;

public abstract class MessageHandler {

    public abstract void handleFormatErrorMessage(int errorItemIndex);

    public abstract void handleOperatorErrorMessage(int errorItemIndex, String operator);

    public abstract void printRPNStack(Deque<BigDecimal> stack);

    protected static String formatBigDecimal(BigDecimal number) {
        var doubleStr = String.format("%.10f", number);
        var intAndDecimal = doubleStr.split("\\.");
        var decimalCharArray = intAndDecimal[1].toCharArray();
        while (decimalCharArray.length > 0) {
            if (decimalCharArray[decimalCharArray.length - 1] == '0') {
                decimalCharArray = Arrays.copyOf(decimalCharArray, decimalCharArray.length - 1);
            } else {
                break;
            }
        }

        if (decimalCharArray.length == 0) {
            return intAndDecimal[0];
        } else {
            return intAndDecimal[0] + "." + String.valueOf(decimalCharArray);
        }
    }
}
