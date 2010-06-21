package com.goodworkalan.danger;

/**
 * An exception class where exceptions are assigned an integer code to help
 * distinguish them programatically, without the overhead of a class hierarchy.
 * 
 * @author Alan Gutierrez
 */
@SuppressWarnings("serial")
public class CodedDanger extends Danger {
    /** The error code. */
    private final int code;

    /**
     * Create a coded exception.
     * 
     * @param code
     *            The error code.
     * @param cause
     *            The cause.
     * @param arguments
     *            The format arguments.
     */
    public CodedDanger(int code, Throwable cause, Object...arguments) {
        super(null, Integer.toString(code), cause, arguments);
        this.code = code;
    }
    
    /**
     * Get the error code.
     * 
     * @return The error code.
     */
    public int getCode() {
        return code;
    }
}
