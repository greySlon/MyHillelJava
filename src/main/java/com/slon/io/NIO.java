package com.slon.io;

import com.slon.io.interfaces.Copieble;
import com.slon.io.interfaces.Readable;
import com.slon.io.interfaces.Writable;
import com.slon.utils.annotation.Profiling;

import java.nio.file.*;

/**
 * Created by Sergii on 07.04.2017.
 */
@Profiling
public class NIO implements Copieble, Writable, Readable {
    @Override
    public void copyTo(String source, String destination) throws Exception {
        Files.copy(Paths.get(source), Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
    }

    @Override
    public byte[] read(String fileName) throws Exception {
        return Files.readAllBytes(Paths.get(fileName));
    }

    @Override
    public long write(byte[] bytes, String fileName) throws Exception {
        Files.write(Paths.get(fileName), bytes, StandardOpenOption.TRUNCATE_EXISTING);
        return bytes.length;
    }
}
