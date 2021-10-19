package com.team11.airbnbbackend.controller;

import com.team11.airbnbbackend.dto.ResponseDto;
import com.team11.airbnbbackend.dto.WishListRequestDto;
import com.team11.airbnbbackend.model.User;
import com.team11.airbnbbackend.security.UserDetailsImpl;
import com.team11.airbnbbackend.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WishListController {
    private final WishListService wishListService;

    @Autowired
    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @PostMapping("api/wishes")
    public ResponseDto addWishLists(@RequestBody WishListRequestDto requestDto,
                                    @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
//        List<String> wishListNames = wishListRequestDto.getWishListNames();
        User user = userDetails.getUser();

        return wishListService.addWishLists(requestDto, user);
    }

    @GetMapping("api/wishes")
    public ResponseDto getWishLists(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return wishListService.getWishLists(userDetails.getUser());
    }
}
