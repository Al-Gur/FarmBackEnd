package telran.java57.farmbackend.accounting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import telran.java57.farmbackend.accounting.dto.RolesDto;
import telran.java57.farmbackend.accounting.dto.UserDto;
import telran.java57.farmbackend.accounting.dto.UserRegisterDto;
import telran.java57.farmbackend.accounting.service.UserAccountService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserAccountController {
    final UserAccountService userService;

    @PostMapping("register")
    public UserDto register(@RequestBody UserRegisterDto userRegisterDto) {
        return userService.register(userRegisterDto);
    }

    @PostMapping("login")
    public UserDto login(Principal principal) {
        return userService.getUser(principal.getName());
    }

    @PutMapping("password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(Principal principal, @RequestHeader("X-Password") String newPassword) {
        userService.changePassword(principal.getName(), newPassword);
    }

    @PutMapping("update")
    public UserDto updateUser(Principal principal, @RequestBody String newFullName) {
        return userService.updateUser(principal.getName(), newFullName);
    }

    @GetMapping("showall")
    public Iterable<UserDto> showAll() {
        return userService.getAllUsers();
    }

    @GetMapping("info/{login}")
    public UserDto getUser(@PathVariable String login) {
        return userService.getUser(login);
    }

    @DeleteMapping("delete/{login}")
    public UserDto removeUser(@PathVariable String login) {
        return userService.removeUser(login);
    }

    @PutMapping("addrole/{login}/{role}")
    public RolesDto addRole(@PathVariable String login, @PathVariable String role) {
        return userService.changeRolesList(login, role, true);
    }

    @DeleteMapping("removerole/{login}/{role}")
    public RolesDto removeRole(@PathVariable String login, @PathVariable String role) {
        return userService.changeRolesList(login, role, false);
    }
}
