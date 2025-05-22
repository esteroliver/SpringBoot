package com.esteroliver.restimages.user;

import com.esteroliver.restimages.profilepic.ProfilePicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProfilePicService profilePicService;

    public User createUser(String username, MultipartFile profilepic) throws IOException {
        User user = new User();
        user.setUsername(username);

        String picName = profilePicService.saveProfilePic(username, profilepic);
        user.setProfilepic(picName);

        userRepository.save(user);

        return user;
    }

    public Optional<User> getUser(String username) {
        return userRepository.findByUsername(username);
    }
}
