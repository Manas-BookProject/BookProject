package com.manas.api;

import com.manas.dto.request.RegisterRequest;
import com.manas.dto.request.UserRequest;
import com.manas.dto.response.SimpleResponse;
import com.manas.dto.response.UserTokenResponse;
import com.manas.entity.User;
import com.manas.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public SimpleResponse signUp(@RequestBody RegisterRequest registerRequest){
        return userService.register(registerRequest);
    }

    @PostMapping("/authenticate")
    public UserTokenResponse signIn(@RequestBody UserRequest userRequest){
        return userService.authenticate(userRequest);
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId){
        return userService.findUserById(userId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER', 'VENDOR')")
    @GetMapping
    public List<User> getAllUsers(){
        return userService.findAllUsers();
    }


}
