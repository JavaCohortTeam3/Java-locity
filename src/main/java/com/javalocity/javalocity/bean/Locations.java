package com.javalocity.javalocity.bean;
import jakarta.persistence.*;
import org.w3c.dom.Text;

@Entity
@Table (name = "locations")
public class Locations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String location_id;
    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private double distance;

    @Column(nullable = false, length = 100)
    private String bearing;

    @Column(nullable = false, length = 100)
    private String street1;

    @Column(nullable = false)
    private String street2;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false, length = 100)
    private String state;

    @Column(nullable = false, length = 100)
    private String country;

    @Column(nullable = false, length = 100)
    private String postalcode;

    @Column(nullable = false, length = 100)
    private String address_string;

    public Locations(String location_id, String name, double distance, String bearing, String street1, String street2, String city, String state, String country, String postalcode, String address_string) {
        this.location_id = location_id;
        this.name = name;
        this.distance = distance;
        this.bearing = bearing;
        this.street1 = street1;
        this.street2 = street2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalcode = postalcode;
        this.address_string = address_string;
    }

    public Locations() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getBearing() {
        return bearing;
    }

    public void setBearing(String bearing) {
        this.bearing = bearing;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getAddress_string() {
        return address_string;
    }

    public void setAddress_string(String address_string) {
        this.address_string = address_string;
    }
}
