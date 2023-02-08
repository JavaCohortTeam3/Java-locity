package com.javalocity.javalocity.bean;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;


import java.util.Date;

@Entity
@Table(name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50)
    private String startDate;

    @Column(length = 50)
    private String endDate;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 255)
    private String description;

    @OneToOne
    private User user;

    public Trip() {}

    public Trip(String startDate, String endDate, String name, String description, User user) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
        this.description = description;
        this.user = user;
    }

    public Trip(long id, String startDate, String endDate, String name, String description, User user) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
        this.description = description;
        this.user = user;
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
}
