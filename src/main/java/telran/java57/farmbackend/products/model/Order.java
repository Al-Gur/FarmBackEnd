package telran.java57.farmbackend.products.model;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Order {
    Integer quantity;
    String userLogin;
    LocalDateTime deadline;
}
