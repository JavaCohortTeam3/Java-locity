package com.javalocity.javalocity.repository;

import com.javalocity.javalocity.bean.Locations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationsRepository extends JpaRepository<Locations, Long> {
    Locations getLocationByName(String name);
    List<Locations> getLocationsByName(String name);
}
