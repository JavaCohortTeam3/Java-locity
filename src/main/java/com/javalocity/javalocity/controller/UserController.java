package com.javalocity.javalocity.controller;

import com.javalocity.javalocity.bean.Locations;
import com.javalocity.javalocity.bean.Trip;
import com.javalocity.javalocity.bean.Trip_Location;
import com.javalocity.javalocity.bean.User;
import com.javalocity.javalocity.repository.LocationsRepository;
import com.javalocity.javalocity.repository.TripRepository;
import com.javalocity.javalocity.repository.Trip_locationRepository;
import com.javalocity.javalocity.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

//import org.springframework.security.crypto.password.PasswordEncoder;
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
            return "/editProfile";
        }
    }
}