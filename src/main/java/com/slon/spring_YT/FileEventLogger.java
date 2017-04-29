package com.slon.spring_YT;

import com.google.common.base.Charsets;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import com.slon.spring_YT.interfaces.EventLogger;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by Sergii on 28.04.2017.
 */
public class FileEventLogger implements EventLogger {
    private String fileName;

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void logEvent(Event event) {
        try {
//            Files.asCharSink(new File(fileName), Charsets.UTF_8, FileWriteMode.APPEND).write(event.toString());
            Files.append("\r\n" + event.toString(), new File(fileName), Charsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
