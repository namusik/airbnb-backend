package com.team11.airbnbbackend.service;

import com.team11.airbnbbackend.dto.AccomodationResponseDto;
import com.team11.airbnbbackend.dto.ResponseDto;
import com.team11.airbnbbackend.model.Accomodation;
import com.team11.airbnbbackend.repository.AccomodationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccomodationService {

    private final AccomodationRepository accomodationRepository;

    public AccomodationService(AccomodationRepository accomodationRepository) {
        this.accomodationRepository = accomodationRepository;
    }

    public List<AccomodationResponseDto> searchList(String location){
        List<Accomodation> accomodations = accomodationRepository.findByLocation(location);
        List<AccomodationResponseDto> accomodationResponseDtos = new ArrayList<>();

        return new ResponseDto("success", "검색에 성공했습니다", "{id, roomName, cost, contents, location"})



    }
}
