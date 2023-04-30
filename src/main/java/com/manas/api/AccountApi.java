package com.manas.api;

import com.manas.dto.request.RegisterRequest;
import com.manas.dto.request.UserRequest;
import com.manas.dto.response.AuthenticationResponse;
import com.manas.dto.response.SimpleResponse;
import com.manas.entity.User;
import com.manas.service.AccountService;
import com.manas.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class AccountApi {

    private final AccountService accountService;
    private final UserService userService;


    @PostMapping("/register")
    public AuthenticationResponse signUp(@RequestBody RegisterRequest registerRequest){
        return accountService.register(registerRequest);
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse signIn(@RequestBody UserRequest userRequest){
        return accountService.authenticate(userRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER', 'VENDOR')")
    @GetMapping
    public List<User> getAllUsers(){
        return userService.findAllUsers();
    }


}
