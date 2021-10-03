package com.zhuyue.calculator.rpn.cli;

import com.zhuyue.calculator.rpn.ErrorMessageHandler;

public class CLIErrorMessageHandler extends ErrorMessageHandler {

    @Override
    public void handleFormatErrorMessage(int errorItemIndex) {
        System.out.printf("Invalid input on position: %d%n", errorItemIndex);
    }

    @Override
    public void handleOperatorErrorMessage(int errorItemIndex, String operator) {
        // use parameter index as the position, it's different than the requirement but make more sense
        System.out.printf("operator <%s> (position: <%d>): insufficient parameters%n", operator, errorItemIndex);
    }

}
