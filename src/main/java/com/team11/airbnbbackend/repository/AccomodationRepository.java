package com.team11.airbnbbackend.repository;

import com.team11.airbnbbackend.model.Accomodation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccomodationRepository extends JpaRepository<Accomodation, String> {
    List<Accomodation> findAllByLocation(String location);

    Optional<Accomodation> findById(Long id);
}
