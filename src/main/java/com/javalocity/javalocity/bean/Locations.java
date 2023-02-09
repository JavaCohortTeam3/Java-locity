package com.javalocity.javalocity.bean;
import jakarta.persistence.*;
import org.w3c.dom.Text;



@Entity
@Table (name = "locations")
public class Locations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Trip_Location trip_location;

    @Column(nullable = false, length = 100)
    private String name;

    @Column( length = 254)
    private String web_url;

    @Column(nullable = false, length = 100)
    private String address_string;

    @Column
    private double latitude;

    @Column
    private double longitude;

    @Column
    private int location_idd;

    @Column( length = 100)
    private String email;

    @Column( length = 100)
    private String phone;

    @Column
    private double rating;


    public Locations() {
    }

    public Locations(Trip_Location trip_location, String name, String web_url, String address_string, double latitude, double longitude, int location_idd, String email, String phone, double rating) {
        this.trip_location = trip_location;
        this.name = name;
        this.web_url = web_url;
        this.address_string = address_string;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location_idd = location_idd;
        this.email = email;
        this.phone = phone;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Trip_Location getTrip_location() {
        return trip_location;
    }

    public void setTrip_location(Trip_Location trip_location) {
        this.trip_location = trip_location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public String getAddress_string() {
        return address_string;
    }

    public void setAddress_string(String address_string) {
        this.address_string = address_string;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getLocation_idd() {
        return location_idd;
    }

    public void setLocation_idd(int location_idd) {
        this.location_idd = location_idd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
