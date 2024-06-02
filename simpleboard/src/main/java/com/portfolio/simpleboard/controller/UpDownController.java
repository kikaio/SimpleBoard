package com.portfolio.simpleboard.controller;


import com.portfolio.simpleboard.dto.upload.UploadFileDTO;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@Log4j2
public class UpDownController {

    @Value("${org.simp[leboard.upload.path}")
    private String uploadPath;

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(UploadFileDTO uploadFileDTO) {
        log.info(uploadFileDTO);

        if(uploadFileDTO.getFiles() == null) {
            return null;
        }
        uploadFileDTO.getFiles().forEach(f->{
            log.info(f.getOriginalFilename());
            String uuid = UUID.randomUUID().toString();
            String oriName = f.getOriginalFilename();
            Path savePath = Paths.get("%s_%s".formatted(uploadPath, oriName));

            try {
                if(Files.probeContentType(savePath).startsWith("image")) {
                    File thumb = new File(uploadPath, "s_%s_%s".formatted(uuid, oriName));
                    Thumbnailator.createThumbnail(savePath.toFile(), thumb, 200, 300);
                }
                f.transferTo(savePath);
            } catch(IOException e) {
                e.printStackTrace();
            }
        });
        return null;
    }
}
