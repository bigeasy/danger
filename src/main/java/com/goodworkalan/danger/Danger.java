package com.goodworkalan.danger;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Base class for exceptions with arbitrary diagnostic data and formatted
 * exception messages that reference the diagnostic data.
 * 
 * @author Alan Gutierrez
 */
public class Danger extends RuntimeException {
    /** Serial version id. */
    private static final long serialVersionUID = -1L;
    
    /** The message context class. */
    public final Class<?> contextClass;
    
    /** The message error code. */
    public final String code;

    /**
     * Create an exception with the given context class and message code, using
     * the given format arguments to create the exception message.
     * 
     * @param cause
     *            The cause.
     * @param contextClass
     *            The context class.
     * @param code
     *            The key of the message in the message bundle.
     * @param arguments
     *            The format arguments.
     */
    public Danger(Class<?> contextClass, String code, Object...arguments) {
        this(null, contextClass, code, arguments);
    }

    /**
     * Create an exception with the given context class, message key and cause,
     * using the given format arguments to create the exception message.
     * 
     * @param cause
     *            The cause.
     * @param contextClass
     *            The context class.
     * @param code
     *            The key of the message in the message bundle.
     * @param arguments
     *            The format arguments.
     */
    public Danger(Throwable cause, Class<?> contextClass, String code, Object...arguments) {
        super(format(contextClass, code, arguments), cause);
        this.contextClass = contextClass; 
        this.code = code;
    }


    /**
     * Format the exception message using the message arguments to format the
     * message found with the message key in the message bundle found in the
     * package of the given context class.
     * 
     * @param contextClass
     *            The context class.
     * @param code
     *            The error code.
     * @param arguments
     *            The format message arguments.
     * @return The formatted message.
     */
    public final static String _(Class<?> contextClass, String code, Object...arguments) {
        String baseName = contextClass.getPackage().getName() + ".exceptions";
        String messageKey = contextClass.getSimpleName() + "/" + code;
        try {
            ResourceBundle bundle = ResourceBundle.getBundle(baseName, Locale.getDefault(), Thread.currentThread().getContextClassLoader());
            return String.format((String) bundle.getObject(messageKey), arguments);
        } catch (Exception e) {
            return String.format("Cannot load message key [%s] from bundle [%s] becuase [%s].", messageKey, baseName, e.getMessage());
        }
    }

    /**
     * Format the exception message using the message arguments to format the
     * message found with the message key in the message bundle found in the
     * package of the given context class.
     * 
     * @param contextClass
     *            The context class.
     * @param code
     *            The error code.
     * @param arguments
     *            The format message arguments.
     * @return The formatted message.
     */
    public static String format(Class<?> contextClass, String code, Object...arguments) {
        return _(contextClass, code, arguments);
    }

    /**
     * Return true if the exception is in the context of the given context
     * class.
     * 
     * @param contextClass
     *            The context class.
     * @return true if the exception is in the context of the given context
     *         class.
     * 
     */
    public boolean in(Class<?> contextClass) {
        return this.contextClass.equals(contextClass);
    }

    /**
     * Return true if the message code is equal to the give message code.
     * 
     * @param code
     *            The message code.
     * @return True if the message code is equal to the give message code.
     */
    public boolean is(String code) {
        return this.code.equals(code);
    }

    /**
     * Throw this exception if the context class is not the given class or if
     * the list of codes is not empty and no code equals a code in the list of
     * codes.
     * 
     * @param contextClass
     *            The context class.
     * @param codes
     *            The list of codes.
     */
    public void throwIfNot(Class<?> contextClass, String...codes) {
        boolean raise = true;
        if (this.contextClass.equals(contextClass)) {
            int stop = codes.length;
            raise = stop > 0;
            if (raise) {
                for (int i = 0; raise && i < stop; i++) {
                    if (codes[i].equals(code)) {
                        raise = false;
                    }
                }
            }
        }
        if (raise) {
            throw this;
        }
    }
}
