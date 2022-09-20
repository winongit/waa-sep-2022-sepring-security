package edu.miu.springsecurity.service.impl;

import edu.miu.springsecurity.entity.Role;
import edu.miu.springsecurity.entity.User;
import edu.miu.springsecurity.model.LogInRequest;
import edu.miu.springsecurity.model.LogInResponse;
import edu.miu.springsecurity.model.RefreshTokenRequest;
import edu.miu.springsecurity.repository.RoleRepo;
import edu.miu.springsecurity.repository.UserRepo;
import edu.miu.springsecurity.security.JWTHelper;
import edu.miu.springsecurity.service.UaaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UaaServiceImpl implements UaaService {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final UserRepo userRepo;

    private final RoleRepo roleRepo;

    private final JWTHelper jwtHelper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public LogInResponse logIn(LogInRequest request) {
        try {
            var result = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );


        } catch (BadCredentialsException e) {
            log.error("Bad Credentials", e);
            throw e;
        }

        final String accessToken = jwtHelper.generateToken(request.getEmail());
        final String refreshToken = jwtHelper.generateRefreshToken(request.getEmail());
        LogInResponse response = new LogInResponse(accessToken, refreshToken);
        return response;
    }

    @Override
    public LogInResponse refreshToken(RefreshTokenRequest request) {
        boolean refreshTokenValid = jwtHelper.validateToken(request.getRefreshToken());

        if (refreshTokenValid) {
            final String accessToken = jwtHelper.generateToken(jwtHelper.getSubject(request.getRefreshToken()));
            var logInResponse = new LogInResponse(accessToken, request.getRefreshToken());
            return logInResponse;
        }
        return new LogInResponse();
    }

    @Override
    public void signUpUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        List<Role> roles = new ArrayList<>();

        for (Role role : user.getRoles()) {
            roles.add(roleRepo.findById(role.getId()).get());
        }

        user.setRoles(roles);
        userRepo.save(user);
    }


}
