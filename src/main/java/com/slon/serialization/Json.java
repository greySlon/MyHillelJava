package com.slon.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Sergii on 13.04.2017.
 */
public class Json {
    static ObjectMapper mapper = new ObjectMapper();

    public static void objectToFile(Object o, File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, o);
    }

    public static <T> T jsonToObjectFromFile(Class<T> cls, File file) throws IOException {
        return mapper.readValue(file, cls);
    }

    public static String objectToJson(Object o) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(o);
    }

    public static <T> T jsonToObjectFromStream(Class<T> cls, InputStream is) throws IOException {
        return mapper.readValue(is, cls);
    }

    public static <T> T jsonToObject(Class<T> cls, String jsonString) throws IOException {
        return mapper.readValue(jsonString, cls);
    }

    public static void main(String[] args) throws Exception {
        File file = new File("d:/json.txt");
        Student student = new Student();
        student.add("rrrr");
        student.add("tttt");
        objectToFile(student, file);
        System.out.println(jsonToObjectFromFile(Student.class, file));
        String json = objectToJson(student);
        System.out.println(json);
        System.out.println(jsonToObject(Student.class, json));
    }
}


