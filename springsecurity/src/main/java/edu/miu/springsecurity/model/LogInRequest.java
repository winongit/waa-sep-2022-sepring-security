package edu.miu.springsecurity.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogInRequest {
    private String email;
    private String password;
}
