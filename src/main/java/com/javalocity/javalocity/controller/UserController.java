package com.javalocity.javalocity.controller;

import com.javalocity.javalocity.bean.*;
import com.javalocity.javalocity.repository.LocationsRepository;
import com.javalocity.javalocity.repository.TripRepository;
import com.javalocity.javalocity.repository.Trip_locationRepository;
import com.javalocity.javalocity.repository.UserRepository;
import com.javalocity.javalocity.util.FileUploadUtil;
import jakarta.persistence.Entity;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;



@Controller
public class UserController {
    @Autowired
    private UserRepository userDao;

    private final TripRepository tripDao;

    private final LocationsRepository locationsDao;

    private final Trip_locationRepository trip_locationDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, TripRepository tripDao, LocationsRepository locationsDao, Trip_locationRepository trip_locationDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.tripDao = tripDao;
        this.locationsDao = locationsDao;
        this.trip_locationDao = trip_locationDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String submitRegistrationForm(@ModelAttribute User user, Model model) {
        boolean validRegister = true;
        if (userDao.findByUsername(user.getUsername()) != null) {
            validRegister = false;
            model.addAttribute("usernameAlreadyInUse", true);
            model.addAttribute("usernameInUse", (String) user.getUsername());

        } else if (userDao.findByEmail(user.getEmail()) != null){
            validRegister = false;
            model.addAttribute("emailAlreadyInUse", true);
            model.addAttribute("emailInUse", (String) user.getEmail());
        }
        if (validRegister) {
            System.out.println(user);
            String pw = passwordEncoder.encode(user.getPassword());
            user.setPassword(pw);
            userDao.save(user);
            return "redirect:/login";
        } else {
            return "register";
        }

    }

    @GetMapping("/login")
    public String loginPage(@ModelAttribute User user) {
        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage(@ModelAttribute User user) {
        return "reidirect:/login?logout";
    }

    @GetMapping("/profile")
    public String profilePage(@ModelAttribute User user, Model model, HttpSession session) {
        User user1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Trip> trip = tripDao.findByUser(user1);

        model.addAttribute("trip", trip);
        return "profile";
    }

    @PostMapping("/profile")
    public String postProfile(Model model, @RequestParam("id") long id, HttpSession session) {
        Trip trip = tripDao.getReferenceById(id);
        List<Trip_Location> trip_locations = trip_locationDao.findTrip_LocationByTrip(trip);
        session.setAttribute("trip", trip_locations);
        return "redirect:/view";
    }

    @GetMapping("/view")
    public String viewTrips(HttpSession session, Model model) {
        model.addAttribute("trips", session.getAttribute("trip"));
        List<Trip_Location> trip_locations;
        trip_locations = (List<Trip_Location>) session.getAttribute("trip");
        List<Locations> locations = new ArrayList<>();
        for (int i = 0; i < trip_locations.size(); i++) {
            locations.add(locationsDao.getReferenceById(trip_locations.get(i).getLocations().getId()));
        }
        model.addAttribute("locations", locations);
        return "view";
    }

    @PostMapping("/view")
    public String removeLocation(@RequestParam("locationId") long id){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Trip_Location trip_location = (Trip_Location) trip_locationDao.getReferenceById(id);

        trip_locationDao.delete(trip_location);
        System.out.println("test");

        System.out.println(id);
        return "redirect:profile";
    }

    @GetMapping("/account/info")
    public String accountInfoGet(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user1 = userDao.getReferenceById(user.getId());
        if (user1.getProfile_img() == null) {

        } else {
            model.addAttribute("img", Base64.getEncoder().encodeToString(user1.getProfile_img()));
        }


        model.addAttribute("user", user);
        return "accountInfo";
    }

    @GetMapping("/account/edit/password")
    public String editAccountPasswordGet(Model model) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        model.addAttribute("user", user);
//        PasswordHolder ph = new PasswordHolder();
//        ph.setUser(user);
        model.addAttribute("passwordHolder", new PasswordHolder());
        return "accountEditPassword";
    }

    @PostMapping("/account/edit/password")
    public String editAccountPasswordPost(@ModelAttribute PasswordHolder passwordHolder, Model model) {

        String currentPassword = passwordHolder.getCurrentPassword();
        String newPassword = passwordHolder.getNewPassword();

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Long oldId = user.getId();
        String oldUsername = user.getUsername();
        String oldEmail = user.getEmail();
        String oldPassword = user.getPassword();
        String hashNewPassword = passwordEncoder.encode(newPassword);
        user.setPassword(hashNewPassword);

        User newUser = new User(oldId, oldUsername, oldEmail, hashNewPassword);

        if (passwordEncoder.matches(currentPassword, oldPassword)){

            userDao.save(newUser);
            model.addAttribute("PasswordHasBeenUpdated", true);
            return "redirect:/login?logout";
        } else {
            model.addAttribute("incorrectPW", true);
            return "accountEditPassword";
        }
    }

    @GetMapping("/account/edit")
    public String editAccountGet(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "accountEdit";
    }

    @PostMapping("/account/edit")
    public String editAccountPost(@ModelAttribute User user, Model model) {
        String passwordToCheck = user.getPassword();

        User oldUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String oldUsername = oldUser.getUsername();
        String oldEmail = oldUser.getEmail();
        String oldPassword = oldUser.getPassword();

        boolean validEdit = false;
        if (!user.getUsername().equals(oldUser.getUsername()) && userDao.findByUsername(user.getUsername()) != null) {
            model.addAttribute("usernameAlreadyInUse", true);
            model.addAttribute("usernameInUse", (String) user.getUsername());
        } else if (!user.getEmail().equals(oldUser.getEmail()) && userDao.findByEmail(user.getEmail()) != null) {
            model.addAttribute("emailAlreadyInUse", true);
            model.addAttribute("emailInUse", (String) user.getEmail());
        } else if (!passwordEncoder.matches(passwordToCheck, oldUser.getPassword())) {
            model.addAttribute("incorrectPW", true);
        } else {
            validEdit = true;
        }
        if (validEdit) {
            if (user.getUsername().isEmpty()) {
                user.setUsername(oldUsername);
            }
            if (user.getEmail().isEmpty()) {
                user.setEmail(oldEmail);
            } else {
                String pw = passwordEncoder.encode(user.getPassword());
                user.setPassword(pw);
            }
            userDao.save(user);
            return "redirect:/login?logout";
        } else {
            return "accountEdit";
        }
    }
@GetMapping("/profile/profilePic")
    public String profilePicGet(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user1 = (User)userDao.getReferenceById(user.getId());
        model.addAttribute("user", user1);
        return "profilePic";
    }
    @PostMapping("/profile/profilePic")
    public RedirectView saveUser(@RequestParam("image") MultipartFile multipartFile) throws IOException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user1 = (User)userDao.getReferenceById(user.getId());


        byte [] files = multipartFile.getBytes();

        user1.setProfile_img(files);
        userDao.save(user1);

        return new RedirectView("/account/info", true);
    }

    @PostMapping("/profile/delete")
    public String deletePic() throws IOException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user1 = userDao.getReferenceById(user.getId());

        user1.setProfile_img(null);
        userDao.save(user1);
        return "redirect:/account/info";
    }

    @PostMapping("/trip/delete")
    public String deleteTrip(@RequestParam("idd") long id) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            Trip trip = tripDao.getReferenceById(id);

            tripDao.deleteById(trip.getId());

        return "redirect:/profile";
    }
    @GetMapping("/team")
    public String teamPage() {
        return "team";
    }
}