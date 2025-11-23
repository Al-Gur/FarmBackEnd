package telran.java57.farmbackend.accounting.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import telran.java57.farmbackend.products.dto.OrderDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Getter
@Document(collection = "users")
public class UserAccount {
    @Id
    String login;
    @Setter
    String password;
    @Setter
    String fullName;
    @Setter
    String email;
    @Setter
    String phone;
    @Setter
    String address;
    Set<String> roles;
    ArrayList<OrderDto> orders;

    public UserAccount() {
        roles = new HashSet<>();
        roles.add("USER");
    }

    public UserAccount(String login, String password, String fullName, String email, String phone, String address) {
        this();
        this.login = login;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public boolean addRole(String role){
        return roles.add(role.toUpperCase());
    }

    public boolean removeRole(String role){
        return roles.remove(role.toUpperCase());
    }
}
