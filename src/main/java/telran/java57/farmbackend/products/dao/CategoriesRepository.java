package telran.java57.farmbackend.products.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import telran.java57.farmbackend.products.model.ProductCategory;

public interface CategoriesRepository extends MongoRepository<ProductCategory, String> {
}
