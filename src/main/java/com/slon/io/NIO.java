package com.slon.io;

import java.io.File;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.util.Random;

/**
 * Created by Sergii on 07.04.2017.
 */
public class NIO implements Copieble, Writable, Readable {
    @Override
    public void copyTo(String source, String destination) throws Exception {
        Files.copy(Paths.get(source), Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
    }

    @Override
    public long read(String fileName) throws Exception {
        return Files.readAllBytes(Paths.get(fileName)).length;
    }

    @Override
    public long write(byte[] bytes, String fileName) throws Exception {
        Files.write(Paths.get(fileName), bytes, StandardOpenOption.TRUNCATE_EXISTING);
        return bytes.length;
    }
}
