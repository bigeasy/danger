package com.goodworkalan.danger;

import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentMap;

@SuppressWarnings("serial")
public class CodedDanger extends Danger {
    /** The error code. */
    private final int code;
    
    /**
     * Create a coded danger 
     * @param bundles
     * @param code
     * @param cause
     */
    public CodedDanger(ConcurrentMap<String, ResourceBundle> bundles, int code, Throwable cause, Object...arguments) {
        super(bundles, null, Integer.toString(code), cause, arguments);
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
