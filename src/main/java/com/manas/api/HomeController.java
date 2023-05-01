package com.manas.api;

import com.manas.dto.request.RegisterRequest;
import com.manas.entity.User;
import com.manas.service.AccountService;
import com.manas.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class HomeController {

    private final AccountService accountService;
    private final UserService userService;


    @GetMapping
    public String listStudents(Model model){
        model.addAttribute("users", userService.findAllUsers());
        return "users";
    }

    @GetMapping("/new")
    public String createStudentForm(Model model){
        model.addAttribute("user", RegisterRequest.builder().build());
        return "register";
    }

    @PostMapping
    public String saveStudent(@ModelAttribute("student") RegisterRequest registerRequest){
        accountService.register(registerRequest);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String updateUserForm(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "edit_user";
    }

    @PostMapping("/{id}")
    public String updateUser(@PathVariable Long id,
                             @ModelAttribute("user")User user,
                             Model model){
        userService.updateUser(id, user);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
        return "redirect:/users";
    }
}
