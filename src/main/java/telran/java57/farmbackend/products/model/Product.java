package telran.java57.farmbackend.products.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Document(collection = "products")
public class Product {
    @Id
    String id;
    String name;
    String image;
    String category;
    Integer price;
    @Setter
    Integer quantity;
    String producer;

    public Product(String name, Integer price, Integer quantity, String producer) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.producer = producer;
    }

    public Product(String id, String name, Integer price, Integer quantity, String producer) {
        this(name, price, quantity, producer);
        this.id = id;
    }

    public Product(String name, String image, String category, Integer price, Integer quantity, String producer) {
        this(name, price, quantity, producer);
        this.image = image;
        this.category = category;
    }
}
