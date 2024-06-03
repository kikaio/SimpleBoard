package com.portfolio.simpleboard.controller;


import com.portfolio.simpleboard.dto.upload.UploadFileDTO;
import com.portfolio.simpleboard.dto.upload.UploadResultDTO;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@Log4j2
public class UpDownController {

    @Value("${org.simpleboard.upload.path}")
    private String uploadPath;

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<UploadResultDTO> upload(UploadFileDTO uploadFileDTO) {
        log.info(uploadFileDTO);
        List<UploadResultDTO> retDtoList = new ArrayList<>();

        if(uploadFileDTO.getFiles() == null) {
            return null;
        }
        uploadFileDTO.getFiles().forEach(f->{
            log.info(f.getOriginalFilename());
            String uuid = UUID.randomUUID().toString();
            String oriName = f.getOriginalFilename();
            Path savePath = Paths.get("%s_%s".formatted(uploadPath, oriName));
            boolean isImg = false;
            try {
                isImg = Files.probeContentType(savePath).startsWith("image");
                if(isImg) {
                    File thumb = new File(uploadPath, "s_%s_%s".formatted(uuid, oriName));
                    Thumbnailator.createThumbnail(savePath.toFile(), thumb, 200, 300);
                }
                f.transferTo(savePath);
            } catch(IOException e) {
                e.printStackTrace();
            }

            retDtoList.add(UploadResultDTO.builder()
                            .uuid(uuid)
                            .img(isImg)
                            .fileName(oriName)
                            .build()
            );
        });
        return retDtoList;
    }

    @GetMapping("/view/{name}")
    public ResponseEntity<Resource> viweFileGET(@PathVariable String name) {
        Resource resource = new FileSystemResource("%s%s%s".formatted(uploadPath, File.separator, name));
        String resourceName = resource.getFilename();
        HttpHeaders headers = new HttpHeaders();

        try {
            headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
        } catch(Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(headers).body(resource);
    }

    @DeleteMapping("/remove/{name}")
    public Map<String, Boolean> removeFile(@PathVariable String name) {
        Resource resource = new FileSystemResource("%s%s%s".formatted(uploadPath, File.separator, name));

        String resourceName = resource.getFilename();
        Map<String, Boolean> resultMap = new HashMap<>();
        boolean isRemoved = false;


        try {
            isRemoved = resource.getFile().delete();
            String contentType = Files.probeContentType(resource.getFile().toPath());
            if(contentType.startsWith("image")) {
                File thumbnail = new File("%s%ss_%s".formatted(uploadPath, File.separator, name));
                thumbnail.delete();
            }
        } catch(IOException e) {
            log.error(e);
        }

        resultMap.put("result", isRemoved);
        return resultMap;
    }
}
