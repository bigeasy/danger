package com.goodworkalan.danger;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.annotations.Test;

import com.goodworkalan.danger.test.TestContextualDanger;

public class ContextualDangerTest {
    /** Test the creation of a contextual exception. */
    @Test
    public void create() {
        try {
            try {
                throw new IOException();
            } catch (IOException e) {
                throw new TestContextualDanger(TestContextualDanger.class, "danger", e).put("a", "a");
            }
        } catch (TestContextualDanger e) {
            assertEquals(e.getMessage(), "Contextual exception [a].");
            assertEquals(e.getData().get("a"), "a");
        }
    }
    
    /** Test the creation of a contextual exception for a class with no package. */
    @Test
    public void createDefault() throws ClassNotFoundException {
        try {
            try {
                throw new IOException();
            } catch (IOException e) {
                throw new TestContextualDanger(Class.forName("OutOfContext", true, getClass().getClassLoader()), "danger", e).put("a", "a");
            }
        } catch (TestContextualDanger e) {
            assertEquals(e.getMessage(), "You cannot use a context class that references the default package.");
            assertEquals(e.getData().get("a"), "a");
        }
    }
}
