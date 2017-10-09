package com.cai.news.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class ValidateUtil {
    public static boolean validateNullOrEmpty(HashMap<String,String> parameters){
        Set<String> keys=parameters.keySet();
        Iterator<String> iterator=keys.iterator();
        while (iterator.hasNext()){
            String key=iterator.next();
            String value=parameters.get(key);
            if(value==null||value.trim().equals("")){
                return false;
            }
        }
        return true;
    }
}
