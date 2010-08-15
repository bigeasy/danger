package com.goodworkalan.danger.test;

import static com.goodworkalan.danger.test.Dangerous.danger;
import static java.util.regex.Pattern.compile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

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
        }, Widget.class, "badness", null, "Eek! Something bad happened.");
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
        }, Widget.class, "badness", null, "Eek! Something bad happened.");
    }
    
    /**
     * No assertion triggered if the exception cause is null and the expected
     * cause is null.
     */
    @Test
    public void assertNoCause() {
        Dangerous.assertCause(new Danger(Dangerous.class, "none"), null);
    }

    /**
     * An assertion is triggered if the exception cause is not null and the
     * expected cause is null.
     */
    @Test(expectedExceptions = java.lang.AssertionError.class)
    public void failedAssertNoCause() {
        Dangerous.assertCause(new Danger(new IOException(), Dangerous.class, "none"), null);
    }

    /**
     * No assertion triggered if the exception cause is assignable to the
     * expected cause.
     */
    @Test
    public void assertCauseType() {
        Dangerous.assertCause(new Danger(new FileNotFoundException(), Dangerous.class, "none"), IOException.class);
    }
    
    @Test(expectedExceptions = java.lang.AssertionError.class)
    public void failedAssertCauseType() {
        Dangerous.assertCause(new Danger(new FileNotFoundException(), Dangerous.class, "none"), SQLException.class);
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
        }, Widget.class, "badness", null, compile("Eek! Something bad happened."));
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
        }, Widget.class, "badness", null, compile( "Eek! Something bad happened."));
    }
}
