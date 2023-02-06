package com.javalocity.javalocity.controller;

import com.javalocity.javalocity.bean.User;
import com.javalocity.javalocity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.security.crypto.password.PasswordEncoder;
@Controller
public class UserController {
    @Autowired
    private UserRepository userDao;
//    private PasswordEncoder passwordEncoder;

//
    public UserController(UserRepository userDao) {
        this.userDao = userDao;
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "/register";
    }

    @PostMapping("/register")
    public String submitRegistrationForm(@ModelAttribute User user) {
        userDao.save(user);
        return "redirect:/home";
    }
}