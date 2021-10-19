package com.team11.airbnbbackend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccomodationResponseDto {
    private Long id;
    private String roomName;
    private Long cost;
    private String contents;
    private String location;

    public AccomodationResponseDto(Long id, String roomName, Long cost, String contents, String location){
        this.id = id;
        this.roomName = roomName;
        this.cost = cost;
        this.contents = contents;
        this.location = location;
    }


}
