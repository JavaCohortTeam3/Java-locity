package com.javalocity.javalocity.controller;

import com.javalocity.javalocity.bean.Locations;
import com.javalocity.javalocity.bean.Trip;
import com.javalocity.javalocity.bean.Trip_Location;
import com.javalocity.javalocity.bean.User;
import com.javalocity.javalocity.repository.LocationsRepository;
import com.javalocity.javalocity.repository.TripRepository;
import com.javalocity.javalocity.repository.Trip_locationRepository;
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

    private final LocationsRepository locationsDao;

    private final Trip_locationRepository trip_locationDao;

    public TripController(TripRepository tripDao, LocationsRepository locationsDao, Trip_locationRepository trip_locationDao) {
        this.tripDao = tripDao;
        this.locationsDao = locationsDao;
        this.trip_locationDao = trip_locationDao;
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
        session.setAttribute("trip", trip);
        return "redirect:/trip/details";
    }
    @GetMapping("/trip/details")
    public String getDetails(Model model, HttpSession session) {

        model.addAttribute("location", session.getAttribute("location"));


        return "/trip-details";
    }
    @PostMapping("/trip/details")
    public String setDetails(@RequestParam("idd") int id, HttpSession session, @RequestParam("start") String start, @RequestParam("end") String end) {

        session.setAttribute("id", id);
        session.setAttribute("start", start);
        session.setAttribute("end", end);
        return "redirect:/location/viewer";
    }

    @GetMapping("/location/viewer")
    public String view(HttpSession session, Model model) {
        model.addAttribute("id", session.getAttribute("id"));
        model.addAttribute("start", session.getAttribute("start"));
        model.addAttribute("end", session.getAttribute("end"));
        return "/location-viewer";
    }

    @PostMapping("/location/viewer")
    public String viewLoc(HttpSession session, Model model, @RequestParam("name") String name, @RequestParam("web_url") String web_url, @RequestParam("address_string") String address_string, @RequestParam("latitude") double latitude, @RequestParam("longitude") double longitude, @RequestParam("email") String email, @RequestParam("phone") String phone, @RequestParam("rating") double rating) {
        Locations location = new Locations(name, web_url, address_string, latitude, longitude, (int) session.getAttribute("id"), email, phone, rating);
        locationsDao.save(location);
        Trip trip = (Trip) session.getAttribute("trip");
        String start = (String) session.getAttribute("start");
        String end = (String) session.getAttribute("end");
        Trip_Location trip_location = new Trip_Location(trip, location, trip.getStartDate(), trip.getEndDate(), start, end);
        trip_locationDao.save(trip_location);

        return "redirect:/profile";
    }
}
