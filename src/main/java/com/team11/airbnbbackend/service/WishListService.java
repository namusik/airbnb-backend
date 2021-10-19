package com.team11.airbnbbackend.service;

import com.team11.airbnbbackend.dto.ResponseDto;
import com.team11.airbnbbackend.dto.WishListRequestDto;
import com.team11.airbnbbackend.model.Accomodation;
import com.team11.airbnbbackend.model.User;
import com.team11.airbnbbackend.model.WishList;
import com.team11.airbnbbackend.repository.AccomodationRepository;
import com.team11.airbnbbackend.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class WishListService {

    private final WishListRepository wishListRepository;
    private final AccomodationRepository accomodationRepository;

    @Autowired
    public WishListService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    // 로그인한 회원 위시리스트 등록
    @Transactional
    public ResponseDto addWishLists(WishListRequestDto requestDto, User user) {
        Long accomodationId = requestDto.getId();
        WishList wishList = new WishList(requestDto, user);
        Accomodation accomodation = accomodationRepository.findAllById(accomodationId).orElseThrow(
                () -> new IllegalArgumentException("accomodationId가 존재하지 않습니다"));

        wishListRepository.save(wishList);
        accomodation.getWishList().add(wishList);
        accomodationRepository.save(accomodation);
        return new ResponseDto("success", "wishList에 추가되었습니다", "");
    }

    // 로그인한 회원이 등록된 모든 위시리스트 조회
    public ResponseDto getWishLists(User user) {
        wishListRepository.findAllByUser(user);
        return new ResponseDto("success", "위시리스트를 불러왔습니다", "");
    }

}
