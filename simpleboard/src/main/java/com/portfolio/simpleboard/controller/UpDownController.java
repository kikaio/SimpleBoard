package com.portfolio.simpleboard.controller;


import com.portfolio.simpleboard.dto.upload.UploadFileDTO;
import com.portfolio.simpleboard.dto.upload.UploadResultDTO;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    public Resource viweFileGET(@PathVariable String name) {
        return null;
    }
}
