package com.project.bookmyshow.services;

import com.project.bookmyshow.models.User;
import com.project.bookmyshow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

     @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signUpUser(String email, String password) throws Exception {
        User user;
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()){
            throw new Exception();
        }
        user = new User();
        user.setEmail(email);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(password));
        User saveUser = userRepository.save(user);
        return saveUser;
    }

    public User udpateUser(String email,String name,int age,String phoneNo) throws Exception {
         User user;
         Optional<User> userOptional = userRepository.findByEmail(email);
         if (userOptional.isEmpty()){
             throw new Exception();
         }
         user = userOptional.get();
         user.setName(name);
         user.setAge(age);
         user.setPhoneNo(phoneNo);
         User saveUser = userRepository.save(user);
         return saveUser;


    }
}
