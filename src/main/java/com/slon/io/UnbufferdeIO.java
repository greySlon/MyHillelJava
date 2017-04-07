package com.slon.io;

import java.io.*;

/**
 * Created by Sergii on 07.04.2017.
 */
public class UnbufferdeIO implements Copieble {
    @Override
    public void copyTo(String source, String destination) throws Exception {
        File fileIn = new File(source);
        File fileOut = new File(destination);

        try (InputStream fis = new FileInputStream(fileIn)) {
            try (OutputStream fos = new FileOutputStream(fileOut)) {
                int b = -1;
                while ((b = fis.read()) != -1) {
                    fos.write(b);
                }
            }
        }
    }
}
