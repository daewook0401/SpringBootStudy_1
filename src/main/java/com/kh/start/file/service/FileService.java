package com.kh.start.file.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    private final Path fileLocation;

    public FileService(){
        this.fileLocation = Paths.get("uploads").toAbsolutePath().normalize();
    }

    public String store(MultipartFile file){
        // 이름 바꾸는 메소드 호출 생략
        String originalFileName = file.getOriginalFilename();
        Path targetLocation = this.fileLocation.resolve(originalFileName);

        try{
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return "https//loaclhost/uploads" + originalFileName;
        } catch(IOException e){
            throw new RuntimeException("파일이 너무 크고 아름답습니다.");
        }
    }
}
