package com.javalocity.javalocity.bean;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "trip_locations")
public class Trip_Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @OneToOne(cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
    private Locations locations;

    @Column
    private String start_date;

    @Column
    String end_date;

    @Column String start_time;

    @Column String end_time;

    public Trip_Location() {
    }

    public Trip_Location(long id, Trip trip, Locations locations, String start_date, String end_date, String start_time, String end_time) {
        this.id = id;
        this.trip = trip;
        this.locations = locations;
        this.start_date = start_date;
        this.end_date = end_date;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public Trip_Location(Trip trip, Locations locations, String start_date, String end_date, String start_time, String end_time) {
        this.trip = trip;
        this.locations = locations;
        this.start_date = start_date;
        this.end_date = end_date;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Locations getLocations() {
        return locations;
    }

    public void setLocations(Locations locations) {
        this.locations = locations;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
