package telran.java57.farmbackend.accounting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import telran.java57.farmbackend.accounting.dto.UserDto;
import telran.java57.farmbackend.accounting.dto.UserRegisterDto;
import telran.java57.farmbackend.accounting.service.UserAccountService;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserAccountController {
    final UserAccountService userService;

    @PostMapping("register")
    public UserDto register(@RequestBody UserRegisterDto userRegisterDto) {
        return userService.register(userRegisterDto);
    }

    @GetMapping("login")
    public UserDto login(){
        return null;
    }
}
