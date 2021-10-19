package com.team11.airbnbbackend.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class WishListRequestDto {
    List<String> wishListNames;
}
