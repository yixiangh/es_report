package com.seari.es_report.util;

import org.springframework.core.env.Environment;

public class PropertiesUtils {

    private static Environment env = null;

    public static void setEnvironment(Environment env){
        PropertiesUtils.env = env;
    }

    public static String getString(int key){
        return env.getProperty(key+"");
    }

    public static String getString(String key){
        return env.getProperty(key);
    }

    public static int getInt(String key){
        return Integer.parseInt(env.getProperty(key));
    }
    public static int getInt(int key){
        return Integer.parseInt(env.getProperty(key+""));
    }
}
