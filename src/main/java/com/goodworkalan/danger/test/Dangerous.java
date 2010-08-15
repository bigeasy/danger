package com.goodworkalan.danger.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.*;

import java.util.regex.Pattern;

import com.goodworkalan.danger.Danger;

/**
 * Methods to run a block of code that throws a {@link Danger} and check the
 * properties of the thrown exception.
 * 
 * @author Alan Gutierrez
 */
public class Dangerous {
    /**
     * Run the block and catch a <code>Danger</code> checking the context class,
     * message code, and message.
     * 
     * @param runnable
     *            The runnable.
     * @param contextClass
     *            The context class.
     * @param code
     *            The message code.
     * @param cause
     *            The expected type or super type of the cause exception or null
     *            if no cause is expected.
     * @param message
     *            The message.
     */
    public static void danger(Runnable runnable, Class<?> contextClass, String code, Class<? extends Throwable> cause, String message) {
        try {
            runnable.run();
        } catch (Danger e) {
            assertEquals(e.contextClass, contextClass);
            assertEquals(e.code, code);
            assertEquals(e.getMessage(), message);
            throw e;
        }
    }
    
    /**
     * If the given cause class is null, assert that the cause of the given exception
     * is null, otherwise, assert that the cause of the given exception is
     * assignable from the given cause class.
     * 
     * @param e The exception.
     * @param cause The cause class.
     */
    static void assertCause(Danger e, Class<? extends Throwable> cause) {
        if (cause == null) {
            assertNull(e.getCause());
        } else {
            assertNotNull(e.getCause());
            assertTrue(cause.isAssignableFrom(e.getCause().getClass()));
        }
    }

    /**
     * Run the block and catch a <code>Danger</code> checking the context class
     * and message code, and checking that the pattern matches message.
     * 
     * @param runnable
     *            The runnable.
     * @param contextClass
     *            The context class.
     * @param code
     *            The message code.
     * @param cause
     *            The expected type or super type of the cause exception or null
     *            if no cause is expected.
     * @param message
     *            The message pattern.
     */
    public static void danger(Runnable runnable, Class<?> contextClass, String code, Class<? extends Throwable> cause, Pattern message) {
        try {
            runnable.run();
        } catch (Danger e) {
            assertEquals(e.contextClass, contextClass);
            assertEquals(e.code, code);
            assertCause(e, cause);
            assertTrue(message.matcher(e.getMessage()).matches());
            throw e;
        }
    }
}
