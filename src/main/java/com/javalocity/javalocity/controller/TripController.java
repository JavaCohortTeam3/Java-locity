package com.javalocity.javalocity.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TripController {

    @GetMapping("/trip/locations")
    public String findLocale() {
        return "/trip-location";
    }
    @PostMapping("/trip/locations")
    public String getLocale(@RequestParam("location") String location, Model model, HttpSession session) {
        System.out.println(location);

        session.setAttribute("location", location);
        return "redirect:/trip/details";
    }
    @GetMapping("/trip/details")
    public String getDetails(Model model, HttpSession session) {

        model.addAttribute("location", session.getAttribute("location"));


        return "/trip-details";
    }
}
