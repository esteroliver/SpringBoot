package com.esteroliver.restimages.user;

import com.esteroliver.restimages.profilepic.ProfilePicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    ProfilePicService profilePicService;

    @PostMapping("/user/{username}")
    public User createUser(@PathVariable String username, @RequestParam MultipartFile profilePicture) throws IOException {
        User user = userService.createUser(username, profilePicture);
        return user;
    }

    @GetMapping("/user/{username}/profilepic")
    public byte[] getProfilePicture(@PathVariable String username) throws IOException {
        Optional<User> userOpt = userService.getUser(username);
        User user = userOpt.get();
        String imageName = user.getProfilepic();
        byte[] image = profilePicService.getProfilePic(user.getUsername(), imageName);
        return image;
    }
}
