package com.epam.service;

import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

public class TestDataReader {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty("environment"));

    public static String getTestData(String key){
        return resourceBundle.getString(key);
    }

    public static String getTestDataRus(String key) throws UnsupportedEncodingException {
        return new String(TestDataReader.getTestData(key)
                .getBytes("ISO-8859-1"), "UTF-8");
    }
}
