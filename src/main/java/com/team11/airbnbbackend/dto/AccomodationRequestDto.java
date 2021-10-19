package com.team11.airbnbbackend.dto;

import lombok.Getter;

@Getter
public class AccomodationRequestDto {
    private Long id;
    private String roomName;
    private Long cost;
    private String contents;
    private String location;

    public AccomodationRequestDto(Long id, String roomName, Long cost, String contents, String location){
        this.id =id;
        this.roomName = roomName;
        this.cost =cost;
        this.contents = contents;
        this.location = location;
    }
}
