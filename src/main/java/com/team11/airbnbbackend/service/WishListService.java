package com.team11.airbnbbackend.service;

import com.team11.airbnbbackend.dto.ResponseDto;
import com.team11.airbnbbackend.dto.WishListRequestDto;
import com.team11.airbnbbackend.exception.CustomErrorException;
import com.team11.airbnbbackend.model.Accomodation;
import com.team11.airbnbbackend.model.User;
import com.team11.airbnbbackend.model.WishList;
import com.team11.airbnbbackend.repository.AccomodationRepository;
import com.team11.airbnbbackend.repository.UserRepository;
import com.team11.airbnbbackend.repository.WishListRepository;
import com.team11.airbnbbackend.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class WishListService {

    private final WishListRepository wishListRepository;
    private final AccomodationRepository accomodationRepository;
    private final UserRepository userRepository;

    @Autowired
    public WishListService(WishListRepository wishListRepository, AccomodationRepository accomodationRepository, UserRepository userRepository) {
        this.wishListRepository = wishListRepository;
        this.accomodationRepository = accomodationRepository;
        this.userRepository = userRepository;
    }

        // 로그인한 회원 위시리스트 등록
    @Transactional
    public ResponseDto addWishLists(WishListRequestDto requestDto, UserDetailsImpl userDetails) {
        //User 가져오기
        Long id = userDetails.getUser().getId();
        User user = userRepository.getById(id);
        //숙소 객체 가져오기
        Long accomodationId = requestDto.getId();
        Accomodation accomodation = accomodationRepository.findById(accomodationId).orElseThrow(
                () -> new CustomErrorException("해당 숙소가 없습니다")
        );


        wishListRepository.findByUserAndAccomodation(user, accomodation).ifPresent(
                m -> {
                    throw new CustomErrorException("이미 위시리스트에 존재합니다");
                }
        );


        //WishList 객체 생성
        WishList wishList = new WishList(user, accomodation);


        //WishList 객체 DB저장
        accomodation.getWishList().add(wishList);

        user.getMyWishList().add(wishList);

        wishListRepository.save(wishList);

        return new ResponseDto("success", "wishList에 추가되었습니다", wishList);
    }

    // 로그인한 회원이 등록된 모든 위시리스트 조회
    public ResponseDto getWishLists(User user) {
        List<WishList> myWishList =wishListRepository.findAllByUser(user);
        List<Accomodation> accomodationList = new ArrayList<>();
        for (WishList el : myWishList) {
            Accomodation accomodation = el.getAccomodation();
            accomodationList.add(accomodation);
        }
        return new ResponseDto("success", "위시리스트를 불러왔습니다", accomodationList);
    }

    // 로그인한 회원 위시리스트 삭제
    @Transactional
    public ResponseDto deleteWishLists(WishListRequestDto requestDto, UserDetailsImpl userDetails) {
        //User 가져오기
        Long id = userDetails.getUser().getId();
        User user = userRepository.getById(id);

        //숙소 객체 가져오기
        Long accomodationId = requestDto.getId();
        Accomodation accomodation = accomodationRepository.findById(accomodationId).orElseThrow(
                () -> new CustomErrorException("해당 숙소가 없습니다")
        );

        //WishList 객체 가져오기
        WishList wishList = wishListRepository.findByUserAndAccomodation(user, accomodation).orElseThrow(
                () -> new CustomErrorException("위시리스트가 존재하지 않습니다"));

        //WishList 객체 DB삭제
        accomodation.getWishList().remove(wishList);
        user.getMyWishList().remove(wishList);
        wishListRepository.delete(wishList);

        return new ResponseDto("success", "wishList에서 삭제되었습니다", "");
    }
}
