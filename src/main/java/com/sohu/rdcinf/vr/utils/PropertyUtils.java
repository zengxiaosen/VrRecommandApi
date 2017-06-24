package com.sohu.rdcinf.vr.utils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * Created by zengxiaosen on 2017/6/22.
 */
public class PropertyUtils {
    private static Set<String> paths = new HashSet<>();
    private static Properties confProperties = new Properties();
    static {
        if(paths != null && paths.size() > 0){
            for(String path: paths){
                load(path);
            }
        }
    }

    public static synchronized void load(String path){
        if(path == null || path.length() == 0 || paths.contains(path)){
            return;
        }
        try{
            confProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(path));
        }catch (IOException e){

        }
        paths.add(path);
    }

    public static String getProperty(String key, String filePath){
        if(key == null || key.length() == 0){
            return null;
        }
        if(!(filePath == null || filePath.length() == 0) && !paths.contains(filePath)){
            load(filePath);
        }
        return confProperties.containsKey(key) ? (String) confProperties.get(key) : null;
    }

    public static String getProperty(String key) {
        return confProperties.containsKey(key) ? (String) confProperties.get(key) : null;
    }

    public static Properties getAllProperties(String filePath){
        if(filePath != null && filePath.length() > 0 && !paths.contains(filePath)){
            load(filePath);
        }
        return confProperties;
    }




}
