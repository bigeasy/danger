package com.goodworkalan.danger.test;

import static com.goodworkalan.danger.test.Dangerous.danger;
import static java.util.regex.Pattern.compile;

import org.testng.annotations.Test;

import com.goodworkalan.danger.Danger;

/**
 * Unit tests for the {@link Dangerous} class.
 *
 * @author Alan Gutierrez
 */
public class DangerousTest {
    /** Test against a message. */
    @Test
    public void message() {
        danger(new Runnable() {
            public void run() {
                throw new Danger(Widget.class, "badnesss", "Eek");
            }
        }, Widget.class, "badness", "Eek! Something bad happened.");
    }
    
    /** Test against a message and do not throw an exception. */
    @Test
    public void messageUnexceptional() {
        danger(new Runnable() {
            public void run() {
            }
        }, Widget.class, "badness", "Eek! Something bad happened.");
    }
    
    /** Test against a regular expression. */
    @Test
    public void regex() {
        danger(new Runnable() {
            public void run() {
                throw new Danger(Widget.class, "badnesss", "Eek");
            }
        }, Widget.class, "badness", compile( "Eek! Something bad happened."));
    }
    
    /** Test against a regular expression and do not throw an expression. */
    @Test
    public void regexUnexceptional() {
        danger(new Runnable() {
            public void run() {
            }
        }, Widget.class, "badness", compile( "Eek! Something bad happened."));
    }
}
