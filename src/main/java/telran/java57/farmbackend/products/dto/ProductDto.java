package telran.java57.farmbackend.products.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.java57.farmbackend.products.model.Product;

@Getter
@NoArgsConstructor
//@AllArgsConstructor
//@Builder
public class ProductDto {
    String id;
    String name;
    Integer quantity;
    String producer;

    public ProductDto(Product product) {
        id = product.getId();
        name = product.getName();
        quantity = product.getQuantity();
        producer = product.getProducer();
    }
}
