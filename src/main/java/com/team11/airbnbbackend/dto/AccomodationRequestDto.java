package com.team11.airbnbbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccomodationRequestDto {
    private String roomName;
    private Long cost;
    private String contents;
    private String location;
    private String image;
}
