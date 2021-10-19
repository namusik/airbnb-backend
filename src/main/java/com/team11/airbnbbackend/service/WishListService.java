package com.team11.airbnbbackend.service;

import com.team11.airbnbbackend.dto.ResponseDto;
import com.team11.airbnbbackend.model.Accomodation;
import com.team11.airbnbbackend.model.User;
import com.team11.airbnbbackend.model.WishList;
import com.team11.airbnbbackend.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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
    public ResponseDto addWishLists(List<String> wishListNames,
                                       User user, Long accomodationId
                                       ) {
        List<WishList> wishList = new ArrayList<>();
        for (String wishListName : wishListNames) {
            WishList wish = new WishList(wishListName, user);
            wishList.add(wish);
        }
        wishListRepository.saveAll(wishList);

        Accomodation accomodation = accomodationRepository.findById(accomodationId).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다"));
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