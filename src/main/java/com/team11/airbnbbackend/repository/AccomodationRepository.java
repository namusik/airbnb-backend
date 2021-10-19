package com.team11.airbnbbackend.repository;

import com.team11.airbnbbackend.model.Accomodation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccomodationRepository extends JpaRepository<Accomodation, String> {
    List<Accomodation> findByLocation(String location);
}
