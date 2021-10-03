package com.zhuyue.calculator.rpn;

public abstract class ErrorMessageHandler {

    public abstract void handleFormatErrorMessage(int errorItemIndex);

    public abstract void handleOperatorErrorMessage(int errorItemIndex, String operator);

    protected int calculatePosition(int itemIndex) {
        return itemIndex * 2 + 1;
    }

}
