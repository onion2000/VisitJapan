package com.example.oniononion.comp4521project.Object;

import java.util.Hashtable;

/**
 * Created by oniononion on 7/3/2016.
 */
public class IntentHelper {

    private static IntentHelper _instance;
    private Hashtable<String, Object> _hash;

    private IntentHelper() {
        _hash = new Hashtable<String, Object>();
    }

    private static IntentHelper getInstance() {
        if (_instance == null) {
            _instance = new IntentHelper();
        }
        return _instance;
    }

    public static void addObjectWithKey(Object object, String key) {
        getInstance()._hash.put(key, object);
    }

    public static Object getObjectForKey(String key) {
        IntentHelper helper = getInstance();
        Object data = helper._hash.get(key);
        helper._hash.remove(key);
        helper = null;
        return data;
    }
}