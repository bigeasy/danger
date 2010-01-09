package com.goodworkalan.danger.test;

import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.goodworkalan.danger.CodedDanger;

@SuppressWarnings("serial")
public class TestCodedDanger extends CodedDanger {
    private final static ConcurrentMap<String, ResourceBundle> bundles = new ConcurrentHashMap<String, ResourceBundle>();

    public TestCodedDanger(int code, Throwable cause) {
        super(bundles, code, cause);
    }
}
