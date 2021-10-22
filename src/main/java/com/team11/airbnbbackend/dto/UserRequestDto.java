package com.team11.airbnbbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    private String username;
    private String password;
    private LocalDate birth;
    private String email;
    private boolean admin = false;
    private String adminToken = "";
}
