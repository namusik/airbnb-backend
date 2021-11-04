package com.team11.airbnbbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
//8001, 8002 어느 포트를 사용할지 판단하는 API는 ProfileController
public class ProfileController {
    private final Environment env;

    @GetMapping("/profile")
    public String profile() {
        //현재 동작중인 프로파일의 이름을 반환
        return Arrays.stream(env.getActiveProfiles()).findFirst().orElse("");
    }
}
