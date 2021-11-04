package com.team11.airbnbbackend.controller;

import com.team11.airbnbbackend.exception.CustomErrorException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {
    @GetMapping("/exception/entrypoint")
    public void entryPoint() {
        throw new CustomErrorException("토큰 오류");
    }

    @GetMapping("/exception/accessdenied")
    public void accessDenied() {
        throw new CustomErrorException("access denied");
    }
}
