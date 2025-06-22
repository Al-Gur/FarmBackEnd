package telran.java57.farmbackend.accounting.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Getter

@AllArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    String id;
    String name;
    String password;
    Set<String> roles;
}
