package com.goodworkalan.danger;

/**
 * An excpetion that has string message codes, that uses a non-nested class to
 * indicate the package where the message bundle is located.
 * <p>
 * The actual message key used to obtain a message from the message bundle is
 * qualifed with the class name of the context class. The class name is
 * catenated to the string code and separated by a slash, so that created an
 * contextual exception with <code>com.acme.Account</code> and an string code of
 * <code>overdrawn</code> would produce the message key
 * <code>Account/overdrawn</code>. This allows a single bundle in a package to
 * be used with multiple context classes from that package, without having to
 * use unique string codes across the entire package.
 * 
 * @author Alan Gutierrez
 */
@SuppressWarnings("serial")
public class ContextualDanger extends Danger {
	/** The message key. */
    private final String messageKey;

    /**
     * Create a contextual exception with the given context class, the given
     * string error code and the given positioned arguments.
     * 
     * @param contextClass
     *            The error context.
     * @param code
     *            The error code.
     * @param arguments
     *            The positioned arguments to the error format string.
     */
    public ContextualDanger(Class<?> contextClass, String code, Throwable cause, Object...arguments) {
        super(contextClass, getMessageKey(contextClass, code), cause, arguments);
        this.messageKey = getMessageKey(contextClass, code);
    }
    
	/**
	 * Get the message key, which is the context class name catenated with the
	 * string code.
	 * 
	 * @return The message key.
	 */
    public String getMessageKey() {
        return messageKey;
    }

	/**
	 * Create the qualified message key by contenting the context class name
	 * with the message code.
	 * 
	 * @param contextClass
	 *            The context class.
	 * @param code
	 *            The message code.
	 * @return The qualified message key.
	 */
    private static String getMessageKey(Class<?> contextClass, String code) {
        String className = contextClass.getCanonicalName();
        int dot = className.lastIndexOf('.');
        if (dot != -1) {
            className = className.substring(dot + 1);
        }
        return className + "/" + code;
    }
}
