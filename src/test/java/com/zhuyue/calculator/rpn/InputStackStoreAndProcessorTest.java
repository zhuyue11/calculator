package com.zhuyue.calculator.rpn;

import com.zhuyue.calculator.rpn.cli.CLIMessageHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

class InputStackStoreAndProcessorTest {

    InputStackStoreAndProcessor inputStackStoreAndProcessor;

    @BeforeEach
    void setUp() {
        inputStackStoreAndProcessor = new InputStackStoreAndProcessor(Mockito.mock(CLIMessageHandler.class));
    }

    @Test
    void testStoreAndCalculateWithNoOperation() {
        inputStackStoreAndProcessor.storeAndCalculate("1.37463526 3.878786");
        Assertions.assertEquals(new BigDecimal("3.878786000000000"), inputStackStoreAndProcessor.getNumberStack().pop());
        Assertions.assertEquals(new BigDecimal("1.374635260000000"), inputStackStoreAndProcessor.getNumberStack().pop());
    }

    @Test
    void testStoreAndCalculateWithOnePlusOperation() {
        inputStackStoreAndProcessor.storeAndCalculate("1.3 3.12");
        inputStackStoreAndProcessor.storeAndCalculate("+");
        Assertions.assertEquals(new BigDecimal("4.420000000000000"), inputStackStoreAndProcessor.getNumberStack().pop());
    }

    @Test
    void testStoreAndCalculateWithOneSubtractOperation() {
        inputStackStoreAndProcessor.storeAndCalculate("1.3 3.12");
        inputStackStoreAndProcessor.storeAndCalculate("-");
        Assertions.assertEquals(new BigDecimal("-1.820000000000000"), inputStackStoreAndProcessor.getNumberStack().pop());
    }

    @Test
    void testStoreAndCalculateWithOneMultiplyOperation() {
        inputStackStoreAndProcessor.storeAndCalculate("11.3 3");
        inputStackStoreAndProcessor.storeAndCalculate("*");
        Assertions.assertEquals(new BigDecimal("33.900000000000000"), inputStackStoreAndProcessor.getNumberStack().pop());
    }

    @Test
    void testStoreAndCalculateWithOneDivideOperation() {
        inputStackStoreAndProcessor.storeAndCalculate("1.3 3.12");
        inputStackStoreAndProcessor.storeAndCalculate("/");
        Assertions.assertEquals(new BigDecimal("0.416666666666667"), inputStackStoreAndProcessor.getNumberStack().pop());
    }

    @Test
    void testStoreAndCalculateWithOneSqrtOperation() {
        inputStackStoreAndProcessor.storeAndCalculate("0.82");
        inputStackStoreAndProcessor.storeAndCalculate("sqrt");
        Assertions.assertEquals(new BigDecimal("0.905538513813742"), inputStackStoreAndProcessor.getNumberStack().pop());
    }

    @Test
    void testStoreAndCalculateWithMultipleOperations() {
        inputStackStoreAndProcessor.storeAndCalculate("0.81");
        inputStackStoreAndProcessor.storeAndCalculate("sqrt");
        inputStackStoreAndProcessor.storeAndCalculate("1.1 2.2 +");
        inputStackStoreAndProcessor.storeAndCalculate("-");
        Assertions.assertEquals(new BigDecimal("-2.400000000000000"), inputStackStoreAndProcessor.getNumberStack().pop());
    }

    @Test
    void testClearWithNoOperationSuccess() {
        inputStackStoreAndProcessor.storeAndCalculate("1.37463526 3.878786");
        inputStackStoreAndProcessor.clear();
        Assertions.assertEquals(0, inputStackStoreAndProcessor.getNumberStack().size());
    }

    @Test
    void testClearWithOneOperationSuccess() {
        inputStackStoreAndProcessor.storeAndCalculate("1.37463526 3.878786");
        inputStackStoreAndProcessor.storeAndCalculate("+");
        inputStackStoreAndProcessor.clear();
        Assertions.assertEquals(0, inputStackStoreAndProcessor.getNumberStack().size());
    }

    @Test
    void testClearWithMultipleOperationSuccess() {
        inputStackStoreAndProcessor.storeAndCalculate("1.37463526 3.878786");
        inputStackStoreAndProcessor.storeAndCalculate("+");
        inputStackStoreAndProcessor.storeAndCalculate("92.123213432 100.090908");
        inputStackStoreAndProcessor.storeAndCalculate("-");
        inputStackStoreAndProcessor.clear();
        Assertions.assertEquals(0, inputStackStoreAndProcessor.getNumberStack().size());
    }

    @Test
    void testUndoWithNoOperationOnlyRemoveLastNumber() {
        inputStackStoreAndProcessor.storeAndCalculate("1.37463526 3.878786");
        inputStackStoreAndProcessor.undo();
        Assertions.assertEquals(new BigDecimal("1.374635260000000"), inputStackStoreAndProcessor.getNumberStack().pop());
    }

    @Test
    void testUndoWithOperationRestoreStack() {
        inputStackStoreAndProcessor.storeAndCalculate("1.37463526 3.878786");
        inputStackStoreAndProcessor.storeAndCalculate("+");
        inputStackStoreAndProcessor.undo();
        Assertions.assertEquals(new BigDecimal("3.878786000000000"), inputStackStoreAndProcessor.getNumberStack().pop());
        Assertions.assertEquals(new BigDecimal("1.374635260000000"), inputStackStoreAndProcessor.getNumberStack().pop());
    }
}