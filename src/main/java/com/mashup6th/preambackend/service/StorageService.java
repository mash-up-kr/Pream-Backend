package com.mashup6th.preambackend.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public interface StorageService {

    String upload(MultipartFile multipartFile, String dirName) throws IOException;

    String upload(File file, String dirName);

    String put(File file, String fileName);

    void remove(File file);

    Optional<File> convert(MultipartFile file) throws IOException;
}
