package com.zhuyue.calculator.rpn.cli;

import com.zhuyue.calculator.rpn.MessageHandler;

import java.math.BigDecimal;
import java.util.Deque;
import java.util.Iterator;

public class CLIMessageHandler extends MessageHandler {

    @Override
    public void handleFormatErrorMessage(int errorItemIndex) {
        System.console().writer().printf("Invalid input on position: %d%n", errorItemIndex + 1);
    }

    @Override
    public void handleOperatorErrorMessage(int errorItemIndex, String operator) {
        // use parameter index as the position, it's different than the requirement but make more sense
        System.console().writer().printf("operator <%s> (position: <%d>): insufficient parameters%n", operator, errorItemIndex + 1);
    }

    @Override
    public void printRPNStack(Deque<BigDecimal> stack) {
        Iterator<BigDecimal> it = stack.descendingIterator();
        while(it.hasNext()) {
            System.console().writer().print(formatBigDecimal(it.next()) + " ");
        }
        System.console().writer().println();
    }

}
