package telran.farmbackend.products.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import telran.farmbackend.products.model.Product;

public interface ProductsRepository extends MongoRepository<Product, String> {
}
