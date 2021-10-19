package com.team11.airbnbbackend.model;

import com.team11.airbnbbackend.dto.AccomodationRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Accomodation {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false)
    private String roomName;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private  Long cost;

    @Column(nullable = false)
    private String location;

    @ManyToOne
    @JoinColumn(name = "WISHLIST_ID", nullable = false)
    private WishList wishList;

    public Accomodation(AccomodationRequestDto accomodationRequestDto, WishList wishList){
        this.location = accomodationRequestDto.getLocation();
    }

}
