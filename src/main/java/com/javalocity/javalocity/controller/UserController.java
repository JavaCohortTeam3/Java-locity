package com.javalocity.javalocity.controller;

import com.javalocity.javalocity.bean.User;
import com.javalocity.javalocity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//<<<<<<< HEAD
//
//=======
//import org.springframework.web.bind.annotation.RequestParam;
//>>>>>>> fce87cfbc0d2d8f60394c42b7f481fdb95953164

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
        boolean validRegister = true;
        if (userDao.findByUsername(user.getUsername()) != null){
            validRegister = false;
            model.addAttribute("usernameAlreadyInUse", true);
        } else if (userDao.findByEmail(user.getEmail()) != null){
            validRegister = false;
            model.addAttribute("emailAlreadyInUse", true);
        }
        if (validRegister){
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

    @GetMapping("/profile/edit")
    public String editProfileGet(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "/editProfile";
    }

    @PostMapping("/profile/edit")
    public String editProfilePost(@ModelAttribute User user, Model model) {
        User oldUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String oldUsername = oldUser.getUsername();
        String oldEmail = oldUser.getEmail();
        String oldPassword = oldUser.getPassword();
        boolean validEdit = true;
        if (!user.getUsername().equals(oldUser.getUsername()) && userDao.findByUsername(user.getUsername()) != null) {
            validEdit = false;
            model.addAttribute("usernameAlreadyInUse", true);
        } else if (!user.getEmail().equals(oldUser.getEmail()) && userDao.findByEmail(user.getEmail()) != null){
            validEdit = false;
            model.addAttribute("emailAlreadyInUse", true);
        }
        if (validEdit){
            if (user.getUsername().isEmpty()){
                user.setUsername(oldUsername);
            }
            if (user.getEmail().isEmpty()){
                user.setEmail(oldEmail);
            }
            if (user.getPassword().isEmpty()){
                user.setPassword(oldPassword);
            } else {
                String pw = passwordEncoder.encode(user.getPassword());
                user.setPassword(pw);
            }
            userDao.save(user);
            return "redirect:/login?logout";
        } else {
            return "editProfile";
        }
    }
}