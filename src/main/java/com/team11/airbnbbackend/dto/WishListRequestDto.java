package com.team11.airbnbbackend.dto;

import lombok.Getter;

@Getter
public class WishListRequestDto {
    private Long id;

    public WishListRequestDto(Long id) {
        this.id = id;
    }
}
