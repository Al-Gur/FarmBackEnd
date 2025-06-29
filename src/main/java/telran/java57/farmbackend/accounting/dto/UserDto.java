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
    Set<String> roles;

    public UserDto(UserAccount user) {
        login = user.getLogin();
        fullName = user.getFullName();
        roles = user.getRoles();
    }
}
