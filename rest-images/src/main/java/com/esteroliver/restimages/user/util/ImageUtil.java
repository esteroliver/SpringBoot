package com.esteroliver.restimages.user.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class ImageUtil {
    public static boolean imageUploud(MultipartFile file) {
        boolean result = false;

        if(!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            try {
                String directoryPath = "/home/gcti-desenvolvimento/Documentos/estudos/SpringBoot/rest-images/src/main/resources/static/images/profilepic-uplouds";
                File dir = new File(directoryPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                File image = new File(dir.getAbsolutePath() + File.separator + fileName);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(image));

                stream.write(file.getBytes());
                stream.close();

                result = true;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        else {
            System.out.println("File is empty");
        }

        return result;
    }
}
