package com.bookCRUD.services;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    public String fileUpload(@RequestParam("file") MultipartFile file) ;
}
