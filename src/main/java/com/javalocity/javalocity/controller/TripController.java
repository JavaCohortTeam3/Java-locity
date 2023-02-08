package com.javalocity.javalocity.controller;

import com.javalocity.javalocity.bean.Trip;
import com.javalocity.javalocity.bean.User;
import com.javalocity.javalocity.repository.TripRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TripController {

    private final TripRepository tripDao;

    public TripController(TripRepository tripDao) {
        this.tripDao = tripDao;
    }

    @GetMapping("/trip/locations")
    public String findLocale() {
        return "/trip-location";
    }
    @PostMapping("/trip/locations")
    public String getLocale(@RequestParam("location") String location, Model model, HttpSession session, @RequestParam("begin") String start, @RequestParam("end") String end, @RequestParam("title") String title) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Trip trip = new Trip(start, end, title, location, user);
        tripDao.save(trip);
        session.setAttribute("location", location);
        return "redirect:/trip/details";
    }
    @GetMapping("/trip/details")
    public String getDetails(Model model, HttpSession session) {

        model.addAttribute("location", session.getAttribute("location"));


        return "/trip-details";
    }
    @PostMapping("/trip/details")
    public String setDetails(@RequestParam("idd") int id, HttpSession session) {
        System.out.println(id);
        session.setAttribute("id", id);
        return "redirect:/location/viewer";
    }

    @GetMapping("/location/viewer")
    public String view(HttpSession session, Model model) {
        model.addAttribute("id", session.getAttribute("id"));
        return "/location-viewer";
    }
}
