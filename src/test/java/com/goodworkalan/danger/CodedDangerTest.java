package com.goodworkalan.danger;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.annotations.Test;

import com.goodworkalan.danger.test.TestCodedDanger;

public class CodedDangerTest {
    /** Test the creation of a coded exception. */
    @Test
    public void create() {
        try {
            try {
                throw new IOException();
            } catch (IOException e) {
                throw new TestCodedDanger(101, e);
            }
        } catch (TestCodedDanger e) {
            assertEquals(e.getCode(), 101);
        }
    }
}
