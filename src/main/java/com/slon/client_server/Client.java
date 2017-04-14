package com.slon.client_server;

import com.slon.serialization.Json;
import com.slon.serialization.Student;
import com.slon.utils.Utils;

import java.io.*;
import java.net.Socket;

/**
 * Created by Sergii on 14.04.2017.
 */
public class Client {
    public static void main(String[] args) throws Exception {
        String resourceName = "student";
        Student student = getResource(resourceName, Student.class);
        System.out.println(student);
    }

    public static <T> T getResource(String resourseName, Class<T> cls) throws Exception {
        try (Socket socket = new Socket("localhost", 30000)) {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write(resourseName);
            writer.flush();
            socket.shutdownOutput();

            InputStream is = socket.getInputStream();

            return Json.jsonToObjectFromStream(cls, is);
        }
    }
}
