package com.team11.airbnbbackend.model;

<<<<<<< HEAD
import com.team11.airbnbbackend.dto.WishListRequestDto;
=======
>>>>>>> 627b19cbee78549ef3d88e7bdceecff98faab059
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class WishList {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

<<<<<<< HEAD
//    @Column(nullable = false)
//    private String listName;
=======
    @Column(nullable = false)
    private String listName;
>>>>>>> 627b19cbee78549ef3d88e7bdceecff98faab059

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @OneToMany(mappedBy = "wishList")   //Accomodation(many쪽)이 OneToMany관계의 주인이 됨, wishList로 참조되고있다.
    private List<Accomodation> accomodationList = new ArrayList<>();

<<<<<<< HEAD
    public WishList(WishListRequestDto requestDto, User user) {
    }

=======
    public WishList(String listName, User user) {
        this.listName = listName;
        this.user = user;
    }

//    public void add(Accomodation accomodation) {
//        accomodation.setWishList(this);
//        getAccomodationList().add(accomodation);
//    }

>>>>>>> 627b19cbee78549ef3d88e7bdceecff98faab059
}
