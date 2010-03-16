package com.goodworkalan.danger;

import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentMap;

/**
 * Good for extensible exceptions, because you can use the class name and
 * derived exception name as a namespace. Hmm...
 * <p>
 * Why not use the class name itself as a namespace? That is what I'm expecting.
 * <p>
 * Coded danger is extensible too. Just derive a type in your own package. You
 * can hide it. To avoid collisions in error codes, you can use a GitHub wiki
 * page to let people grab 100 value ranges.
 * <p>
 * Coded danger is especially useful for issues that you can recover from, but
 * not necessarily useful for errors that are programmer errors.
 * 
 * @author Alan Gutierrez
 */
@SuppressWarnings("serial")
public class ContextualDanger extends Danger {
    public ContextualDanger(ConcurrentMap<String, ResourceBundle> bundles, Class<?> contextClass, String code, Throwable cause) {
        super(bundles, getMessageKey(contextClass, code), null);
    }

    private static String getMessageKey(Class<?> contextClass, String code) {
        String className = contextClass.getCanonicalName();
        int dot = className.lastIndexOf('.');
        if (dot != -1) {
            className = className.substring(dot + 1);
        }
        return className + "/" + code;
    }
}
