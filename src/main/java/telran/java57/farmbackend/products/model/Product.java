package telran.java57.farmbackend.products.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Document(collection = "products")
public class Product {
    @Id
    String id;
    String name;
    Integer quantity;
    String producer;

    public Product(String name, Integer quantity, String producer) {
        this.name = name;
        this.quantity = quantity;
        this.producer = producer;
    }
}
