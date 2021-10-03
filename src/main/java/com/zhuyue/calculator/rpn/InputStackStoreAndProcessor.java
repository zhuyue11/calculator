package com.zhuyue.calculator.rpn;

import com.zhuyue.calculator.CalculatorFunction;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class InputStackStoreAndProcessor {

    private static final String INPUT_ITEM_SEPARATOR = " ";
    private final Deque<BigDecimal> numberStack;
    private final Deque<String> operatorStack;
    private final Deque<BigDecimal> numberReverseStack;
    private final Deque<String> operatorReverseStack;
    private final MessageHandler messageHandler;

    public InputStackStoreAndProcessor(MessageHandler messageHandler) {
        this.numberStack = new ArrayDeque<>();
        this.operatorStack = new ArrayDeque<>();
        this.numberReverseStack = new ArrayDeque<>();
        this.operatorReverseStack = new ArrayDeque<>();
        this.messageHandler = messageHandler;
    }

    public void storeAndCalculate(String input) {
        if (StringUtils.isBlank(input)) {
            return;
        }

        var ifNeedToCalculate = false;
        var items = input.split(INPUT_ITEM_SEPARATOR);

        for (var i = 0; i < items.length; i++) {
            var item = items[i];
            if (InputValidator.isOperator(item)) {
                ifNeedToCalculate |= storeOperator(item, i);
            } else {
                var number = InputValidator.strToBigDecimal(item);
                if (number == null) {
                    messageHandler.handleFormatErrorMessage(i);
                } else {
                    numberStack.push(number);
                }
            }
        }

        if (ifNeedToCalculate) {
            calculate();
        }

        messageHandler.printRPNStack(numberStack);
    }

    private void calculate() {
        var operator = operatorStack.pop();
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

        if (!operatorStack.isEmpty()) {
            calculate();
        }
    }

    private boolean storeOperator(String item, int itemIndex) {
        var allocatedNumberAmount = getAllocatedNumberAmount();

        var ifEnoughNumberToOperate = (InputValidator.isSingleNumberOperator(item)
                && numberStack.size() - allocatedNumberAmount > 0)
                || (InputValidator.isDoubleNumberOperator(item) && numberStack.size() - allocatedNumberAmount > 1);
        if (ifEnoughNumberToOperate) {
            operatorStack.push(item);
        } else {
            messageHandler.handleOperatorErrorMessage(itemIndex, item);
        }

        return ifEnoughNumberToOperate;
    }

    private int getAllocatedNumberAmount() {
        Iterator<String> it = operatorStack.descendingIterator();
        var allocatedNumberAmount = 0;
        while(it.hasNext()) {
            var operator = it.next();
            if (InputValidator.isSingleNumberOperator(operator)) {
                allocatedNumberAmount++;
            } else {
                allocatedNumberAmount += 2;
            }
        }
        return allocatedNumberAmount;
    }

    public void clear() {
        numberStack.clear();
        operatorStack.clear();
        numberReverseStack.clear();
        operatorReverseStack.clear();
        System.out.println("Stack cleared");
    }

    public void undo() {
        String operator = null;
        if (!operatorReverseStack.isEmpty()) {
            operator = operatorReverseStack.pop();
        }

        if (!numberStack.isEmpty()) {
            numberStack.pop();
            if (!numberReverseStack.isEmpty()) {
                numberStack.push(numberReverseStack.pop());
                if (operator != null && InputValidator.isDoubleNumberOperator(operator)) {
                    numberStack.push(numberReverseStack.pop());
                }
            }
        }
        messageHandler.printRPNStack(numberStack);
    }

    public Deque<BigDecimal> getNumberStack() {
        return numberStack;
    }
}
