package br.com.esteroliver.files.service;

import br.com.esteroliver.files.config.FileStorageConfig;
import br.com.esteroliver.files.exception.FileStorageException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService{
    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageConfig fileStorageConfig) throws FileStorageException {

        this.fileStorageLocation = Paths.get(fileStorageConfig.getUploudDir())
                .toAbsolutePath()
                .normalize();

        try{
          Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e){
            throw new FileStorageException("Could not create the directory where the uploud files will be stored.", e);
        }
    }

    public String storeFile(MultipartFile file) throws FileStorageException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try{
            if(fileName.contains("..")){
                throw new FileStorageException("Sorry! Filename contains invalid path sequence.");
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (Exception e){
            throw new FileStorageException("Could not store file " + fileName + ".", e);
        }
    }

    public void getImage(String fileName){
        try{
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            //todo
        }
        catch(Exception exc){

        }
    }
}
