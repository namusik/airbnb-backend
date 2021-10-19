package com.team11.airbnbbackend.repository;

import com.team11.airbnbbackend.model.Accomodation;
import com.team11.airbnbbackend.model.User;
import com.team11.airbnbbackend.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface WishListRepository extends JpaRepository<WishList, Long> {
    List<WishList> findAllByUser(User user);
    Optional<WishList> findByUserAndAccomodation(User user, Accomodation accomodation);
}
