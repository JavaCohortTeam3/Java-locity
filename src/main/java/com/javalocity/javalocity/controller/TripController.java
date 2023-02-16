package com.javalocity.javalocity.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.javalocity.javalocity.bean.Locations;
import com.javalocity.javalocity.bean.Trip;
import com.javalocity.javalocity.bean.Trip_Location;
import com.javalocity.javalocity.bean.User;

import com.javalocity.javalocity.repository.LocationsRepository;
import com.javalocity.javalocity.repository.TripRepository;
import com.javalocity.javalocity.repository.Trip_locationRepository;
import com.javalocity.javalocity.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class TripController {
    private final UserRepository userDao;

    private final TripRepository tripDao;

    private final LocationsRepository locationsDao;

    private final Trip_locationRepository trip_locationDao;

    public TripController(UserRepository userDao, TripRepository tripDao, LocationsRepository locationsDao, Trip_locationRepository trip_locationDao) {
        this.userDao = userDao;
        this.tripDao = tripDao;
        this.locationsDao = locationsDao;
        this.trip_locationDao = trip_locationDao;
    }

    @GetMapping("/trip/locations")
    public String findLocale(@Value("${mapKey}") String mapKey, Model model) {
        model.addAttribute("mapKey", mapKey);
        return "trip-location";
    }
    @PostMapping("/trip/locations")
    public String getLocale(@RequestParam("location") String location, Model model, HttpSession session, @RequestParam("begin") String start, @RequestParam("end") String end, @RequestParam("title") String title) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Trip trip = new Trip();
        trip.setStartDate(start);
        trip.setEndDate(end);
        trip.setName(title);
        trip.setDescription(location);
        trip.setUser(user);
        if (tripDao.findByStartDate(trip.getStartDate()) == null || tripDao.findByEndDate(trip.getEndDate()) == null) {
            tripDao.save(trip);

        }
        session.setAttribute("location", location);
        session.setAttribute("trip", trip);
        return "redirect:/trip/details";

    }
    @GetMapping("/trip/details")
    public String getDetails(Model model, HttpSession session, @Value("${travleyKey}") String tripkey) {

        model.addAttribute("map", tripkey);

        model.addAttribute("location", session.getAttribute("location"));
        Trip trip = (Trip) session.getAttribute("trip");

        if (trip_locationDao.findTrip_LocationByTrip(trip) != null) {
            List<Trip_Location> trip_location = (List<Trip_Location>) trip_locationDao.findTrip_LocationByTrip(trip);
            List<Locations> locations = new ArrayList<>();
            for (int i = 0; i < trip_location.size(); i++) {
                locations.add(locationsDao.getReferenceById(trip_location.get(i).getLocations().getId()));
            }
            model.addAttribute("train", locations);
            model.addAttribute("crazy", trip_location);
        }

        model.addAttribute("start", trip.getStartDate());
        model.addAttribute("end", trip.getEndDate());

        return "trip-details";
    }
    @PostMapping("/trip/details")
    public String setDetails(@RequestParam("idd") int id, HttpSession session, @RequestParam("start") String start, @RequestParam("end") String end, @RequestParam("begin") String begin) {


        session.setAttribute("begin", begin);
        session.setAttribute("id", id);
        session.setAttribute("start", start);
        session.setAttribute("end", end);
        return "redirect:/location/viewer";
    }

    @GetMapping("/location/viewer")
    public String view(HttpSession session, Model model, @Value("${travleyKey}") String tripKey) {
        model.addAttribute("tripKey", tripKey);
        model.addAttribute("id", session.getAttribute("id"));
        model.addAttribute("start", session.getAttribute("start"));
        model.addAttribute("end", session.getAttribute("end"));
        return "location-viewer";
    }

    @PostMapping("/location/viewer")
    public String viewLoc(HttpSession session, Model model, @RequestParam("name") String name, @RequestParam("web_url") Optional<String> web_url, @RequestParam("address_string") Optional<String> address_string, @RequestParam("latitude") double latitude, @RequestParam("longitude") double longitude, @RequestParam("email") Optional<String> email, @RequestParam("phone") Optional<String> phone, @RequestParam("rating")Optional<String> rating, @RequestParam("picture") Optional<String> picture) {
        Locations location = new Locations();
        System.out.println("rating.get() = " + rating.get());;
        System.out.println("picture.get() = " + picture.get());
        if (!rating.get().equals("undefined")) {
            location.setRating(Double.parseDouble(rating.get()));
        }
        if (!picture.get().equals("undefined") || !picture.get().equals("")) {
            location.setPicture(picture.get());
        }
        if (!email.get().equals("undefined")) {
            location.setEmail(email.get());
        }
        if (!phone.get().equals("undefined")) {
            location.setPhone(phone.get());
        }
        if (!web_url.get().equals("undefined")) {
            location.setWeb_url(web_url.get());
        }

            location.setName(name);

            location.setAddress_string(address_string.get());
            location.setLatitude(latitude);
            location.setLongitude(longitude);
            location.setLocation_idd((int) session.getAttribute("id"));

            locationsDao.save(location);



        Trip trip = (Trip) session.getAttribute("trip");

        String start = (String) session.getAttribute("start");
        String end = (String) session.getAttribute("end");
        String begin = (String) session.getAttribute("begin");

        Trip_Location trip_location = new Trip_Location(trip, location, begin, trip.getEndDate(), start, end);
        trip_location.setLocations(location);

        trip_locationDao.save(trip_location);

        Locations locations = locationsDao.getReferenceById(trip_location.getLocations().getId());
        locations.setTrip_location(trip_location);
        locationsDao.save(locations);



        return "redirect:/trip/details";
    }

    @GetMapping("/trip/view")
    public String viewTrip() {
        return "redirect:/profile";
    }

    @PostMapping("/trip/add")
    public String addToTrip(@RequestParam("addToTrip") long id, HttpSession session){
Trip trip = (Trip) tripDao.getReferenceById(id);

session.setAttribute("location", trip.getDescription());
session.setAttribute("trip", trip);

        return "redirect:/trip/details";
    }

    @GetMapping("/viewed/{path}")
    public String viewer(@PathVariable long path, Model model) {
        System.out.println("path = " + path);
        Trip trip = (Trip) tripDao.getReferenceById(path);

        List<Trip_Location> trip_locations = (List<Trip_Location>) trip_locationDao.findTrip_LocationByTrip(trip);
        List<Locations> locations = new ArrayList<>();
        for (int i = 0; i < trip_locations.size(); i++) {
            locations.add(locationsDao.getReferenceById(trip_locations.get(i).getLocations().getId()));
        }

        System.out.println("locations = " + locations);
        model.addAttribute("locations", locations);
        return "viewed";

    }
}
