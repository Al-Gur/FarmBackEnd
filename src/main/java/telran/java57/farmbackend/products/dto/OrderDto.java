package telran.java57.farmbackend.products.dto;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    String productId;
    Integer quantity;
}
