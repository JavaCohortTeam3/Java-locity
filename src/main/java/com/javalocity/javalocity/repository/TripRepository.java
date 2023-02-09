package com.javalocity.javalocity.repository;

import com.javalocity.javalocity.bean.Trip;
import com.javalocity.javalocity.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    Trip findByName(String name);

    Trip findByStartDate(String date);

    Trip findByEndDate(String date);

    Trip findById(long id);

    List<Trip> findByUser(User user);
}
