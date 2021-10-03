package com.zhuyue.calculator.rpn.cli;

import java.util.Scanner;

public class CLIInputReceiver {

    private static final Scanner SCANNER = new Scanner(System.in);

    private CLIInputReceiver() {}

    public static String receiveInput() {
        return SCANNER.nextLine().trim();
    }

}
