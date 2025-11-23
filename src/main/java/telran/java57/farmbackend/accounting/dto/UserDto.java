package telran.java57.farmbackend.accounting.dto;

import lombok.*;
import telran.java57.farmbackend.accounting.model.UserAccount;

import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    String login;
    String fullName;
    String email;
    String phone;
    String address;
    Set<String> roles;

    public UserDto(UserAccount user) {
        login = user.getLogin();
        fullName = user.getFullName();
        email = user.getEmail();
        phone = user.getPhone();
        address = user.getAddress();
        roles = user.getRoles();
    }
}
