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
        inputStackStoreAndProcessor.calculateAndStore("1.37463526 3.878786");
        Assertions.assertEquals(new BigDecimal("3.878786000000000"), inputStackStoreAndProcessor.getNumberStack().pop());
        Assertions.assertEquals(new BigDecimal("1.374635260000000"), inputStackStoreAndProcessor.getNumberStack().pop());
    }

    @Test
    void testStoreAndCalculateWithOnePlusOperation() {
        inputStackStoreAndProcessor.calculateAndStore("1.3 3.12");
        inputStackStoreAndProcessor.calculateAndStore("+");
        Assertions.assertEquals(new BigDecimal("4.420000000000000"), inputStackStoreAndProcessor.getNumberStack().pop());
    }

    @Test
    void testStoreAndCalculateWithOneSubtractOperation() {
        inputStackStoreAndProcessor.calculateAndStore("1.3 3.12");
        inputStackStoreAndProcessor.calculateAndStore("-");
        Assertions.assertEquals(new BigDecimal("-1.820000000000000"), inputStackStoreAndProcessor.getNumberStack().pop());
    }

    @Test
    void testStoreAndCalculateWithOneMultiplyOperation() {
        inputStackStoreAndProcessor.calculateAndStore("11.3 3");
        inputStackStoreAndProcessor.calculateAndStore("*");
        Assertions.assertEquals(new BigDecimal("33.900000000000000"), inputStackStoreAndProcessor.getNumberStack().pop());
    }

    @Test
    void testStoreAndCalculateWithOneDivideOperation() {
        inputStackStoreAndProcessor.calculateAndStore("1.3 3.12");
        inputStackStoreAndProcessor.calculateAndStore("/");
        Assertions.assertEquals(new BigDecimal("0.416666666666667"), inputStackStoreAndProcessor.getNumberStack().pop());
    }

    @Test
    void testStoreAndCalculateWithOneSqrtOperation() {
        inputStackStoreAndProcessor.calculateAndStore("0.82");
        inputStackStoreAndProcessor.calculateAndStore("sqrt");
        Assertions.assertEquals(new BigDecimal("0.905538513813742"), inputStackStoreAndProcessor.getNumberStack().pop());
    }

    @Test
    void testStoreAndCalculateWithMultipleOperations() {
        inputStackStoreAndProcessor.calculateAndStore("0.81");
        inputStackStoreAndProcessor.calculateAndStore("sqrt");
        inputStackStoreAndProcessor.calculateAndStore("1.1 2.2 +");
        inputStackStoreAndProcessor.calculateAndStore("-");
        Assertions.assertEquals(new BigDecimal("-2.400000000000000"), inputStackStoreAndProcessor.getNumberStack().pop());
    }

    @Test
    void testClearWithNoOperationSuccess() {
        inputStackStoreAndProcessor.calculateAndStore("1.37463526 3.878786");
        inputStackStoreAndProcessor.clear();
        Assertions.assertEquals(0, inputStackStoreAndProcessor.getNumberStack().size());
    }

    @Test
    void testClearWithOneOperationSuccess() {
        inputStackStoreAndProcessor.calculateAndStore("1.37463526 3.878786");
        inputStackStoreAndProcessor.calculateAndStore("+");
        inputStackStoreAndProcessor.clear();
        Assertions.assertEquals(0, inputStackStoreAndProcessor.getNumberStack().size());
    }

    @Test
    void testClearWithMultipleOperationSuccess() {
        inputStackStoreAndProcessor.calculateAndStore("1.37463526 3.878786");
        inputStackStoreAndProcessor.calculateAndStore("+");
        inputStackStoreAndProcessor.calculateAndStore("92.123213432 100.090908");
        inputStackStoreAndProcessor.calculateAndStore("-");
        inputStackStoreAndProcessor.clear();
        Assertions.assertEquals(0, inputStackStoreAndProcessor.getNumberStack().size());
    }

    @Test
    void testUndoWithNoOperationOnlyRemoveLastNumber() {
        inputStackStoreAndProcessor.calculateAndStore("1.37463526 3.878786");
        inputStackStoreAndProcessor.undo();
        Assertions.assertEquals(new BigDecimal("1.374635260000000"), inputStackStoreAndProcessor.getNumberStack().pop());
    }

    @Test
    void testUndoWithOperationRestoreStack() {
        inputStackStoreAndProcessor.calculateAndStore("1.37463526 3.878786");
        inputStackStoreAndProcessor.calculateAndStore("+");
        inputStackStoreAndProcessor.undo();
        Assertions.assertEquals(new BigDecimal("3.878786000000000"), inputStackStoreAndProcessor.getNumberStack().pop());
        Assertions.assertEquals(new BigDecimal("1.374635260000000"), inputStackStoreAndProcessor.getNumberStack().pop());
    }
}