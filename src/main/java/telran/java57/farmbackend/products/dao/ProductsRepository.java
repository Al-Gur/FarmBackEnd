package telran.java57.farmbackend.products.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import telran.java57.farmbackend.products.model.Product;

import java.util.stream.Stream;

public interface ProductsRepository extends MongoRepository<Product, String> {
    Stream<Product> findProductsByCategory(String selectedCategory);

    Stream<Product> findProductsByPriceBefore(Integer priceBefore);

    Stream<Product> findProductsByCategoryAndPriceBefore(String category, Integer priceBefore);
}
