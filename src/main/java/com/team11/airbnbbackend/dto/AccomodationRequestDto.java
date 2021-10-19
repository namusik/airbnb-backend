package com.team11.airbnbbackend.dto;

import lombok.Getter;

@Getter
public class AccomodationRequestDto {
    private Long id;
    private String location;

    public AccomodationRequestDto(Long id, String location){
        this.id = id;
        this.location = location;
    }
}
