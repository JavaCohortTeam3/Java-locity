package com.javalocity.javalocity.repository;

import com.javalocity.javalocity.bean.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    Trip findByName(String name);

    Trip findByStartDate(Date date);

    Trip findById(long id);
}
