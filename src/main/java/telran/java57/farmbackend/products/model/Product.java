package telran.java57.farmbackend.products.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    Integer quantity;
    ArrayList<Order> orders;
    String producer;

    public Product(String name, Integer quantity, String producer) {
        this.name = name;
        this.quantity = quantity;
        this.producer = producer;
        this.orders = new ArrayList<>();
    }

    public Product(String id, String name, Integer quantity, String producer) {
        this(name, quantity, producer);
        this.id = id;
    }

    public Product(String name, String image, String category, Integer quantity, String producer) {
        this(name, quantity, producer);
        this.image = image;
        this.category = category;
    }
}
