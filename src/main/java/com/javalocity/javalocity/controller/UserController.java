package com.javalocity.javalocity.controller;

import com.javalocity.javalocity.bean.User;
import com.javalocity.javalocity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public String submitRegistrationForm(@ModelAttribute User user, Model model) {
        boolean validInput = true;
        if (userDao.findByUsername(user.getUsername()) != null){
            validInput = false;
            model.addAttribute("usernameAlreadyInUse", true);
        } else if (userDao.findByEmail(user.getEmail()) != null){
            validInput = false;
            model.addAttribute("emailAlreadyInUse", true);
        }
        if (validInput){
            System.out.println(user);
            String pw = passwordEncoder.encode(user.getPassword());
            user.setPassword(pw);
            userDao.save(user);
            return "redirect:/login";
        } else {
            return "/register";
        }

    }

    @GetMapping("/login")
    public String loginPage(@ModelAttribute User user) {
        return "/login";
    }

    @GetMapping("/logout")
    public String logoutPage(@ModelAttribute User user) {
        return "/logout";
    }
    @GetMapping("/profile")
    public String profilePage(@ModelAttribute User user) {
        return "/profile";
    }

}