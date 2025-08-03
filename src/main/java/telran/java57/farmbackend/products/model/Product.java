package telran.java57.farmbackend.products.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    Integer quantity;
    Integer remainingQuantity;
    ArrayList<Order> orders;
    String producer;

    public Product(String name, Integer price, Integer quantity, String producer) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.remainingQuantity = quantity;
        this.orders = new ArrayList<>();
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

    public Integer calculateRemainedQuantity() {
        if (orders == null) {
            orders = new ArrayList<>();
        }
        remainingQuantity = quantity - orders.stream().map(Order::getQuantity).reduce(0, Integer::sum);
        return remainingQuantity;
    }

    public Integer getRefreshedRemainingQuantity() {
        if (orders == null) {
            orders = new ArrayList<>();
        }
        LocalDateTime now = LocalDateTime.now();
        orders = new ArrayList<>(orders.stream()
                .filter(order -> order.getDeadline().isAfter(now))
                .toList());
        return calculateRemainedQuantity();
    }
}
