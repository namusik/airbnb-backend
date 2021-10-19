package com.team11.airbnbbackend.dto;

import com.team11.airbnbbackend.model.Accomodation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private String result;

    private String msg;

    private Object object;

    private List<Accomodation> accomodations;

}
