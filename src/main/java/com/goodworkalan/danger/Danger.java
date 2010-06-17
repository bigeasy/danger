package com.goodworkalan.danger;

import java.util.HashMap;
import java.util.Map;

import com.goodworkalan.verbiage.Message;

/**
 * Base class for exceptions with arbitrary diagnostic data and formatted
 * exception messages that reference the diagnostic data.
 * 
 * @author Alan Gutierrez
 */
@SuppressWarnings("serial")
public class Danger extends RuntimeException {
    /** The formatted message. */
    private final Message message;
    
    /** The map of format arguments. */
    private final Map<Object, Object> data;

	/**
	 * Create an exception with the given context class, message key and cause,
	 * using the given format arguments to create the exception message.
	 * 
	 * @param contextClass
	 *            The context class.
	 * @param messageKey
	 *            The key of the message in the message bundle.
	 * @param cause
	 *            The cause.
	 * @param arguments
	 *            The format arguments.
	 */
    public Danger(Class<?> contextClass, String messageKey, Throwable cause, Object...arguments) {
        super(null, cause);
        this.data = Message.position(new HashMap<Object, Object>(), arguments); 
        this.message = new Message(getBundleContext(contextClass).getCanonicalName(), "exceptions", messageKey, this.data);
    }

	/**
	 * Get the context class using either the given context class or this class,
	 * if the given context class is null.
	 * 
	 * @param contextClass
	 *            The context class.
	 * @return The context class or this class if the context class is null.
	 */
    private Class<?> getBundleContext(Class<?> contextClass) {
        return contextClass == null ? getClass() : contextClass;
    }

	/**
	 * Put the given name and value into the map of exception data. The map of
	 * exception data can be referenced from the format arguments of verbiage.
	 * 
	 * @param name
	 *            The name.
	 * @param value
	 *            The value.
	 * @return This exception to permit method chaining.
	 */
    public Danger put(Object name, Object value) {
        data.put(name, value);
        return this;
    }

	/**
	 * Get the map of exception information.
	 * 
	 * @return The map of exception information.
	 */
    public Map<Object, Object> getData() {
        return data;
    }
    
    /**
     * Get the formatted exception message.
     * 
     * @return The exception message.
     */
    @Override
    public String getMessage() {
        return message.toString();
    }
}
