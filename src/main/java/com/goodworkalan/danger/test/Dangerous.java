package com.goodworkalan.danger.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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
     * @param message
     *            The message.
     */
    public static void danger(Runnable runnable, Class<?> contextClass, String code, String message) {
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
     * Run the block and catch a <code>Danger</code> checking the context class
     * and message code, and checking that the pattern matches message.
     * 
     * @param runnable
     *            The runnable.
     * @param contextClass
     *            The context class.
     * @param code
     *            The message code.
     * @param message
     *            The message pattern.
     */
    public static void danger(Runnable runnable, Class<?> contextClass, String code, Pattern message) {
        try {
            runnable.run();
        } catch (Danger e) {
            assertEquals(e.contextClass, contextClass);
            assertEquals(e.code, code);
            assertTrue(message.matcher(e.getMessage()).matches());
            throw e;
        }
    }
}
