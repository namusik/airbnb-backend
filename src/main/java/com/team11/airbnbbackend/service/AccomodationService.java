package com.team11.airbnbbackend.service;

import com.team11.airbnbbackend.dto.AccomodationRequestDto;
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

    public List<AccomodationResponseDto> searchList(String location) {
        List<Accomodation> accomodations = accomodationRepository.findAllByLocation(location);
        List<AccomodationResponseDto> accomodationResponseDtos = new ArrayList<>();

        for(Accomodation accomodation : accomodations){
            AccomodationResponseDto accomodationResponseDto = new AccomodationResponseDto(
                    accomodation.getId(),
                    accomodation.getRoomName(),
                    accomodation.getCost(),
                    accomodation.getContents(),
                    accomodation.getLocation()
            );
        }
        return accomodationResponseDtos;
    }

    public AccomodationResponseDto readAccomodation(Long id) {
        Accomodation accomodation = accomodationRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("상세보기 페이지가 없습니다.")
        );
        AccomodationResponseDto accomodationResponseDto = new AccomodationResponseDto(
                accomodation.getId(),
                accomodation.getRoomName(),
                accomodation.getCost(),
                accomodation.getContents(),
                accomodation.getLocation()
        );
        return accomodationResponseDto;
    }
}
