package com.team11.airbnbbackend.dto;

import com.team11.airbnbbackend.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccomodationResponseDto {
    private Long id;
    private String roomName;
    private Long cost;
    private String contents;
    private String location;
    private String image;
    private User user;

    public AccomodationResponseDto(Long id, String roomName, Long cost, String contents, String location, String image) {
        this.id = id;
        this.roomName = roomName;
        this.cost = cost;
        this.contents = contents;
        this.location = location;
        this.image = image;
    }
}
