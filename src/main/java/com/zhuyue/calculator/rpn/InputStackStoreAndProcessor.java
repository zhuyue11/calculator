package com.zhuyue.calculator.rpn;

import com.zhuyue.calculator.CalculatorFunction;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class InputStackStoreAndProcessor {

    private static final String INPUT_ITEM_SEPARATOR = " ";
    private final LinkedList<BigDecimal> numberStack;
    private final Deque<BigDecimal> numberReverseStack;
    private final Deque<String> operatorReverseStack;
    private final Deque<Integer> undoSizeStack;
    private final MessageHandler messageHandler;

    public InputStackStoreAndProcessor(MessageHandler messageHandler) {
        this.numberStack = new LinkedList<>();
        this.numberReverseStack = new ArrayDeque<>();
        this.operatorReverseStack = new ArrayDeque<>();
        this.undoSizeStack = new ArrayDeque<>();
        this.messageHandler = messageHandler;
    }

    /**
     * Parse the input and push the numbers to the number stack and the operators
     * to the operator stack. After that doing the calculation if necessary
     *
     * @param input string contains rpn calculator input {@code String}.
     */
    public void calculateAndStore(String input) {
        if (StringUtils.isBlank(input)) {
            return;
        }

        var items = getInputItems(input);

        for (var i = 0; i < items.length; i++) {
            var failToProcess = false;
            var item = items[i];
            if (InputValidator.isOperator(item)) {
                var calculated = checkOperatorAndCalculate(item, i);
                failToProcess = !calculated;
            } else {
                var number = InputValidator.strToBigDecimal(item);
                if (number == null) {
                    messageHandler.handleFormatErrorMessage(i);
                    failToProcess = true;
                } else {
                    numberStack.push(number);
                }
            }

            if (failToProcess) {
                break;
            }
        }

        messageHandler.printRPNStack(numberStack);
    }

    /**
     * remove empty items from input and get items
     *
     * @return {@code items from input}
     */
    private String[] getInputItems(String input) {
        var items = input.split(INPUT_ITEM_SEPARATOR);
        var itemList = new ArrayList<>(Arrays.asList(items));
        itemList.removeIf(s -> s.length() == 0);
        return itemList.toArray(new String[0]);
    }

    /**
     * Pop up the operators one by one and doing the calculation, until the operator
     * stack is empty
     */
    private void calculate(String operator) {
        operatorReverseStack.push(operator);
        BigDecimal result;
        BigDecimal numberTwo;
        BigDecimal numberOne = null;
        switch (operator) {
            case "+":
                numberTwo = numberStack.pop();
                numberOne = numberStack.pop();
                result = CalculatorFunction.plus(numberOne, numberTwo);
                break;
            case "-":
                numberTwo = numberStack.pop();
                numberOne = numberStack.pop();
                result = CalculatorFunction.subtract(numberOne, numberTwo);
                break;
            case "*":
                numberTwo = numberStack.pop();
                numberOne = numberStack.pop();
                result = CalculatorFunction.multiply(numberOne, numberTwo);
                break;
            case "/":
                numberTwo = numberStack.pop();
                numberOne = numberStack.pop();
                result = CalculatorFunction.divide(numberOne, numberTwo);
                break;
            default:
                numberTwo = numberStack.pop();
                result = CalculatorFunction.sqrt(numberTwo);
                break;
        }

        numberReverseStack.push(numberTwo);
        if (numberOne != null) {
            numberReverseStack.push(numberOne);
        }
        numberStack.push(result);
    }

    /**
     * Check if the item is valid to be stored in the stack.
     * If not, use <code>messageHandler</code> to produce the message
     *
     * @param operator it is an operator {@code String}.
     * @param itemIndex the index of the item from the input {@code int}.
     */
    private boolean checkOperatorAndCalculate(String operator, int itemIndex) {

        var ifEnoughNumberToOperate = (InputValidator.isSingleNumberOperator(operator)
                && !numberStack.isEmpty())
                || (InputValidator.isDoubleNumberOperator(operator) && numberStack.size() > 1);
        if (ifEnoughNumberToOperate) {
            calculate(operator);
            operatorReverseStack.push(operator);
            setUndoSize();
        } else {
            messageHandler.handleOperatorErrorMessage(itemIndex, operator);
        }

        return ifEnoughNumberToOperate;
    }

    /**
     * Set undoIndex after operation
     */
    private void setUndoSize() {
        undoSizeStack.push(numberStack.size());
    }

    /**
     * Clear the 4 internal stacks, which store the number to be operated, the operators
     * and the stacks used to do undo operation
     */
    public void clear() {
        numberStack.clear();
        numberReverseStack.clear();
        operatorReverseStack.clear();
        undoSizeStack.clear();
        System.console().writer().println("Stack cleared");
    }

    /**
     * Revert the number stack to the previous status, discard the latest operator
     */
    public void undo() {
        String operator = null;
        if (!operatorReverseStack.isEmpty()) {
            operator = operatorReverseStack.pop();
        }

        if (numberReverseStack.isEmpty()) {
            if (!numberStack.isEmpty()) {
                numberStack.pop();
            } else {
                System.console().writer().println("Nothing to undo!");
            }
        } else {
            int undoIndex = undoSizeStack.pop();
            var index = numberStack.size() - undoIndex;
            numberStack.add(index, numberReverseStack.pop());
            if (InputValidator.isDoubleNumberOperator(operator)) {
                numberStack.add(index, numberReverseStack.pop());
                index += 2;
            } else {
                index ++;
            }
            numberStack.remove(index);
        }
        messageHandler.printRPNStack(numberStack);
    }

    /**
     * Return current number stack
     */
    public Deque<BigDecimal> getNumberStack() {
        return numberStack;
    }
}
