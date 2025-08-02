package telran.java57.farmbackend.products.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Order {
    Integer quantity;
    LocalDateTime deadline;
}
