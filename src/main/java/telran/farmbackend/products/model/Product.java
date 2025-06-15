package telran.farmbackend.products.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import telran.farmbackend.products.dto.ProductDto;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Document(collection = "products")
public class Product {
    @Id
    String id;
    String name;
    Integer quantity;
    String producer;

    public Product(ProductDto productDto) {
        name = productDto.getName();
        quantity=productDto.getQuantity();
        producer=productDto.getProducer();
    }
}
