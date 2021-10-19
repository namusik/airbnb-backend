package com.team11.airbnbbackend.controller;

import com.team11.airbnbbackend.dto.ResponseDto;
import com.team11.airbnbbackend.dto.UserRequestDto;
import com.team11.airbnbbackend.dto.LoginResponseDto;
import com.team11.airbnbbackend.dto.UserResponseDto;
import com.team11.airbnbbackend.model.Accomodation;
import com.team11.airbnbbackend.model.User;
import com.team11.airbnbbackend.security.UserDetailsImpl;
import com.team11.airbnbbackend.security.jwt.JwtTokenProvider;
import com.team11.airbnbbackend.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    //회원가입
    @PostMapping("/api/users")
    @ApiOperation(value = "회원가입 API", notes = "회원가입form에서 정보를 받아 DB에 저장합니다")
    public ResponseDto signUp(@RequestBody UserRequestDto userRequestDto) {

        return userService.signup(userRequestDto);
    }

    // 로그인
    @PostMapping("/api/users/login")
    public LoginResponseDto login(@RequestBody HashMap<String, String> map, HttpServletResponse response){
        String email = map.get("email");
        String password = map.get("password");
        System.out.println(email);
        System.out.println(password);
        User user = userService.login(email, password);
        String checkEmail = user.getEmail();

        String token = jwtTokenProvider.createToken(checkEmail);
        response.setHeader("X-AUTH-TOKEN", token);

        //header에 cookie 저장도 해주고
        Cookie cookie = new Cookie("X-AUTH-TOKEN", token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);

        //body에도 보내주기 혹시모르니까
        return new LoginResponseDto("success","로그인 성공했습니다",token, user);
    }

    //로그아웃
    @PostMapping("/api/users/logout")
    public ResponseDto logout(HttpServletResponse response){
        Cookie cookie = new Cookie("X-AUTH-TOKEN", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return new ResponseDto("success", "로그아웃 성공", "");
    }

    //로그인 유저 정보 불러오기
    @GetMapping("api/users")
    public UserResponseDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long id = userDetails.getUser().getId();

        User user = userService.getUserInfo(id);

        List<Accomodation> accomodationList = user.getMyAccomodations();
        return new UserResponseDto("success", "정보를 가져왔습니다.", user, accomodationList);
    }

}
