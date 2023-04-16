package com.manas.api;//package com.bookstore.api;
//
//import com.bookstore.dto.request.RegisterRequest;
//import com.bookstore.dto.request.UserRequest;
//import com.bookstore.dto.response.SimpleResponse;
//import com.bookstore.dto.response.UserTokenResponse;
//import com.bookstore.service.UserService;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/users")
//public class UserApi {
//    private final UserService userService;
//
//    public UserApi(UserService userService) {
//        this.userService = userService;
//    }
//
//
//    @PostMapping("/register")
//    SimpleResponse signIn(@RequestBody RegisterRequest registerRequest){
//        return userService.register(registerRequest);
//    }
//
//    @PostMapping("/authenticate")
//    UserTokenResponse authenticate(@RequestBody UserRequest userRequest){
//        return userService.authenticate(userRequest);
//    }
//
//   }
