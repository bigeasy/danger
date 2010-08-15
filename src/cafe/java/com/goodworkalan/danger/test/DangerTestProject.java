package com.goodworkalan.danger.test;

import com.goodworkalan.cafe.ProjectModule;
import com.goodworkalan.cafe.builder.Builder;
import com.goodworkalan.cafe.outline.JavaProject;

/**
 * Builds the project definition for Danger Test.
 *
 * @author Alan Gutierrez
 */
public class DangerTestProject implements ProjectModule {
    /**
     * Build the project definition for Danger Test.
     *
     * @param builder
     *          The project builder.
     */
    public void build(Builder builder) {
        builder
            .cookbook(JavaProject.class)
                .produces("com.github.bigeasy.danger/danger-test/0.3.0.0")
                .depends()
                    .production("com.github.bigeasy.danger/danger/0.+3")
                    .production("org.testng/testng-jdk15/5.10")
                    .end()
                .end()
            .end();
    }
}
