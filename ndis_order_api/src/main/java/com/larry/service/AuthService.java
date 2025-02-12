package com.larry.service;

import com.larry.pojo.User;
import com.larry.pojo.dto.LoginRequestDto;
import com.larry.pojo.dto.LoginResponseDto;
import com.larry.pojo.dto.RegisterRequestDto;
import com.larry.pojo.exception.ResourceNotFoundException;
import com.larry.pojo.jwt.JwtUtil;
import com.larry.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(()->new ResourceNotFoundException("User Not Found"));
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new LoginResponseDto(token);
    }

    public boolean register(RegisterRequestDto registerRequestDto) {
        User user = new User();
        user.setEmail(registerRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        user.setUserName(registerRequestDto.getEmail());
        user.setFullName(registerRequestDto.getFullName());
        user.setRole(User.Role.CUSTOMER);

        userRepository.save(user);
        return true;
    }
}
