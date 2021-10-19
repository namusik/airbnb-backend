package com.team11.airbnbbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Long birth;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;
    
    //내가 등록한 숙소들 매핑
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Accomodation> myAccomodations;
    
    //내가 wish 누른 숙소들 매핑
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<WishList> myWishList;

    public User(String username, String password, Long birth, String email, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.birth = birth;
        this.email = email;
        this.role = role;
    }
}
