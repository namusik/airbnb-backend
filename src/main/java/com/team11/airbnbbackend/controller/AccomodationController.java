package com.team11.airbnbbackend.controller;

import com.team11.airbnbbackend.dto.AccomodationResponseDto;
import com.team11.airbnbbackend.dto.ResponseDto;
import com.team11.airbnbbackend.service.AccomodationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class AccomodationController {

    private final AccomodationService accomodationService;

    public AccomodationController(AccomodationService accomodationService){
        this.accomodationService = accomodationService;
    }

    //검색하기
    @PostMapping("/api/searches")
    public ResponseDto searchList(@RequestBody HashMap<String, String> map){
        String location = map.get("location");
        List<AccomodationResponseDto> accomoList = accomodationService.searchList(location);
        return new ResponseDto("success", "검색에 성공했습니다.", accomoList);
    }

    //상세보기
    @PostMapping("/api/details")
    public ResponseDto readAccomodation(@RequestBody HashMap<String, Long> map){
        Long id = map.get("id");
        AccomodationResponseDto accomodationResponseDto = accomodationService.readAccomodation(id);
        return new ResponseDto("success", "상세보기에 성공했습니다.", accomodationResponseDto);
    }
}
