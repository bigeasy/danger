package com.goodworkalan.danger.mix;

import com.goodworkalan.mix.ProjectModule;
import com.goodworkalan.mix.builder.Builder;
import com.goodworkalan.mix.cookbook.JavaProject;

/**
 * Builds the project definition for Danger.
 *
 * @author Alan Gutierrez
 */
public class DangerProject implements ProjectModule {
    /**
     * Build the project definition for Danger.
     *
     * @param builder
     *          The project builder.
     */
    public void build(Builder builder) {
        builder
            .cookbook(JavaProject.class)
                .produces("com.github.bigeasy.danger/danger/0.1.0.2")
                .depends()
                    .production("com.github.bigeasy.verbiage/verbiage/0.+1")
                    .development("org.testng/testng-jdk15/5.10")
                    .end()
                .end()
            .end();
    }
}
