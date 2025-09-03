package telran.java57.farmbackend.products.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import telran.java57.farmbackend.products.model.Product;

import java.util.stream.Stream;

public interface ProductsRepository extends MongoRepository<Product, String> {
    Stream<Product> findProductsByCategory(String selectedCategory);
}
