package com.team11.airbnbbackend.controller;

import com.team11.airbnbbackend.dto.AccomodationResponseDto;
import com.team11.airbnbbackend.service.AccomodationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccomodationController {

    private final AccomodationService accomodationService;

    public AccomodationController(AccomodationService accomodationService){
        this.accomodationService = accomodationService;
    }

    //검색하기
    @PostMapping("/api/searches")
    public List<AccomodationResponseDto> searchList(@RequestBody String location){
        return accomodationService.searchList(location);
    }

    //상세보기
    @PostMapping("/api/details")
    public AccomodationResponseDto readAccomodation(@RequestBody Long id){
        return accomodationService.readAccomodation(id);
    }
}
