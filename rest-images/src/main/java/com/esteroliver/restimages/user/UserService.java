package com.esteroliver.restimages.user;

import com.esteroliver.restimages.user.util.ImageUtil;
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

    public User createUser(String username, MultipartFile profilepic) throws IOException {
        User user = new User();

        // TO:DO

        return user;
    }

}
