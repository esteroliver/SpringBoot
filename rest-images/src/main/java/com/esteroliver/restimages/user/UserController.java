package com.esteroliver.restimages.user;

import com.esteroliver.restimages.profilepic.ProfilePicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    ProfilePicService profilePicService;

    @PostMapping("/user/{username}")
    public ResponseEntity<User> createUser(@PathVariable("username") String username, @RequestParam("file") MultipartFile profilePicture) throws IOException {
        User user = userService.createUser(username, profilePicture);
        ResponseEntity<User> responseEntity = new ResponseEntity<>(user, HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping("/user/{username}/profilepic")
    public ResponseEntity<byte[]> getProfilePicture(@PathVariable("username") String username) throws IOException {
        Optional<User> userOpt = userService.getUser(username);
        User user = userOpt.get();
        String imageName = user.getProfilepic();
        byte[] image = profilePicService.getProfilePic(user.getUsername(), imageName);
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(image, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        ResponseEntity<String> responseEntity = new ResponseEntity<>("Hello World!", HttpStatus.OK);
        return responseEntity;
    }
}
