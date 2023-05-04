package com.manas.api;

import com.manas.dto.response.UserResponse;
import com.manas.entity.User;
import com.manas.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApi {
    private final UserService userService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER')")
    @GetMapping("/getAll")
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }



}
