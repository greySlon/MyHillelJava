package com.slon.io;

/**
 * Created by Sergii on 07.04.2017.
 */
public interface Writable {
    long write(byte[] bytes, String fileName) throws Exception;
}
