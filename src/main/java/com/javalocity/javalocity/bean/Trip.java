package com.javalocity.javalocity.bean;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50, unique = true)
    private String startDate;

    @Column(length = 50, unique = true)
    private String endDate;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 255)
    private String description;

    @OneToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trip")
    private List<Trip_Location> trip_location;

    public Trip() {}

    public Trip(String startDate, String endDate, String name, String description, User user, List<Trip_Location> trip_location) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
        this.description = description;
        this.user = user;
        this.trip_location = trip_location;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Trip_Location> getTrip_location() {
        return trip_location;
    }

    public void setTrip_location(List<Trip_Location> trip_location) {
        this.trip_location = trip_location;
    }
}
