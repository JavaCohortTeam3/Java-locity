package com.javalocity.javalocity.bean;
import jakarta.persistence.*;
import org.w3c.dom.Text;

@Entity
@Table (name = "locations")
public class Locations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long location_id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String description;

    @Column(nullable = false, length = 100)
    private String web_url;

    @Column(nullable = false, length = 100)
    private String address_string;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @Column(nullable = false, length = 100)
    private String timezone;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String phone;

    @Column(nullable = false, length = 100)
    private String website;

    @Column
    private int rating;

    @Column(columnDefinition = "TEXT")
    private String weekday_text;

    @Column(columnDefinition = "TEXT")
    private String amenities;

    @Column(columnDefinition = "TEXT")
    private String cuisine;

    @Column(nullable = false, length = 100)
    private String category_name;

    public Locations(String name, String description, String web_url, String address_string, double latitude, double longitude, String timezone, String email, String phone, String website, int rating, String weekday_text, String amenities, String cuisine, String category_name) {
        this.name = name;
        this.description = description;
        this.web_url = web_url;
        this.address_string = address_string;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timezone = timezone;
        this.email = email;
        this.phone = phone;
        this.website = website;
        this.rating = rating;
        this.weekday_text = weekday_text;
        this.amenities = amenities;
        this.cuisine = cuisine;
        this.category_name = category_name;
    }

    public Locations() {

    }

    public long getLocation_id() {
        return location_id;
    }

    public void setLocation_id(long location_id) {
        this.location_id = location_id;
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

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getWeekday_text() {
        return weekday_text;
    }

    public void setWeekday_text(String weekday_text) {
        this.weekday_text = weekday_text;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
