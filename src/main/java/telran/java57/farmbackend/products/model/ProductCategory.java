package telran.java57.farmbackend.products.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "category")
@Document(collection = "categories")
public class ProductCategory {
    @Id
    String category;
    @Setter
    Integer count;
}
