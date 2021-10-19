package com.team11.airbnbbackend.controller;

import com.team11.airbnbbackend.dto.AccomodationRequestDto;
import com.team11.airbnbbackend.dto.AccomodationResponseDto;
import com.team11.airbnbbackend.service.AccomodationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccomodationController {

    private final AccomodationService accomodationService;

    public AccomodationController(AccomodationController accomodationController){
        this.accomodationService = accomodationService;
    }

    //검색하기
    @PostMapping("/api/searches")
    public AccomodationResponseDto searchList(@RequestBody AccomodationRequestDto accomodationRequestDto){
        return accomodationService.searchList(accomodationRequestDto);
    }


    //상세보기
    @PostMapping("/api/details")

}
