package com.goodworkalan.danger.test;

import com.goodworkalan.danger.CodedDanger;

/**
 * A test exception for testing exceptions with coded messages.
 * 
 * @author Alan Gutierrez
 */
@SuppressWarnings("serial")
public class TestCodedDanger extends CodedDanger {
    /**
     * Construct a test exception that wraps the given cause exception and and
     * the given error code.
     * 
     * @param code
     *            The error code.
     * @param cause
     *            The cause.
     */
    public TestCodedDanger(int code, Throwable cause) {
        super(code, cause);
    }
}
