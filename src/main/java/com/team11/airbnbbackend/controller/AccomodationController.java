package com.team11.airbnbbackend.controller;

import com.team11.airbnbbackend.dto.AccomodationRequestDto;
import com.team11.airbnbbackend.dto.AccomodationResponseDto;
import com.team11.airbnbbackend.dto.ResponseDto;
import com.team11.airbnbbackend.exception.CustomErrorException;
import com.team11.airbnbbackend.model.Accomodation;
import com.team11.airbnbbackend.model.User;
import com.team11.airbnbbackend.security.UserDetailsImpl;
import com.team11.airbnbbackend.service.AccomodationService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AccomodationController {

    private final AccomodationService accomodationService;

    public AccomodationController(AccomodationService accomodationService){
        this.accomodationService = accomodationService;
    }

    //검색하기
    @GetMapping("/api/searches")
    public ResponseDto searchList(@RequestParam("location") String location){

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

    //숙소 등록하기
    @PostMapping("/api/rooms")
    public ResponseDto addAccomodation(@RequestBody AccomodationRequestDto accomodationRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(userDetails == null) {
            throw new CustomErrorException("로그인이 필요합니다.");
        }
        //숙소 등록하기 로직
        Accomodation accomodation = accomodationService.addAccomodation(accomodationRequestDto, userDetails);

        return new ResponseDto("success", "숙소 등록에 성공했습니다", accomodation);
    }

    //숙소 수정하기
    @PutMapping("/api/rooms")
    public ResponseDto editAccomodation(@RequestBody AccomodationRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(userDetails == null) {
            throw new CustomErrorException("로그인이 필요합니다.");
        }

        accomodationService.editAccomodation(requestDto);
        return  new ResponseDto("success", "숙소 정보가 수정되었습니다.", "");
    }

    //숙소 삭제하기
    @DeleteMapping("/api/rooms")
    public ResponseDto deleteAccomodation(@RequestBody HashMap<String, Long> map, @AuthenticationPrincipal UserDetailsImpl userDetails){
        Long id = map.get("id");

        if(userDetails == null) {
            throw new CustomErrorException("로그인이 필요합니다.");
        }

        accomodationService.deleteAccomodation(id);
        return  new ResponseDto("success", "숙소 정보가 삭제되었습니다.", "");
    }
}
