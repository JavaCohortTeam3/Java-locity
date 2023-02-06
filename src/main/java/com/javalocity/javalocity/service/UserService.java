package com.javalocity.javalocity.service;

import com.javalocity.javalocity.bean.User;
import com.javalocity.javalocity.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
@Service

public class UserService {
    UserRepository userRepository;


    public User saveUser(User user) {
        return userRepository.save(user);
    }




    public List<User> fetchUserList() {
        return (List<User>) userRepository.findAll();
    }



    public User updateUser(User user, Long id) {
        User userDB
                =  userRepository.getById(id);

        if (Objects.nonNull(user.getEmail())
                && !"".equalsIgnoreCase(
                user.getEmail())) {
            userDB.setEmail(
                    user.getEmail());
        }

        if (Objects.nonNull(
                user.getUsername())
                && !"".equalsIgnoreCase(
                user.getUsername())) {
            userDB.setUsername(
                    user.getUsername());
        }
        if (Objects.nonNull(
                user.getPassword())
                && !"".equalsIgnoreCase(
                user.getPassword())) {
            userDB.setPassword(
                    user.getPassword()
            );
        }



        return userRepository.save(userDB);
    }


    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }


    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
