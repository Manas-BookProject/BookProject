package com.manas.service;

import com.manas.dto.request.AuthenticationRequest;
import com.manas.dto.request.RegisterRequest;
import com.manas.dto.response.AuthenticationResponse;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountService {
    AuthenticationResponse register(RegisterRequest registerRequest);

    AuthenticationResponse authenticate(AuthenticationRequest userRequest);
}
