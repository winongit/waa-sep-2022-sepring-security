package edu.miu.springsecurity.controller;

import edu.miu.springsecurity.entity.User;
import edu.miu.springsecurity.model.LogInRequest;
import edu.miu.springsecurity.model.RefreshTokenRequest;
import edu.miu.springsecurity.service.UaaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/uaa")
@CrossOrigin
@RequiredArgsConstructor
public class UaaController {
    private final UaaService uaaService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LogInRequest logInRequest) {
        var logInResponse = uaaService.logIn(logInRequest);
        return ResponseEntity.ok().body(logInResponse);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        var respose = uaaService.refreshToken(refreshTokenRequest);
        return ResponseEntity.ok().body(respose);
    }

    @PostMapping("/signup")
    public void signUp(@RequestBody User user) {
        uaaService.signUpUser(user);

    }



}
