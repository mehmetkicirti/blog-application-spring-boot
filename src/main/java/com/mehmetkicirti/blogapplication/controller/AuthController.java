package com.mehmetkicirti.blogapplication.controller;

import com.mehmetkicirti.blogapplication.dto.user.LoginDto;
import com.mehmetkicirti.blogapplication.dto.user.RegisterUserDto;
import com.mehmetkicirti.blogapplication.entity.concrete.User;
import com.mehmetkicirti.blogapplication.service.UserService;
import com.mehmetkicirti.blogapplication.utility.security.JwtTokenProvider;
import com.mehmetkicirti.blogapplication.utility.wrapper.AuthResponse;
import com.mehmetkicirti.blogapplication.utility.wrapper.UserResponse;
import com.mehmetkicirti.blogapplication.utility.wrapper.abstracts.ResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtTokenProvider tokenProvider,
                          UserService userService) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginDto request){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = (User) authentication.getPrincipal();
        String accessToken = tokenProvider.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(accessToken));
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseData<UserResponse>> register(@Valid @RequestBody RegisterUserDto request){
        ResponseData<UserResponse> response = userService.register(request);
        return ResponseEntity.ok(response);
    }
}
