package com.javalocity.javalocity.repository;

import com.javalocity.javalocity.bean.Locations;
import com.javalocity.javalocity.bean.Trip;
import com.javalocity.javalocity.bean.Trip_Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Trip_locationRepository extends JpaRepository<Trip_Location, Long> {

    Trip_Location findByTrip(Trip trip);

    List<Trip_Location> findTrip_LocationByTrip(Trip trip);

    List<Trip_Location> findTrip_LocationByLocations(Locations locations);




}
