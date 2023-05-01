package com.manas.api;

import com.manas.dto.request.AuthenticationRequest;
import com.manas.dto.request.RegisterRequest;
import com.manas.dto.response.AuthenticationResponse;
import com.manas.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AccountApi {

    private final AccountService accountService;


    @PostMapping("/signUp")
    public AuthenticationResponse singUp(@RequestBody @Valid RegisterRequest request) {
        return accountService.register(request);
    }

    @PostMapping("/signIn")
    public AuthenticationResponse signIn(@RequestBody @Valid AuthenticationRequest request) {
        return accountService.authenticate(request);
    }


}
