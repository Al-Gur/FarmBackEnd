package telran.java57.farmbackend.accounting.dto;

import lombok.*;
import telran.java57.farmbackend.accounting.model.User;

import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    String login;
    String firstName;
    String lastName;
    @Singular
    Set<String> roles;

    public UserDto(User user) {
        login = user.getLogin();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        roles = user.getRoles();
    }
}
