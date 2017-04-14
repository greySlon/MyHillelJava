package com.slon.client_server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;

/**
 * Created by Sergii on 14.04.2017.
 */
public class Server {
    public static void main(String[] args) {
        new Server().start();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(30000)) {
            Socket socket = null;
            while (true) {
                socket = serverSocket.accept();
                processRequest(socket.getInputStream(), socket.getOutputStream());
                socket.shutdownOutput();
                socket.shutdownInput();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void echo(InputStream is, OutputStream os) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line = reader.readLine();

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
        writer.write(line);
        writer.flush();
    }

    public void processRequest(InputStream is, OutputStream os) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String fileName = "d:/" + reader.readLine().trim();
        System.out.println(fileName);
        Files.copy(Paths.get(fileName), os);
    }
}
