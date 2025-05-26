package br.com.esteroliver.files.responses;

import lombok.Data;

@Data
public class UploudFileResponse {
    private String fileName;
    private String downloadUri;
    private String fileType;
    private long size;
}
