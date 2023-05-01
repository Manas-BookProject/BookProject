package com.manas.service.impl;

import com.manas.config.jwt.JwtService;
import com.manas.dto.request.AuthenticationRequest;
import com.manas.dto.request.RegisterRequest;
import com.manas.dto.response.AuthenticationResponse;
import com.manas.entity.Account;
import com.manas.entity.User;
import com.manas.enums.Role;
import com.manas.exceptions.AlreadyExistException;
import com.manas.exceptions.BadRequestException;
import com.manas.repository.AccountRepository;
import com.manas.repository.UserRepository;
import com.manas.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        if (accountRepository.existsByEmail(registerRequest.email())){
            log.error(String.format("User with login: %s is exists", registerRequest.email()));
            throw new AlreadyExistException(String.format(
                    "User with login: %s is exists", registerRequest.email()));
        }
        User user = User.builder()
                .firstName(registerRequest.firstName())
                .lastName(registerRequest.lastName())
                .phoneNumber(registerRequest.phoneNumber())
                .account(Account.builder()
                        .email(registerRequest.email())
                        .password(passwordEncoder.encode(registerRequest.password()))
                        .role(Role.CUSTOMER).build()
                ).build();
        userRepository.save(user);

        return AuthenticationResponse.builder()
                .login(user.getAccount().getEmail())
                .token(jwtService.generateToken(user.getAccount()))
                .role(user.getAccount().getRole().name())
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest userRequest) {
        if (!accountRepository.existsByEmail(userRequest.login())){
            throw new BadRequestException("User with email: " + userRequest.login()+ " doesn't exists!");
        }
        Account account = accountRepository.findByEmail(userRequest.login())
                .orElseThrow(()->{
                    log.error(String.format("User %s is not found!", userRequest.login()));
                    return new AlreadyExistException(String.format("User %s is not found!", userRequest.login()));
                });
        if (!passwordEncoder.matches(userRequest.password(), account.getPassword())){
            throw new BadCredentialsException("Invalid password!");
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userRequest.login(),
                userRequest.password())
        );

        return AuthenticationResponse.builder()
                .login(userRequest.login())
                .token(jwtService.generateToken(account))
                .role(account.getRole().name())
                .build();

    }
}