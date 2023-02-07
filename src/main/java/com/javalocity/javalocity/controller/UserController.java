package com.javalocity.javalocity.controller;

import com.javalocity.javalocity.bean.User;
import com.javalocity.javalocity.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//import org.springframework.security.crypto.password.PasswordEncoder;
@Controller
public class UserController {
    @Autowired
    private UserRepository userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "/register";
    }

    @PostMapping("/register")
    public String submitRegistrationForm(@ModelAttribute User user) {
        System.out.println(user);
        String pw = passwordEncoder.encode(user.getPassword());
        user.setPassword(pw);
        userDao.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(@ModelAttribute User user) {
        return "/login";
    }




}