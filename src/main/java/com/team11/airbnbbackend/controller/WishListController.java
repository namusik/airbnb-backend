package com.team11.airbnbbackend.controller;

import com.team11.airbnbbackend.dto.ResponseDto;
import com.team11.airbnbbackend.dto.WishListRequestDto;
import com.team11.airbnbbackend.model.User;
import com.team11.airbnbbackend.model.WishList;
import com.team11.airbnbbackend.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    public ResponseDto addWishLists(
            @RequestBody WishListRequestDto wishListRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long id
    ) {
        List<String> wishListNames = wishListRequestDto.getWishListNames();
        User user = userDetails.getuser();

        return wishListService.addWishLists(wishListNames, user, id);
    }

    @GetMapping("api/wishes")
    public ResponseDto getWishLists(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return wishListService.getWishLists(userDetails.getUser());
    }
}
