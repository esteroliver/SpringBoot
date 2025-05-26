package br.com.esteroliver.files.files;

import br.com.esteroliver.files.exceptions.filesExceptions.FileStorageException;
import br.com.esteroliver.files.responses.UploudFileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/file")
public class FileStorageController {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/uploud")
    public ResponseEntity<UploudFileResponse> uploudFile(@RequestParam("file")MultipartFile file) throws FileStorageException {
        String filename = fileStorageService.storeFile(file);
        String downloaduri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/files/").path(filename).toUriString();

        UploudFileResponse uploudResponse = new UploudFileResponse();
        uploudResponse.setFileName(filename);
        uploudResponse.setDownloadUri(downloaduri);
        uploudResponse.setFileType(file.getContentType());
        uploudResponse.setSize(file.getSize());

        return ResponseEntity.status(HttpStatus.CREATED).body(uploudResponse);
    }
}
