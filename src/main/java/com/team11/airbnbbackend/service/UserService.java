package com.team11.airbnbbackend.service;

import com.team11.airbnbbackend.dto.ResponseDto;
import com.team11.airbnbbackend.dto.UserRequestDto;
import com.team11.airbnbbackend.exception.CustomErrorException;
import com.team11.airbnbbackend.model.Accomodation;
import com.team11.airbnbbackend.model.User;
import com.team11.airbnbbackend.model.UserRoleEnum;
import com.team11.airbnbbackend.repository.UserRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //회원가입 로직
    public ResponseDto signup(UserRequestDto userRequestDto) {
        String username = userRequestDto.getUsername();

        // 회원 ID 중복 확인
        userRepository.findByUsername(username).ifPresent(
                m -> { throw new CustomErrorException("중복된 유저네임이 존재합니다.");
                }
        );

        //패스워드 암호화
        String password = passwordEncoder.encode(userRequestDto.getPassword());

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;

        //true면 == 관리자이면
        if (userRequestDto.isAdmin()) {
            if (!userRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new CustomErrorException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            //role을 admin으로 바꿔준다
            role = UserRoleEnum.ADMIN;
        }
        LocalDate birth = userRequestDto.getBirth();

        String email = userRequestDto.getEmail();
        userRepository.findByEmail(email).ifPresent(
                m -> { throw new CustomErrorException("중복된 이메일이 존재합니다.");
                }
        );
        
        User user = new User(username, password, birth, email, role);

        try {
            userRepository.save(user);
        }catch (Exception e){
            throw new CustomErrorException("회원가입에 실패하였습니다.");
        }

        return new ResponseDto("success", "회원가입에 성공하였습니다", "");
    }
    
    //로그인 로직
    public User login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new CustomErrorException("이메일을 찾을 수 없습니다.")
        );

        // 패스워드 암호화
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new CustomErrorException("비밀번호가 맞지 않습니다.");
        }
        return user;
    }
    
    //회원정보 가져오기 로직
    public User getUserInfo(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new CustomErrorException("존재하지 않는 사용자입니다")
        );
    }
}
