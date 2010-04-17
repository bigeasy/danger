package com.goodworkalan.danger;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentMap;

import com.goodworkalan.verbiage.Message;

@SuppressWarnings("serial")
public class Danger extends RuntimeException {
    /** The formatted message. */
    private final Message message;
    
    /** The map of diagnostic data. */
    private final Map<Object, Object> data;
    
    public Danger(ConcurrentMap<String, ResourceBundle> bundles, Class<?> bundleContext, String messageKey, Throwable cause, Object...arguments) {
        super(null, cause);
        this.data = Message.position(new HashMap<Object, Object>(), arguments); 
        this.message = new Message(bundles, getBundleContext(bundleContext).getCanonicalName(), "exceptions", messageKey, this.data);
    }
    
    private Class<?> getBundleContext(Class<?> bundleContext) {
        return bundleContext == null ? getClass() : bundleContext;
    }
    
    public Danger put(Object name, Object value) {
        data.put(name, value);
        return this;
    }
    
    public Map<Object, Object> getData() {
        return data;
    }
    
    @Override
    public String getMessage() {
        return message.toString();
    }
}
