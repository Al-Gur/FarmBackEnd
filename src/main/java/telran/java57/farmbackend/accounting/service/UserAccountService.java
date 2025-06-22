package telran.java57.farmbackend.accounting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import telran.java57.farmbackend.accounting.dto.RolesDto;
import telran.java57.farmbackend.accounting.dto.UpdateUserDto;
import telran.java57.farmbackend.accounting.dto.UserDto;
import telran.java57.farmbackend.accounting.dto.UserRegisterDto;

public interface UserAccountService {
    UserDto register(UserRegisterDto userRegisterDto);

    UserDto getUser(String name);

    void changePassword(String name, String newPassword);

    UserDto removeUser(String login);

    UserDto updateUser(String login, UpdateUserDto updateUserDto);

    RolesDto changeRolesList(String login, String role, boolean b);
}
