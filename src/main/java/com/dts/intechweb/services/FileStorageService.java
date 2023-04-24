package com.dts.intechweb.services;
import org.springframework.core.io.Resource;

public interface FileStorageService {
    void storeFile(String filename, Resource fileResource);
    Resource loadFile(String filename);
    void deleteFile(String filename);
}
