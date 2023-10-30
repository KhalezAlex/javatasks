package ru.spb.reshenie.javatasks.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigReader {
    public static Map<String, String> dbUrl() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/config.txt"));
        Map<String, String> config = new HashMap<>();
        String temp;
        while ((temp = br.readLine()) != null)
            config.put(temp.split(" ")[0], temp.split(" ")[1]);
        br.close();
        return config;
    }
}
