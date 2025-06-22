package telran.java57.farmbackend.products.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import telran.java57.farmbackend.products.model.Product;

public interface ProductsRepository extends MongoRepository<Product, String> {
}
