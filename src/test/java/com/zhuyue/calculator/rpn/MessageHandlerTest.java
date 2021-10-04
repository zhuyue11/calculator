package com.zhuyue.calculator.rpn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Deque;

class MessageHandlerTest {

    MessageHandler messageHandler;
    Method method;

    @BeforeEach
    void setUp() throws NoSuchMethodException {
        messageHandler = new MessageHandler() {
            @Override
            public void handleFormatErrorMessage(int errorItemIndex) {}

            @Override
            public void handleOperatorErrorMessage(int errorItemIndex, String operator) {}

            @Override
            public void printRPNStack(Deque<BigDecimal> stack) {}
        };

        Class<MessageHandler> clazz = MessageHandler.class;
        method = clazz.getDeclaredMethod("formatBigDecimal", BigDecimal.class);
        method.setAccessible(true);
    }

    @Test
    void testFormatBigDecimalFormatInt() throws InvocationTargetException, IllegalAccessException {
        var result = (String) method.invoke(messageHandler, new BigDecimal("10"));
        Assertions.assertEquals("10", result);
    }

    @Test
    void testFormatBigDecimalFormatNormalDecimal() throws InvocationTargetException, IllegalAccessException {
        var result = (String) method.invoke(messageHandler, new BigDecimal("10.543"));
        Assertions.assertEquals("10.543", result);
    }

    @Test
    void testFormatBigDecimalFormatDecimalWithTrailingZeros() throws InvocationTargetException, IllegalAccessException {
        var result = (String) method.invoke(messageHandler, new BigDecimal("10.543000000"));
        Assertions.assertEquals("10.543", result);
    }

    @Test
    void testFormatBigDecimalFormatLongDecimalRoundDown() throws InvocationTargetException, IllegalAccessException {
        var result = (String) method.invoke(messageHandler, new BigDecimal("10.54300000012"));
        Assertions.assertEquals("10.5430000001", result);
    }

    @Test
    void testFormatBigDecimalFormatLongDecimalRoundUp() throws InvocationTargetException, IllegalAccessException {
        var result = (String) method.invoke(messageHandler, new BigDecimal("10.54300000016"));
        Assertions.assertEquals("10.5430000002", result);
    }
}