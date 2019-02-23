package com.bookCRUD.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class UploadController {


    private static String UPLOADED_FOLDER = "c:/users/Shubham/";

    @RequestMapping(method = RequestMethod.POST,value = "/uploadFile")
    public String fileUpload(@RequestParam("file") MultipartFile file){
        if (file.isEmpty()) {
            return "Please upload a file";
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return "Upload Successful";

    }
}
