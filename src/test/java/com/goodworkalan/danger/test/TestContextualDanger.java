package com.goodworkalan.danger.test;

import com.goodworkalan.danger.ContextualDanger;

/**
 * A test exception for testing exceptions with context messages.
 * 
 * @author Alan Gutierrez
 */
@SuppressWarnings("serial")
public class TestContextualDanger extends ContextualDanger {
    /**
     * Construct a test exception that wraps the given cause exception and reads
     * the message associate with the given message code relative to the given
     * context class.
     * 
     * @param contextClass
     *            The context class.
     * @param code
     *            The message code.
     * @param cause
     *            The cause.
     */
    public TestContextualDanger(Class<?> contextClass, String code, Throwable cause) {
        super(contextClass, code, cause);
    }
}
