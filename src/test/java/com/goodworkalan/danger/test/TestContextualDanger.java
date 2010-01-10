package com.goodworkalan.danger.test;

import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.goodworkalan.danger.ContextualDanger;

/**
 * A test exception for testing exceptions with context messages.
 * 
 * @author Alan Gutierrez
 */
@SuppressWarnings("serial")
public class TestContextualDanger extends ContextualDanger {
    /** The cache of message bundles. */
    private final static ConcurrentMap<String, ResourceBundle> bundles = new ConcurrentHashMap<String, ResourceBundle>();

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
        super(bundles, contextClass, code, cause);
    }
}
