package com.goodworkalan.danger;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

import com.goodworkalan.danger.test.Widget;

/**
 * Unit tests for the {@link Danger} class.
 *
 * @author Alan Gutierrez
 */
public class DangerTest {
    /** Test message. */
    @Test
    public void message() { 
        try {
            throw new Danger(Widget.class, "badness", "Eek");
        } catch (Danger e) {
            assertEquals(e.getMessage(), "Eek! Something bad has happened.");
        }
    }
    
    /** Test in. */
    @Test
    public void in() { 
        try {
            throw new Danger(Widget.class, "badness", "Eek");
        } catch (Danger e) {
            assertTrue(e.in(Widget.class));
        }
    }
    
    /** Test is. */
    @Test
    public void is() { 
        try {
            throw new Danger(Widget.class, "badness", "Eek");
        } catch (Danger e) {
            assertTrue(e.is("badness"));
        }
    }
    
    /** Test throw if not match context. */
    @Test
    public void throwIfNotMatchMatchContext() { 
        try {
            throw new Danger(Widget.class, "badness", "Eek");
        } catch (Danger e) {
            e.throwIfNot(Widget.class);
        }
    }
    
    /** Test throw if not match context and code. */
    @Test
    public void throwIfNotMatchMatchContextAndCode() { 
        try {
            throw new Danger(Widget.class, "badness", "Eek");
        } catch (Danger e) {
            e.throwIfNot(Widget.class, "badness");
        }
    }
    
    /** Test throw if not match context failure. */
    @Test(expectedExceptions = Danger.class)
    public void throwBecauseNotContext() { 
        try {
            throw new Danger(Widget.class, "badness", "Eek");
        } catch (Danger e) {
            e.throwIfNot(String.class);
        }
    }
    
    /** Test throw if not match context failure. */
    @Test(expectedExceptions = Danger.class)
    public void throwBecauseNotContextAndCode() { 
        try {
            throw new Danger(Widget.class, "badness", "Eek");
        } catch (Danger e) {
            e.throwIfNot(Widget.class, "goodness");
        }
    }
    
    /** Test missing bundle. */
    @Test
    public void missingBundle() { 
        try {
            throw new Danger(String.class, "badness");
        } catch (Danger e) {
            assertTrue(e.getMessage().startsWith("Cannot load message key [String/badness] from bundle [java.lang.exceptions] becuase "));
        }
    }
}
