package com.zhuyue.calculator.rpn.cli;

import com.zhuyue.calculator.rpn.InputStackStoreAndProcessor;

public class CLIApp {

    private static final InputStackStoreAndProcessor inputStackStoreAndProcessor =
            new InputStackStoreAndProcessor(new CLIErrorMessageHandler());

    public static void main(String[] args) {
        System.out.println("RPN calculator started, input \"clear\" to start over, " +
                "input \"undo\" to go back to the previous step, input \"exit\" to exit");
        looping();
    }

    private static void looping() {
        var input = CLIInputReceiver.receiveInput();
        switch (input) {
            case "exit":
                System.out.println("Exiting calculator......");
                break;
            case "clear":
                inputStackStoreAndProcessor.clear();
                looping();
                break;
            case "undo":
                inputStackStoreAndProcessor.undo();
                looping();
                break;
            default:
                inputStackStoreAndProcessor.storeAndCalculate(input);
                looping();
                break;
        }
    }

}
