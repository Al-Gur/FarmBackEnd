package telran.java57.farmbackend.products.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class ProductDto {
    String id;
    String name;
    Integer quantity;
    String producer;
}
