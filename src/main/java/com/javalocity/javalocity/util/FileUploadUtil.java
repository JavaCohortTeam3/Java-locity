package com.javalocity.javalocity.util;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.*;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {

    public static void saveFile(String uploadDir, String fileName,
                                MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }

        public static void deleteImg(String dir, String filename) throws IOException {
            Path uploadPath = Paths.get(dir);
            Path filePath;
            if (Files.exists(uploadPath)) {
                filePath = uploadPath.resolve(filename);
                try {
                    Files.deleteIfExists(filePath);
                } catch (IOException e) {
                    throw new IOException("Image could not be deleted");
                }
            }

        }
//    public static Resource load(String filename) {
//
//        try {
//            Path file = Paths.get(filename);
//            Resource resource = new UrlResource(file.toUri());
//
//            if (resource.exists() || resource.isReadable()) {
//                return resource;
//            } else {
//                throw new RuntimeException("Could not read the file!");
//            }
//        } catch (MalformedURLException e) {
//            throw new RuntimeException("Error: " + e.getMessage());
//        }
//    }
}