package edu.miu.springsecurity.service;

import edu.miu.springsecurity.entity.User;
import edu.miu.springsecurity.model.LogInRequest;
import edu.miu.springsecurity.model.LogInResponse;
import edu.miu.springsecurity.model.RefreshTokenRequest;

public interface UaaService {
    LogInResponse logIn(LogInRequest request);

    LogInResponse refreshToken(RefreshTokenRequest request);

    void signUpUser(User user);
}
