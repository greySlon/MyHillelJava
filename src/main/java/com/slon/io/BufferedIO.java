package com.slon.io;

import com.slon.io.interfaces.Copieble;
import com.slon.io.interfaces.Readable;
import com.slon.io.interfaces.Writable;
import com.slon.utils.annotation.Profiling;

import java.io.*;

/**
 * Created by Sergii on 07.04.2017.
 */
@Profiling
public class BufferedIO implements Copieble, Writable, Readable {
    @Override
    public void copyTo(String source, String destination) throws Exception {
        File fileIn = new File(source);
        File fileOut = new File(destination);

        try (InputStream fis = new BufferedInputStream(new FileInputStream(fileIn))) {
            try (OutputStream fos = new BufferedOutputStream(new FileOutputStream(fileOut))) {
                int b = -1;
                while ((b = fis.read()) != -1) {
                    fos.write(b);
                }
            }
        }
    }

    @Override
    public byte[] read(String fileName) throws Exception {
        File fileIn = new File(fileName);
        byte[] bytes;
        long lenght = fileIn.length();
        if (lenght > Integer.MAX_VALUE) {
            throw new RuntimeException("Too large file");
        } else {
            bytes = new byte[(int) lenght];
        }
        int i = 0;
        try (InputStream fis = new BufferedInputStream(new FileInputStream(fileIn))) {
            byte b = -1;
            while ((b = (byte) fis.read()) != -1)
                bytes[i++] = b;
        }
        return bytes;
    }

    @Override
    public long write(byte[] bytes, String fileName) throws Exception {
        File fileOut = new File(fileName);
        try (OutputStream fos = new BufferedOutputStream(new FileOutputStream(fileOut))) {
            fos.write(bytes);
        }
        return bytes.length;
    }
}
