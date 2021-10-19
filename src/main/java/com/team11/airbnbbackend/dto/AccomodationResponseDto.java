package com.team11.airbnbbackend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccomodationResponseDto {
    private Long id;
    private String roomName;
    private String contents;
    private Long cost;
    private String location;

    public AccomodationResponseDto(Long id, String roomName, String contents, Long cost, String location){
        this.id = id;
        this.roomName = roomName;
        this.contents = contents;
        this.cost = cost;
        this.location;
    }


}
