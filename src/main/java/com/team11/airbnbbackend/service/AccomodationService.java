package com.team11.airbnbbackend.service;

import com.team11.airbnbbackend.dto.AccomodationRequestDto;
import com.team11.airbnbbackend.dto.AccomodationResponseDto;
import com.team11.airbnbbackend.dto.ResponseDto;
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

    //숙소 검색하기
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

    //숙소 상세보기
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

        //동일한 이름의 숙소가 있는지 확인 후, 존재하면 에러 날림
        accomodationRepository.findByRoomName(requestDto.getRoomName()).ifPresent(
                m -> {
                    throw new CustomErrorException("이미 등록된 이름의 숙소입니다");
                }
        );

        Accomodation accomodation = new Accomodation(requestDto.getRoomName(), requestDto.getContents(), requestDto.getCost(),
                requestDto.getLocation(), requestDto.getImage(), user);
        
        //db에 숙소 저장
        accomodationRepository.save(accomodation);
        //User 객체의 숙소리스트에 숙소 추가
        user.getMyAccomodations().add(accomodation);

        return accomodation;

    }

    //숙소 수정하기
    @Transactional
    public void editAccomodation(AccomodationRequestDto requestDto) {
        Long id = requestDto.getId();
        System.out.println("수정 id = " + id);
        Accomodation accomodation = accomodationRepository.findById(id).orElseThrow(
                ()-> new CustomErrorException("해당 숙소가 없습니다")
        );
        System.out.println("accomodation 수정 = " + accomodation);
        accomodation.updateAccomodation(requestDto);
    }

    //숙소 삭제하기
    public void deleteAccomodation(Long id) {
        Accomodation accomodation = accomodationRepository.findById(id).orElseThrow(
                ()-> new CustomErrorException("해당 숙소가 없습니다")
        );
        accomodationRepository.delete(accomodation);
    }

}
