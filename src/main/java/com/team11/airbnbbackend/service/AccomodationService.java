package com.team11.airbnbbackend.service;

import com.team11.airbnbbackend.dto.AccomodationRequestDto;
import com.team11.airbnbbackend.dto.AccomodationResponseDto;
import com.team11.airbnbbackend.exception.CustomErrorException;
import com.team11.airbnbbackend.model.Accomodation;
import com.team11.airbnbbackend.model.User;
import com.team11.airbnbbackend.repository.AccomodationRepository;
import com.team11.airbnbbackend.repository.UserRepository;
import com.team11.airbnbbackend.security.UserDetailsImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccomodationService {

    private final AccomodationRepository accomodationRepository;
    private final UserRepository userRepository;


    public AccomodationService(AccomodationRepository accomodationRepository, UserRepository userRepository) {
        this.accomodationRepository = accomodationRepository;
        this.userRepository = userRepository;
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
                    accomodation.getLocation(),
                    accomodation.getImage()
            );
            accomodationResponseDtos.add(accomodationResponseDto);
        }
        return accomodationResponseDtos;
    }

    public AccomodationResponseDto readAccomodation(Long id) {
        Accomodation accomodation = accomodationRepository.findById(id).orElseThrow(
                ()-> new CustomErrorException("해당 숙소가 없습니다")
        );
        AccomodationResponseDto accomodationResponseDto = new AccomodationResponseDto(
                accomodation.getId(),
                accomodation.getRoomName(),
                accomodation.getCost(),
                accomodation.getContents(),
                accomodation.getLocation(),
                accomodation.getImage(),
                accomodation.getUser()
        );
        return accomodationResponseDto;
    }
    
    //숙소 등록하기
    @Transactional
    public Accomodation addAccomodation(AccomodationRequestDto requestDto, UserDetailsImpl userDetails) {
        Long id = userDetails.getUser().getId();

        User user = userRepository.findById(id).orElseThrow(
                () -> new CustomErrorException("존재하지 않는 사용자 입니다.")
        );

        Accomodation accomodation = new Accomodation(requestDto.getRoomName(), requestDto.getContents(), requestDto.getCost(),
                requestDto.getLocation(), requestDto.getImage(), user);
        
        //db에 숙소 저장
        accomodationRepository.save(accomodation);
        //User 객체의 숙소리스트에 숙소 추가
        user.getMyAccomodations().add(accomodation);
        
        return accomodation;

    }
}
