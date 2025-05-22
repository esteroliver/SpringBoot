package com.esteroliver.restimages.profilepic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ProfilePicService {
    @Autowired
    ProfilePicRepository profilePicRepository;

    public String saveProfilePic(String dir, MultipartFile imageUploud) throws IOException {
        ProfilePic profilePic = new ProfilePic();
        profilePic.setId(UUID.randomUUID());
        profilePic.setImage(imageUploud.getBytes());
        profilePic.setFilename(imageUploud.getOriginalFilename());
        profilePicRepository.save(profilePic);

        String fileName = UUID.randomUUID().toString() + "_" + imageUploud.getOriginalFilename();

        Path root = Paths.get("images");
        Path dirPath = Paths.get(root.getFileName() + "/" + dir);

        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);
        }

        Path filePath = dirPath.resolve(fileName);

        Files.copy(imageUploud.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return fileName;
    }

    public byte[] getProfilePic(String dir, String imageName) throws IOException {
        Path imagePath = Path.of("images", dir, imageName);

        if (!Files.exists(imagePath)) {
            byte[] imageBytes = Files.readAllBytes(imagePath);
            return imageBytes;
        } else {
            return null;
        }
    }
}