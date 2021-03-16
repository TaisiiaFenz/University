package controller;

import com.google.gson.*;

import javax.servlet.ServletRequest;
import java.io.BufferedReader;
import java.lang.reflect.Type;
import java.time.LocalDate;

public interface ReadData {
    static StringBuilder readData(ServletRequest request) {
        StringBuilder jb = new StringBuilder();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception e) { /*report an error*/ }
        return jb;
    }
}
