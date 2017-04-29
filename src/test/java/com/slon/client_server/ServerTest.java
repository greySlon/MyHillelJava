package com.slon.client_server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Sergii on 14.04.2017.
 */
class ServerTest {
    Server server;
    InputStream is;
    OutputStream os;

    @BeforeEach
    void setUp() throws IOException {
        server = new Server();
        is = Files.newInputStream(Paths.get("C:\\Users\\Sergii\\IdeaProjects\\MyHillelJavaUltimate\\src\\main\\java\\com\\slon\\serialization\\test"));
        os = Files.newOutputStream(Paths.get("d:/json-2.txt"));
    }

    @Test
    void processRequest() throws IOException {
        server.processRequest(is, os);
    }

}