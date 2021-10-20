package com.team11.airbnbbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team11.airbnbbackend.dto.AccomodationRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    @Column(nullable = false)
    private String image;

    //호스트 (숙소 등록한 사람)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    //wishList 매핑
    @OneToMany(mappedBy = "accomodation")
    @JsonIgnore
    private List<WishList> wishList;
    
    //숙소 저장용 생성자
    public Accomodation(String roomName, String contents, Long cost, String location, String image, User user) {
        this.roomName = roomName;
        this.contents = contents;
        this.cost = cost;
        this.location = location;
        this.image = image;
        this.user = user;
    }

    public void updateAccomodation(AccomodationRequestDto requestDto){
        this.roomName = roomName;
        this.contents = contents;
        this.cost = cost;
        this.location = location;
        this.image = image;
    }
}
