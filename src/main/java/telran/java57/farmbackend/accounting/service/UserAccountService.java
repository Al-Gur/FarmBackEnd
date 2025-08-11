package telran.java57.farmbackend.accounting.service;

import telran.java57.farmbackend.accounting.dto.RolesDto;
import telran.java57.farmbackend.accounting.dto.UserDto;
import telran.java57.farmbackend.accounting.dto.UserRegisterDto;
import telran.java57.farmbackend.products.dto.OrderDto;
import telran.java57.farmbackend.products.dto.ProductDto;

public interface UserAccountService {
    UserDto register(UserRegisterDto userRegisterDto);

    UserDto getUser(String name);

    void changePassword(String name, String newPassword);

    UserDto removeUser(String login);

    UserDto updateUser(String login, String newFullName);

    RolesDto changeRolesList(String login, String role, boolean b);

    Iterable<UserDto> getAllUsers();
}
