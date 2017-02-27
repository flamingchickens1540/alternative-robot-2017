package org.team1540.robot2017.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Preferences {
    private String filename;
    private Map<String, String> data = new HashMap<>(); 
    
    public Preferences(String filename) {
        this.filename = filename;
    }
    
    public void save() throws IOException {
        StringBuilder builder = new StringBuilder();
        data.forEach((k, v) -> {
            builder.append(k + "=" + v + "\n");
        });
        Files.write(Paths.get(filename), builder.toString().getBytes());
    }
    
    public void load() throws IOException {
        Stream<String> lines = Files.lines(Paths.get(filename));
        lines.forEach(line -> {
           String[] keyValue = line.split("#")[0].split("=");
           if (keyValue.length == 2) {
               data.put(keyValue[0].replaceAll("\\s", ""), keyValue[1].replaceAll("\\s", ""));
           }
        });
        lines.close();
    }
    
    public void put(String key, double value) {
        data.put(key, Double.toString(value));
    }
    
    public void put(String key, long value) {
        data.put(key, Long.toString(value));
    }
    
    public void put(String key, boolean value) {
        data.put(key, Boolean.toString(value));
    }
    
    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(data.get(key));
    }
    
    public double getDouble(String key) {
        return Double.parseDouble(data.get(key));
    }
    
    public long getLong(String key) {
        return Long.parseLong(data.get(key));
    }
}
