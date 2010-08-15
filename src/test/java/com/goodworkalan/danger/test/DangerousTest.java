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
    /**
     * Construct an instance of the {@link Dangerous} class to satisfy coverage.
     */
    @Test
    public void constructor() {
        new Dangerous();
    }

    /** Assert that the message is as expected and rethrow. */
    @Test(expectedExceptions = Danger.class)
    public void message() {
        danger(new Runnable() {
            public void run() {
                throw new Danger(Widget.class, "badness", "Eek");
            }
        }, Widget.class, "badness", "Eek! Something bad happened.");
    }
    
    /**
     * If no exception is thrown, we expect the expected exception mechanism of
     * the unit test framework to report it.
     */
    @Test
    public void messageUnexceptional() {
        danger(new Runnable() {
            public void run() {
            }
        }, Widget.class, "badness", "Eek! Something bad happened.");
    }
    
    /**
     * Assert that the message matches the given regular expression and rethrow.
     */
    @Test(expectedExceptions = Danger.class)
    public void regex() {
        danger(new Runnable() {
            public void run() {
                throw new Danger(Widget.class, "badness", "Eek");
            }
        }, Widget.class, "badness", compile("Eek! Something bad happened."));
    }
    
    /**
     * If no exception is thrown, we expect the expected exception mechanism of
     * the unit test framework to report it.
     */
    @Test
    public void regexUnexceptional() {
        danger(new Runnable() {
            public void run() {
            }
        }, Widget.class, "badness", compile( "Eek! Something bad happened."));
    }
}
