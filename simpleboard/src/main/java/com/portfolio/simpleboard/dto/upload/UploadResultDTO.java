package com.portfolio.simpleboard.dto.upload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadResultDTO {
    private String uuid;

    private String fileName;

    private boolean img;

    public String getLink() {
        if(img) {
            return "s_%s_%s".formatted(uuid, fileName);
        }
        else {
            return "%s_%s".format(uuid, fileName);
        }
    }
}
