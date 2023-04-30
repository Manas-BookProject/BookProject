package com.manas.service;

import com.manas.dto.request.RegisterRequest;
import com.manas.dto.request.UserRequest;
import com.manas.dto.response.AuthenticationResponse;
import com.manas.dto.response.SimpleResponse;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountService {
    AuthenticationResponse register(RegisterRequest registerRequest);


    AuthenticationResponse authenticate(UserRequest userRequest);
}
